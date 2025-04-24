package FinanceApp.controller.User;


import FinanceApp.dto.User.UserRequest;
import FinanceApp.dto.User.UserResponse;
import FinanceApp.service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody @Valid UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @PutMapping
    public UserResponse update(@RequestBody @Valid UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @GetMapping
    public UserResponse getCurrentUser() {
        return userService.get();
    }

    @DeleteMapping
    public ResponseEntity<String> delete(){
        return userService.delete();
    }
}
