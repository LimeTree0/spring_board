package com.limecoding.limecoding.dto;

import com.limecoding.limecoding.domain.Post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostSaveResponseDto {
    private String title;
    private String postPass;
    private String author;
    private String contents;

    @Builder
    public PostSaveResponseDto(String title, String postPass, String author, String contents) {
        this.title = title;
        this.postPass = postPass;
        this.author = author;
        this.contents = contents;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .postPass(postPass)
                .author(author)
                .contents(contents)
                .hits(0)
                .build();
    }
}
