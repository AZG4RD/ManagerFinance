package FinanceApp.service.User;

import FinanceApp.dto.User.RegistrationRequest;
import FinanceApp.dto.User.UserRequest;
import FinanceApp.dto.User.UserResponse;
import FinanceApp.model.User;
import FinanceApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private String getEmailName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private User getCurrentUserIfNotDeleted(){
        return getCurrentUser(userRepository.findByEmailAndIsDeletedFalse(getEmailName()));
    }

    private User getCurrentUser(Optional<User> userOptional){
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return userOptional.get();
    }

    @Transactional
    @Override
    public UserResponse create(RegistrationRequest registrationRequest) {
        Optional<User> userOptional = userRepository.findByEmail(registrationRequest.getEmail());

        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getDeletedAt() != null){
                throw new UserAlreadyDeletedException("Пользователь был удален");
            }
            throw new UserAlreadyExistsException("Пользователь существует");
        }
        User user = modelMapper.map(registrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    @Override
    public UserResponse update(UserRequest userRequest) {
        User user = getCurrentUserIfNotDeleted();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setBirthday(userRequest.getBirthday());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    @Override
    public UserResponse get() {
        User user = getCurrentUserIfNotDeleted();
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete() {
        Optional<User> optionalUser = userRepository.findByEmail(getEmailName());

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        User user = optionalUser.get();

        if (user.getDeletedAt() != null) {
            throw new UserAlreadyDeletedException("Пользователь удален");
        }

        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok("Пользователь удален успешно");
    }
}

