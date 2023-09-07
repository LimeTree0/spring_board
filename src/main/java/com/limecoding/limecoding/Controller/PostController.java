package com.limecoding.limecoding.Controller;

import com.limecoding.limecoding.config.auth.LoginUser;
import com.limecoding.limecoding.config.auth.dto.SessionUser;
import com.limecoding.limecoding.dto.PostListResponseDto;
import com.limecoding.limecoding.dto.PostResponseDto;
import com.limecoding.limecoding.dto.PostSaveResponseDto;
import com.limecoding.limecoding.dto.PostUpdateResponseDto;
import com.limecoding.limecoding.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 게시글 목록 보여주기
    @GetMapping("/")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model, @LoginUser SessionUser user) {
        Page<PostListResponseDto> listResponseDtos = postService.paging(pageable);

        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < listResponseDtos.getTotalPages()) ? startPage + blockLimit - 1 : listResponseDtos.getTotalPages();

        model.addAttribute("responseDto", listResponseDtos);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        if(user != null) model.addAttribute("userName", user.getName());

        return "list";
    }
//    @GetMapping("/")
//    public String post(Model model) {
//        List<PostListResponseDto> post = postService.findAll();
//        model.addAttribute("post", post);
//
//        return "list";
//    }

    // 게시글 작성 페이지로 이동
    @GetMapping("/save")
    public String postWrite() {
        return "save";
    }

    // 게시글 저장
    @PostMapping("/save")
    @ResponseBody
    public Long postSave(@RequestBody PostSaveResponseDto responseDto) {
        return postService.save(responseDto);
    }

    //게시글 수정 페이지 이동
    @GetMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model, @PageableDefault(page = 1) Pageable pageable) {
        PostResponseDto responseDto = postService.findById(id);
        model.addAttribute("responseDto", responseDto);
        model.addAttribute("page", pageable.getPageNumber());

        return "update";
    }

    //게시글 수정
    @PutMapping("/update/{id}")
    @ResponseBody
    public Long postUpdate(@PathVariable Long id, @RequestBody PostUpdateResponseDto responseDto) {

        return postService.update(id, responseDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Long postDelete(@PathVariable Long id) {
        postService.deleteById(id);

        return id;
    }


}
