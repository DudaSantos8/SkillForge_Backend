package br.com.zup.SkillForge.login.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDTO {
    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    private String password;

    private LoginUserRequestDTO(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginUserRequestDTO build() {
            return new LoginUserRequestDTO(this);
        }
    }
}