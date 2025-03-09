package br.com.zup.SkillForge.loginTest;

<<<<<<< HEAD
import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
=======
import br.com.zup.SkillForge.infras.security.PasswordUtil;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6
import br.com.zup.SkillForge.login.repositories.LoginRepository;
import br.com.zup.SkillForge.login.services.LoginService;
import br.com.zup.SkillForge.login.services.mappers.LoginMapper;
import br.com.zup.SkillForge.register.models.RegisterUser;
import br.com.zup.SkillForge.register.repositories.RegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
<<<<<<< HEAD
=======
import br.com.zup.SkillForge.infras.security.JwtUtil;
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private RegisterRepository registerRepository;

    @Mock
    private LoginMapper loginMapper;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
<<<<<<< HEAD
    public void testLoginSuccess() {
        String email = "test@example.com";
        String password = "password123";
=======
    public void testLoginWithJwtSuccess() {
        String email = "test@example.com";
        String password = "password123";
        String hashedPassword = PasswordUtil.hashPassword(password);
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO(email, password);

        RegisterUser registeredUser = new RegisterUser();
        registeredUser.setEmail(email);
<<<<<<< HEAD
        registeredUser.setPassword(password);

        LoginUser loginUser = new LoginUser();
        loginUser.setEmail(email);
        loginUser.setPassword(password);

        LoginUserResponseDTO loginUserResponseDTO = new LoginUserResponseDTO();
        loginUserResponseDTO.setEmail(email);

        when(registerRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));
        when(loginMapper.toModel(loginUserRequestDTO)).thenReturn(loginUser);
        when(loginRepository.save(loginUser)).thenReturn(loginUser);
        when(loginMapper.toDto(loginUser)).thenReturn(loginUserResponseDTO);

        LoginUserResponseDTO result = loginService.login(loginUserRequestDTO);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(registerRepository, times(1)).findByEmail(email);
        verify(loginRepository, times(1)).save(loginUser);
    }

    @Test
    public void testLoginEmailNotFound() {
        String email = "notfound@example.com";
        String password = "password123";
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO(email, password);

        when(registerRepository.findByEmail(email)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            loginService.login(loginUserRequestDTO);
        });

        assertEquals("Email not registered", exception.getMessage());
        verify(registerRepository, times(1)).findByEmail(email);
        verify(loginRepository, never()).save(any());
    }

    @Test
    public void testLoginInvalidPassword() {
        String email = "test@example.com";
        String password = "wrongpassword";
=======
        registeredUser.setPassword(hashedPassword);

        when(registerRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));

        String token = loginService.loginWithToken(loginUserRequestDTO);

        assertNotNull(token);
        assertTrue(JwtUtil.validateToken(token, email));
        verify(registerRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testLoginWithJwtInvalidPassword() {
        String email = "test@example.com";
        String password = "wrongpassword";
        String hashedPassword = PasswordUtil.hashPassword("correctpassword");
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO(email, password);

        RegisterUser registeredUser = new RegisterUser();
        registeredUser.setEmail(email);
<<<<<<< HEAD
        registeredUser.setPassword("correctpassword");
=======
        registeredUser.setPassword(hashedPassword);
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6

        when(registerRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
<<<<<<< HEAD
            loginService.login(loginUserRequestDTO);
=======
            loginService.loginWithToken(loginUserRequestDTO);
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6
        });

        assertEquals("Invalid email or password", exception.getMessage());
        verify(registerRepository, times(1)).findByEmail(email);
<<<<<<< HEAD
        verify(loginRepository, never()).save(any());
=======
>>>>>>> 9012963f7a67092b6b281232477fb53be564dce6
    }
}