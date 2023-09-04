package com.limecoding.limecoding.domain.Post;

import com.limecoding.limecoding.domain.BaseTimeEntity;
import com.limecoding.limecoding.dto.PostUpdateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    private String postPass;

    private String author;

    @Column(columnDefinition = "TEXT", length = 500)
    private String contents;

    @Column
    private int hits;

    @Builder
    public Post(String title, String postPass, String author, String contents, int hits) {
        this.title = title;
        this.postPass = postPass;
        this.author = author;
        this.contents = contents;
        this.hits = hits;
    }

    public void update(PostUpdateResponseDto responseDto) {
        this.title = responseDto.getTitle();
        this.contents = responseDto.getContents();
    }
}
