package br.com.zup.SkillForge.register.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponseDTO {

    private Long id;
    private String email;

    private RegisterUserResponseDTO(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
    }

    public static class Builder {
        private Long id;
        private String email;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public RegisterUserResponseDTO build() {
            return new RegisterUserResponseDTO(this);
        }
    }
}