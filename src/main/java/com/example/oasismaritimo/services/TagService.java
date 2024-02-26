package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.exceptions.InvalidRequestException;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(UUID id) {
        return tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag"));
    }

    public Tag createTag(Tag tag, User user) {
        if (tag.getName() == null || tag.getName().trim().isEmpty()) {
            throw new InvalidRequestException("Insira o nome da tag.");
        }
        List<Tag> existingTags = tagRepository.findByNameAndUser(tag.getName(), user);
        if (!existingTags.isEmpty()) {
            throw new InvalidRequestException("Essa tag já existe.");
        }
        tag.setUser(user);
        return tagRepository.save(tag);
    }

    public void deleteTag(UUID id) {
        Optional.ofNullable(getTagById(id)).orElseThrow(() -> new NotFoundException("Tag"));
        tagRepository.deleteById(id);
    }
}
