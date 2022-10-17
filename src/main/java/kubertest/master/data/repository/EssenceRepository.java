package kubertest.master.data.repository;

import kubertest.master.data.entity.Essence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EssenceRepository extends JpaRepository<Essence, UUID> {

    boolean existsByEmail(String email);
 }
