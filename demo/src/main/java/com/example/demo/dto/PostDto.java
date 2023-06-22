package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PostDto {
    @NonNull
    private String message;
    @NonNull
    private String username;
}
