package com.limecoding.limecoding.service;

import com.limecoding.limecoding.domain.Post.Post;
import com.limecoding.limecoding.domain.Post.PostRepository;
import com.limecoding.limecoding.dto.PostSaveResponseDto;
import com.limecoding.limecoding.dto.PostSaveResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void clear() {
        postRepository.deleteAll();
    }

    @Test
    public void Post저장_테스트() {
        //given
        String title = "title";
        String pass = "1234";
        String author = "author";
        String content = "content";
        PostSaveResponseDto responseDto = PostSaveResponseDto
                .builder()
                .title(title)
                .postPass(pass)
                .author(author)
                .contents(content)
                .build();


        // when
        postService.save(responseDto);

        //then
        List<Post> postList = postRepository.findAll();
        Post post = postList.get(0);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getAuthor()).isEqualTo(author);
        assertThat(post.getContents()).isEqualTo(content);
    }

}