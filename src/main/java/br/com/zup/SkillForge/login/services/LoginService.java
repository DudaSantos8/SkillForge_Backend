package br.com.zup.SkillForge.login.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
import br.com.zup.SkillForge.login.repositories.LoginRepository;
import br.com.zup.SkillForge.login.services.mappers.LoginMapper;
import br.com.zup.SkillForge.register.models.RegisterUser;
import br.com.zup.SkillForge.register.repositories.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;
    private final RegisterRepository registerRepository;

    public LoginUserResponseDTO login(LoginUserRequestDTO loginUserRequestDTO) {
        RegisterUser registeredUser = registerRepository.findByEmail(loginUserRequestDTO.getEmail())
                .orElseThrow(() -> {
                    logger.error("Login failed: email not found - {}", loginUserRequestDTO.getEmail());
                    return new ResourceNotFoundException("Email not registered");
                });

        if (!registeredUser.getPassword().equals(loginUserRequestDTO.getPassword())) {
            logger.error("Login failed: incorrect password for email - {}", loginUserRequestDTO.getEmail());
            throw new IllegalArgumentException("Invalid email or password");
        }

        LoginUser loginUser = loginMapper.toModel(loginUserRequestDTO);
        loginUser = loginRepository.save(loginUser);
        logger.info("User logged in with email: {}", loginUser.getEmail());
        return loginMapper.toDto(loginUser);
    }

    public LoginUserResponseDTO createUser(LoginUserRequestDTO loginUserRequestDTO) {
        LoginUser loginUser = loginMapper.toModel(loginUserRequestDTO);
        loginUser = loginRepository.save(loginUser);
        logger.info("User created with id: {}", loginUser.getId());
        return loginMapper.toDto(loginUser);
    }

    public LoginUserResponseDTO updateUser(Long id, LoginUserRequestDTO loginUserRequestDTO) {
        return loginRepository.findById(id)
                .map(existingLoginUser -> {
                    existingLoginUser.setEmail(loginUserRequestDTO.getEmail());
                    existingLoginUser.setPassword(loginUserRequestDTO.getPassword());
                    LoginUser savedLoginUser = loginRepository.save(existingLoginUser);
                    logger.info("User updated with id: {}", savedLoginUser.getId());
                    return loginMapper.toDto(savedLoginUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        if (!loginRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        loginRepository.deleteById(id);
        logger.info("User deleted with id: {}", id);
    }

    public List<LoginUserResponseDTO> listUsers() {
        List<LoginUserResponseDTO> users = loginRepository.findAll().stream()
                .map(loginMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Listing all users");
        return users;
    }

    public LoginUserResponseDTO getUserById(Long id) {
        return loginRepository.findById(id)
                .map(loginMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }
}
