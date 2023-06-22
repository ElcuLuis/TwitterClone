package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String parola;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> posts;

    public void addPost(Post post){
        if (post != null){
            if (posts == null){
                posts = new HashSet<>();
            }
            post.setUser(this);
            posts.add(post);
        } else {
            System.out.println("post is null");
        }
    }

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Follow> followers;
    public void addFollower(Follow follow){
        if (follow != null){
            if (followers == null){
                followers = new HashSet<>();
            }
            follow.setFollower(this);
            followers.add(follow);
        } else {
            System.out.println("follow is null");
        }
    }

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Follow> followeds;
    public void addFollowed(Follow follow) {
        if (follow != null) {
            if (followeds == null) {
                followeds = new HashSet<>();
            }
            follow.setFollowed(this);
            followeds.add(follow);
        } else {
            System.out.println("follow is null");
        }
    }
}

