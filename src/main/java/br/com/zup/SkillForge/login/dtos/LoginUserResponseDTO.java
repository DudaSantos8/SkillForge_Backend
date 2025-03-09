package br.com.zup.SkillForge.login.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResponseDTO {

    private Long id;
    private String email;
    private String password;

    private LoginUserResponseDTO(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginUserResponseDTO build() {
            return new LoginUserResponseDTO(this);
        }
    }
}