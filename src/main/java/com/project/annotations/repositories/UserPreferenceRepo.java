package com.project.annotations.repositories;

import com.project.annotations.entities.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepo extends JpaRepository<UserPreferences , Long> {
}
