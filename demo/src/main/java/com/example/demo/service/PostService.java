package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(String message, String username){
        User user = userRepository.findByUsername(username);
        Post post = Post.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(message)
                .build();

        user.addPost(post);
        postRepository.save(post);
        return post;
    }
    public void deletePost(Integer id){
        postRepository.deleteById(id);
    }
    public List<Post> allPost(){
        return postRepository.findAll();
    }
}