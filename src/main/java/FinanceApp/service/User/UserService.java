package FinanceApp.service.User;


import FinanceApp.dto.User.RegistrationRequest;
import FinanceApp.dto.User.UserRequest;
import FinanceApp.dto.User.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponse create(RegistrationRequest registrationRequest);

    UserResponse update(UserRequest userRequest);

    UserResponse get();

    ResponseEntity<String> delete();
}
