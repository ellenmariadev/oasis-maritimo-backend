package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.facade.TagFacade;
import com.example.oasismaritimo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagFacade tagFacade;
    private final UserService userService;

    @Autowired
    public TagController(TagFacade tagFacade, UserService userService) {
        this.tagFacade = tagFacade;
        this.userService = userService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagFacade.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") UUID id) {
        return tagFacade.getTagById(id);
    }

    @PostMapping
    public Tag createTag(@Valid @RequestBody Tag tag, Principal principal) {
        UserDetails userDetails = userService.findByLogin(principal.getName());
        User user = (User) userDetails;
        return tagFacade.createTag(tag, user);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable("id") UUID id) {
        tagFacade.deleteTag(id);
    }
}