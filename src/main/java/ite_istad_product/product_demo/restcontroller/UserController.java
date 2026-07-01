package ite_istad_product.product_demo.restcontroller;


import ite_istad_product.product_demo.dto.user.CreateUserRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;
import ite_istad_product.product_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public UserResponse createNew(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }
}
