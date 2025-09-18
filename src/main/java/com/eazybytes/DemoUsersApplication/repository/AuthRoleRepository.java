package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}