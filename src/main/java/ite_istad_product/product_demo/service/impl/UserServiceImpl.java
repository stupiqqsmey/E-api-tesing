package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.dto.user.CreateUserRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;
import ite_istad_product.product_demo.entity.Profile;
import ite_istad_product.product_demo.mapper.UserMapper;
import ite_istad_product.product_demo.repository.ProfileRepository;
import ite_istad_product.product_demo.repository.UserRepository;
import ite_istad_product.product_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        var user = userMapper.toEntity(request);
        var profile = new Profile();

        profile.setBio(request.bio());
        profile.setUrl(request.url());
        profile.setUser(user);
        user.setProfile(profile);
        return userMapper.toResponse(userRepository.save(user));
    }
   @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toResponse)
                .toList();
    }
}