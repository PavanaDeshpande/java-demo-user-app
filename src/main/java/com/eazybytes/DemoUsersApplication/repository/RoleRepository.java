package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
