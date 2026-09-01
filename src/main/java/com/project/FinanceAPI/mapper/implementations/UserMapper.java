package com.project.FinanceAPI.mapper.implementations;

import com.project.FinanceAPI.DTOs.request.UserRequestDTO;
import com.project.FinanceAPI.DTOs.response.UserResponseDTO;
import com.project.FinanceAPI.mapper.interfaces.UserMapperInterface;
import com.project.FinanceAPI.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements UserMapperInterface {

    @Override
    public User toUser(UserRequestDTO dto) {

        if(dto == null) {
            return null;
        }

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    @Override
    public UserResponseDTO toResponseDTO(User entity) {

        if(entity == null){
            return null;
        }

        return new UserResponseDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreated_at());
    }

    @Override
    public List<UserResponseDTO> toResponseDTOList(List<User> userList) {
        if(userList == null) {
            return null;
        }

        return userList.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
