package br.com.zup.SkillForge.login.services.mappers;

import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginUser toModel(LoginUserRequestDTO dto) {
        LoginUser loginUser = new LoginUser();
        loginUser.setEmail(dto.getEmail());
        loginUser.setPassword(dto.getPassword());
        return loginUser;
    }

    public LoginUserResponseDTO toDto(LoginUser loginUser) {
        LoginUserResponseDTO dto = new LoginUserResponseDTO();
        dto.setId(loginUser.getId());
        dto.setEmail(loginUser.getEmail());
        return dto;
    }
}