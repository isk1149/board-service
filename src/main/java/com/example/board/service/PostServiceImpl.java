package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.dto.PostInsertDto;
import com.example.board.dto.PostUpdateDto;
import com.example.board.dto.RecommendationDto;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.PostEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public PostEntity post(String boardId, PostInsertDto postInsertDto) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));

        PostEntity entity = PostEntity.builder()
                .board(boardEntity)
                .writer(postInsertDto.getWriter())
                .subject(postInsertDto.getSubject())
                .content(postInsertDto.getContent())
                .viewCount(0L)
                .recommendationCount(0L)
                .creator(postInsertDto.getWriter())
                .build();

        PostEntity savedEntity = postRepository.save(entity);
        return savedEntity;
    }


    public Page<PostEntity> getPostsByBoard(String boardId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        Page<PostEntity> pageEntity = postRepository.findPostsByPageable(boardId, pageRequest);
        return pageEntity;
    }

    /**
     * Page<Member> list(Pageable pageable)
     * /members?page=0&size=3&sort=createdDateTime,desc
     * /members?page=0&size=3&sort=id,desc&sort=username,desc
     * page: 현재 페이지, 0부터 시작한다.
     * size: 한 페이지에 노출할 데이터 건수
     */
    @Override
    public Page<PostEntity> getPostsByBoardUsingPageable(String boardId, Pageable pageable) {
        Page<PostEntity> pageEntity = postRepository.findPostsByPageable(boardId, pageable);
        return pageEntity;
    }

    @Override
    public Page<PostEntity> getPostsByBoardUsingQueryDslPageable(String boardId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        Page<PostEntity> pageEntity = postRepository.findPostsByBoardUsingQueryDsl(boardId, pageRequest);
        return pageEntity;
    }

    @Override
    public List<PostEntity> getPostsByHome(String boardId) {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        //SpringDataJpa
        //List<PostEntity> entities = postRepository.findPostsByHome(boardId, pageRequest);
        //QueryDsl
        List<PostEntity> entities = postRepository.findPostsByHomeUsingQueryDsl(boardId);
        return entities;
    }

    @Transactional
    @Override
    public PostDto getPost(String postId) {
//        PostEntity entity = postRepository.findById(postId)
//                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
        PostEntity entity = postRepository.findPostUsingQueryDsl1(postId);
        entity.increaseViewCount();
        return new PostDto(entity);
    }

//    /**
//     * 조회수 증가가 되지 않는 Post 조회
//     * @param postId
//     * @return PostEntity
//     */
//    @Override
//    public PostEntity getPostForComment(String postId) {
//        PostEntity entity = postRepository.findById(postId)
//                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
//        return entity;
//    }

    @Override
    public List<PostEntity> getPostsByWriter(String writer) {
        List<PostEntity> entities = postRepository.findByWriterOrderByIdDesc(writer);
        return entities;
    }

    @Transactional
    @Override
    public PostEntity update(String postId, PostUpdateDto postUpdateDto, String userId) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        entity.updatePost(postUpdateDto);
        entity.updateUpdater(userId);
        return entity;
    }

    @Transactional
    @Override
    public PostEntity recommend(String postId, RecommendationDto recommendationDto) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
        if (recommendationDto.getValue() > 0)
            entity.increaseRecommendationCount();
        else
            entity.decreaseRecommendationCount();

        return entity;
    }

    @Transactional
    @Override
    public void delete(String postId, String userId) {
        PostEntity entity = postRepository.findById(postId)
                                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

//        if (!entity.getCreatorId().equals(userId)) {
//            throw new UnauthorizedException("User is not the post owner.");
//        }

        postRepository.delete(entity);
    }

}
