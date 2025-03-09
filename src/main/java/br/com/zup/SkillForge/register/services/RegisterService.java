package br.com.zup.SkillForge.register.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.register.dtos.RegisterUserRequestDTO;
import br.com.zup.SkillForge.register.dtos.RegisterUserResponseDTO;
import br.com.zup.SkillForge.register.models.RegisterUser;
import br.com.zup.SkillForge.register.repositories.RegisterRepository;
import br.com.zup.SkillForge.register.services.mappers.RegisterMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    private final RegisterRepository registerRepository;
    private final RegisterMapper registerMapper;

    public RegisterUserResponseDTO createUser(RegisterUserRequestDTO registerUserRequestDTO) {
        validatePasswords(registerUserRequestDTO);

        RegisterUser registerUser = registerMapper.toModel(registerUserRequestDTO);
        registerUser = registerRepository.save(registerUser);
        logger.info("User created with id: {}", registerUser.getId());
        return registerMapper.toDto(registerUser);
    }

    public RegisterUserResponseDTO updateUser(Long id, RegisterUserRequestDTO registerUserRequestDTO) {
        validatePasswords(registerUserRequestDTO);

        return registerRepository.findById(id)
                .map(existingRegisterUser -> {
                    existingRegisterUser.setEmail(registerUserRequestDTO.getEmail());
                    existingRegisterUser.setPassword(registerUserRequestDTO.getPassword());
                    RegisterUser savedRegisterUser = registerRepository.save(existingRegisterUser);
                    logger.info("User updated with id: {}", savedRegisterUser.getId());
                    return registerMapper.toDto(savedRegisterUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        if (!registerRepository.existsById(id)) {
            logger.error("Attempted to delete non-existent user with id: {}", id);
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        registerRepository.deleteById(id);
        logger.info("User deleted with id: {}", id);
    }

    public List<RegisterUserResponseDTO> listUsers() {
        List<RegisterUserResponseDTO> users = registerRepository.findAll().stream()
                .map(registerMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Listing all users, total: {}", users.size());
        return users;
    }

    public RegisterUserResponseDTO getUserById(Long id) {
        return registerRepository.findById(id)
                .map(registerMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }

    private void validatePasswords(RegisterUserRequestDTO registerUserRequestDTO) {
        if (!registerUserRequestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", registerUserRequestDTO.getEmail());
            throw new IllegalArgumentException("Passwords do not match");
        }
    }
}