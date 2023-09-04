package com.limecoding.limecoding.dto;

import com.limecoding.limecoding.domain.Post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private int hits;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.contents = post.getContents();
        this.hits = post.getHits();
    }
}
