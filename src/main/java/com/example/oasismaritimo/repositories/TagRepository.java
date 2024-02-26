package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByNameAndUser(String name, User user);
}
