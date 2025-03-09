package br.com.zup.SkillForge.history.models;

import br.com.zup.SkillForge.login.models.LoginUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Player extends LoginUser {

    @NotNull(message = "Ranking cannot be null.")
    private int ranking;
    @NotNull(message = "Score cannot be null.")
    private int score;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameHistory> gameHistory;

    public Player(String email, String password, int ranking, int score) {
        super(email, password);
        this.ranking = ranking;
        this.score = score;
    }
}

