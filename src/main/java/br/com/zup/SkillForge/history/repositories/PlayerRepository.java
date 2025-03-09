package br.com.zup.SkillForge.history.repositories;

import br.com.zup.SkillForge.history.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}