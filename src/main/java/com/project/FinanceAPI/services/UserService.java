package com.project.FinanceAPI.services;

import com.project.FinanceAPI.DTOs.request.ChangePasswordRequestDTO;
import com.project.FinanceAPI.DTOs.request.LoginRequestDTO;
import com.project.FinanceAPI.DTOs.request.UserRequestDTO;
import com.project.FinanceAPI.DTOs.request.UserUpdateRequestDTO;
import com.project.FinanceAPI.DTOs.response.UserResponseDTO;
import com.project.FinanceAPI.exceptions.DuplicationResourceException;
import com.project.FinanceAPI.exceptions.InvalidCredentialsException;
import com.project.FinanceAPI.exceptions.UserNotFoundException;
import com.project.FinanceAPI.mapper.implementations.UserMapper;
import com.project.FinanceAPI.model.entities.User;
import com.project.FinanceAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createNewUser(UserRequestDTO userRequestDTO) {

        if(this.userRepository.existsByEmail(userRequestDTO.email())) {
            throw new DuplicationResourceException("This email already exists.");
        }

        User newUser = this.userMapper.toUser(userRequestDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        User userSaved = this.userRepository.save(newUser);

        return this.userMapper.toResponseDTO(userSaved);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        return this.userMapper.toResponseDTOList(users);
    }

    public UserResponseDTO getUserById(UUID userId) {
        User user = this.getUserEntityById(userId);

        return this.userMapper.toResponseDTO(user);
    }

    public UserResponseDTO updateUser(UserUpdateRequestDTO updateDTO, UUID userId) {
        User user = this.getUserEntityById(userId);

        if(updateDTO.email() != null && !updateDTO.email().isBlank()) {
            user.setEmail(updateDTO.email());
        }

        if(updateDTO.name() != null && !updateDTO.name().isBlank()) {
            user.setName(updateDTO.name());
        }

        User userUpdated = this.userRepository.save(user);

        return this.userMapper.toResponseDTO(userUpdated);
    }

    public UserResponseDTO changePassword(ChangePasswordRequestDTO passwordDto, UUID userId) {
        User user = this.getUserEntityById(userId);

        if(!this.passwordEncoder.matches(passwordDto.currentPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Your current password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.newPassword()));

        User userUpdated = this.userRepository.save(user);

        return this.userMapper.toResponseDTO(userUpdated);
    }

    public void deleteUser(UUID userId) {
        User user = this.getUserEntityById(userId);

        this.userRepository.delete(user);
    }

    public boolean verifyLogin(LoginRequestDTO loginDto) {
        User user = this.userRepository.findByEmail(loginDto.email())
                .orElseThrow(UserNotFoundException::new);

        return this.passwordEncoder.matches(loginDto.password(), user.getPassword());
    }

    private User getUserEntityById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

}
