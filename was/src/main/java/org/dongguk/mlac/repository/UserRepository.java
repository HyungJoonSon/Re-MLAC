package org.dongguk.mlac.repository;

import org.dongguk.mlac.domain.User;
import org.dongguk.mlac.dto.type.EArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndArea(String username, EArea area);
}
