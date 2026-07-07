package com.ajay.productivity.service;

import com.ajay.productivity.dto.CreateUserRequest;
import com.ajay.productivity.dto.LoginRequest;
import com.ajay.productivity.dto.LoginResponse;
import com.ajay.productivity.dto.UserResponse;
import com.ajay.productivity.entity.User;
import com.ajay.productivity.exception.InvalidInputException;
import com.ajay.productivity.util.EntityToDTOConverter;
import com.ajay.productivity.model.Role;
import com.ajay.productivity.repository.UserRepository;
import com.ajay.productivity.security.CustomUserDetails;
import com.ajay.productivity.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Log log= LogFactory.getLog(AuthService.class);

    private final EntityToDTOConverter converter;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),loginRequest.password()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt=jwtService.generateToken(userDetails);
        return new LoginResponse(jwt,"Bearer",jwtService.getExpiration());
    }

    public UserResponse createUser(CreateUserRequest createUserRequest){
        if(userRepository.findByEmail(createUserRequest.email()).isPresent()){
            log.warn("Email already exist");
            throw new InvalidInputException("Email already exists please try with a new email or try logging to user actual account");
        }
        User user=new User();
        user.setUsername(createUserRequest.username());
        user.setEmail(createUserRequest.email());

        String hashedPassword=passwordEncoder.encode(createUserRequest.password());
        user.setPassword(hashedPassword);

        user.setRole(Role.USER);
        userRepository.save(user);
        return converter.userToUserResponse(user);
    }
}
