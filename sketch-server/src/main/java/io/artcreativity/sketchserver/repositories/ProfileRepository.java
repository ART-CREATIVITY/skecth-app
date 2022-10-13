package io.artcreativity.sketchserver.repositories;

import io.artcreativity.sketchserver.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
