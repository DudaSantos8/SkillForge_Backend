package br.com.zup.SkillForge.login.controllers;

import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.services.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginUserResponseDTO> create(@RequestBody @Valid LoginUserRequestDTO requestDTO) {
        LoginUserResponseDTO responseDTO = loginService.createUser(requestDTO);
        logger.info("User created with email: {}", requestDTO.getEmail());
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginUserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid LoginUserRequestDTO requestDTO) {
        LoginUserResponseDTO responseDTO = loginService.updateUser(id, requestDTO);
        logger.info("User updated with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LoginUserResponseDTO>> listUsers() {
        List<LoginUserResponseDTO> responseDTOList = loginService.listUsers();
        logger.info("Listing all users");
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginUserResponseDTO> getUserById(@PathVariable Long id) {
        LoginUserResponseDTO responseDTO = loginService.getUserById(id);
        logger.info("Fetching user with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loginService.deleteUser(id);
        logger.info("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}