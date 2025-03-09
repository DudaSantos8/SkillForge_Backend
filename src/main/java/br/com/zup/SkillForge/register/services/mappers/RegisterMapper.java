package br.com.zup.SkillForge.register.services.mappers;

import br.com.zup.SkillForge.register.dtos.RegisterUserRequestDTO;
import br.com.zup.SkillForge.register.dtos.RegisterUserResponseDTO;
import br.com.zup.SkillForge.register.models.RegisterUser;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {

    public RegisterUser toModel(RegisterUserRequestDTO dto) {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setEmail(dto.getEmail());
        registerUser.setPassword(dto.getPassword());
        return registerUser;
    }

    public RegisterUserResponseDTO toDto(RegisterUser registerUser) {
        RegisterUserResponseDTO dto = new RegisterUserResponseDTO();
        dto.setId(registerUser.getId());
        dto.setEmail(registerUser.getEmail());
        return dto;
    }
}