package com.limecoding.limecoding.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateResponseDto {

    private String title;
    private String contents;

    @Builder
    public PostUpdateResponseDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
