package com.limecoding.limecoding.service;

import com.limecoding.limecoding.domain.Post.Post;
import com.limecoding.limecoding.domain.Post.PostRepository;
import com.limecoding.limecoding.dto.PostListResponseDto;
import com.limecoding.limecoding.dto.PostResponseDto;
import com.limecoding.limecoding.dto.PostSaveResponseDto;
import com.limecoding.limecoding.dto.PostUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public List<PostListResponseDto> findAll() {
        return postRepository.findAll()
                .stream().map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        return new PostResponseDto(post);
    }

    @Transactional
    public Long save(PostSaveResponseDto responseDto) {
        return postRepository.save(responseDto.toEntity()).getId();

    }

    @Transactional
    public Long update(Long id, PostUpdateResponseDto responseDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        post.update(responseDto);
        return id;
    }

    @Transactional
    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postRepository.delete(post);
    }

    public Page<PostListResponseDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;

        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<PostListResponseDto> listResponseDtos = postPage.map(post -> new PostListResponseDto(post));

        return listResponseDtos;
    }
}
