package com.project.FinanceAPI.controllers;

import com.project.FinanceAPI.DTOs.request.UserRequestDTO;
import com.project.FinanceAPI.DTOs.request.UserUpdateRequestDTO;
import com.project.FinanceAPI.DTOs.response.UserResponseDTO;
import com.project.FinanceAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody @Validated UserRequestDTO requestDto) {
        UserResponseDTO newUserDto = this.userService.createNewUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = this.userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "id") UUID userId) {
        UserResponseDTO userDto = this.userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable(value = "id") UUID userId, @RequestBody @Validated UserUpdateRequestDTO updateRequestDto) {
        UserResponseDTO userUpdatedDto = this.userService.updateUser(userId, updateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdatedDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(value = "id") UUID userId) {

        this.userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
