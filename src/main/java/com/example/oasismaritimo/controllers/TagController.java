package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.facade.TagFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagFacade tagFacade;

    @Autowired
    public TagController(TagFacade tagFacade) {
        this.tagFacade = tagFacade;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagFacade.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable UUID id) {
        return tagFacade.getTagById(id);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagFacade.createTag(tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable UUID id) {
        tagFacade.deleteTag(id);
    }
}