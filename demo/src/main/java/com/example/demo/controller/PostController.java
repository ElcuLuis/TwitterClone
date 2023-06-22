package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(  postService.createPost(postDto.getMessage(), postDto.getUsername()), HttpStatus.CREATED );
    }

    @GetMapping(value = "/allpost")
    public List<Post> allPost(){
        return postService.allPost();
    }
    @DeleteMapping(value = "/post")
    public void deletePost(@RequestParam Integer id){
        postService.deletePost(id);
    }

}
