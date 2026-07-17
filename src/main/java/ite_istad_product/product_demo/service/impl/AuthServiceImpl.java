package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.dto.auth.RegisterRequest;
import ite_istad_product.product_demo.dto.auth.RegisterResponse;
import ite_istad_product.product_demo.entity.User;
import ite_istad_product.product_demo.mapper.UserMapper;
import ite_istad_product.product_demo.repository.UserRepository;
import ite_istad_product.product_demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final Keycloak keycloak;
    private final UserMapper userMapper;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    private ClientRepresentation getClientById(String clientId) {
        return keycloak.realm(realm)
                .clients()
                .findByClientId(clientId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No client found with id: " + clientId));
    }

    private RegisterResponse createUserInKeycloak(RegisterRequest request) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(request.username());
        userRepresentation.setEmail(request.email());
        userRepresentation.setFirstName(request.firstName());
        userRepresentation.setLastName(request.lastName());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender", List.of(request.gender()));
        attributes.put("biography", List.of(request.biography()));

        userRepresentation.setAttributes(attributes);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(request.password());

        userRepresentation.setCredentials(List.of(credential));

        var usersResource = keycloak.realm(realm).users();

        try (var response = usersResource.create(userRepresentation)) {

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user in Keycloak.");
            }

            String userId = CreatedResponseUtil.getCreatedId(response);

            UserResource userResource = keycloak.realm(realm)
                    .users()
                    .get(userId);

            ClientRepresentation client = getClientById(clientId);

            var roleRepresentation = keycloak.realm(realm)
                    .clients()
                    .get(client.getId())
                    .roles()
                    .get("CUSTOMER")
                    .toRepresentation();

            userResource.roles()
                    .clientLevel(client.getId())
                    .add(List.of(roleRepresentation));

            return userMapper.toRegisterResponse(userRepresentation);

        } catch (Exception ex) {
            log.error("Error creating user in Keycloak", ex);
            throw new RuntimeException("Error creating user in Keycloak", ex);
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (!request.password().equals(request.confirmedPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        RegisterResponse response = createUserInKeycloak(request);

        User user = new User();
        user.setKeycloakId(response.id());
        user.setUsername(response.username());
        user.setEmail(response.email());

        userRepository.save(user);

        return response;
    }
}