package com.ajay.productivity.controller;

import com.ajay.productivity.dto.UserResponse;
import com.ajay.productivity.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "User Management",
        description = "Endpoints for retrieving user information and managing registered users"
)
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Retrieve user by ID",
            description = "Returns the details of the user associated with the provided identifier."
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @Operation(
            summary = "Retrieve All users",
            description = "Returns a paginated list of registered users."
    )
    @SecurityRequirement(name = "bearerAuth")
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

    @Operation(
            summary = "Retrieve user by email",
            description = "Returns the user associated with the specified email address."
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

}
