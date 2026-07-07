package com.ajay.productivity.service;

import com.ajay.productivity.dto.UserResponse;
import com.ajay.productivity.entity.User;
import com.ajay.productivity.exception.UserNotFoundException;
import com.ajay.productivity.util.EntityToDTOConverter;
import com.ajay.productivity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityToDTOConverter converter;


    public Page<UserResponse> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(converter::userToUserResponse);
    }

    public UserResponse findById(Long id){
        User user= userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return converter.userToUserResponse(user);

    }

    public UserResponse findByEmail(String email){
        User user= userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException(email));
        return converter.userToUserResponse(user);
    }

}
