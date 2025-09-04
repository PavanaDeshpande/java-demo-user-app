package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);
}