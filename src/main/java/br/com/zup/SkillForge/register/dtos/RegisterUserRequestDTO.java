package br.com.zup.SkillForge.register.dtos;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequestDTO {
    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @Size(min = 8, message = "Password should have at least 8 characters.")
    private String password;

    @Size(min = 8, message = "Password confirmation should have at least 8 characters.")
    private String confirmPassword;

    @AssertTrue(message = "Passwords do not match.")
    public boolean isPasswordsEqual() {
        return password != null && password.equals(confirmPassword);
    }
}