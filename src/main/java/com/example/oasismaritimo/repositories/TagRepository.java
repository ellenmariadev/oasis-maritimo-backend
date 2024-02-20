package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {

}
