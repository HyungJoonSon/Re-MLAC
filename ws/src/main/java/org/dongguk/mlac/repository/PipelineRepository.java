package org.dongguk.mlac.repository;

import org.dongguk.mlac.domain.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRegexRepository extends JpaRepository<Pipeline, Long> {
}
