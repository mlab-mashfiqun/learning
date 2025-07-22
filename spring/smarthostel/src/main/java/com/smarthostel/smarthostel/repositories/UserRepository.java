package com.smarthostel.smarthostel.repositories;

import com.smarthostel.smarthostel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
