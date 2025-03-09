package br.com.zup.SkillForge.register.controllers;

import br.com.zup.SkillForge.register.dtos.RegisterUserRequestDTO;
import br.com.zup.SkillForge.register.dtos.RegisterUserResponseDTO;
import br.com.zup.SkillForge.register.services.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<RegisterUserResponseDTO> create(@RequestBody @Valid RegisterUserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for email: {}", requestDTO.getEmail());
            return ResponseEntity.badRequest().build();
        }
        RegisterUserResponseDTO responseDTO = registerService.createUser(requestDTO);
        logger.info("User created with email: {}", requestDTO.getEmail());
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegisterUserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid RegisterUserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for email: {}", requestDTO.getEmail());
            return ResponseEntity.badRequest().build();
        }
        RegisterUserResponseDTO responseDTO = registerService.updateUser(id, requestDTO);
        logger.info("User updated with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RegisterUserResponseDTO>> listUsers() {
        List<RegisterUserResponseDTO> responseDTOList = registerService.listUsers();
        logger.info("Listing all users");
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterUserResponseDTO> getUserById(@PathVariable Long id) {
        RegisterUserResponseDTO responseDTO = registerService.getUserById(id);
        logger.info("Fetching user with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registerService.deleteUser(id);
        logger.info("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}