package com.ajay.productivity.controller;

import com.ajay.productivity.dto.UserResponse;
import com.ajay.productivity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true")boolean ascending
    ){
        Sort sort=ascending?Sort.by(sortBy):Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

}
