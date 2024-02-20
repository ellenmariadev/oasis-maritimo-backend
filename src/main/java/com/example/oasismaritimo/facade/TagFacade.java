package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TagFacade {
    private final TagService tagService;

    @Autowired
    public TagFacade(TagService tagService) {
        this.tagService = tagService;
    }

    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    public Tag getTagById(UUID id) {
        return tagService.getTagById(id);
    }

    public Tag createTag(Tag tag) {
        return tagService.createTag(tag);
    }

    public void deleteTag(UUID id) {
        tagService.deleteTag(id);
    }
}