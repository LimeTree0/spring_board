package com.limecoding.limecoding.dto;

import com.limecoding.limecoding.domain.Post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private int hits;
    private LocalDateTime createdTime;

    @Builder
    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.hits = post.getHits();
        this.createdTime = post.getCreatedTime();
    }
}
