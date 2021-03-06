package com.codepark.webservice.service.posts;

import com.codepark.webservice.domain.posts.Posts;
import com.codepark.webservice.domain.posts.PostsRepository;
import com.codepark.webservice.web.dto.PostsResponseDto;
import com.codepark.webservice.web.dto.PostsSaveRequestDto;
import com.codepark.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        getCertainPost(id).update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto generateResponseByPost(Long id) {
        return new PostsResponseDto(getCertainPost(id));
    }

    public Posts getCertainPost(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no post. id = "+ id));
    }
}
