package dev.dex.oauth2authorities.repository;

import dev.dex.oauth2authorities.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
