package br.com.zup.SkillForge.history.repositories;

import br.com.zup.SkillForge.history.models.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {
}