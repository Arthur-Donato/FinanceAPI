package com.project.FinanceAPI.mapper.interfaces;

import com.project.FinanceAPI.DTOs.request.UserRequestDTO;
import com.project.FinanceAPI.DTOs.response.UserResponseDTO;
import com.project.FinanceAPI.model.entities.User;

import java.util.List;

public interface UserMapperInterface {

    User toUser(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(User entity);

    List<UserResponseDTO> toResponseDTOList(List<User> entities);
}
