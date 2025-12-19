package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostChain;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostChainRepository;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostMemberRepository postMemberRepository;
    private final PostChainRepository postChainRepository;

    public long count() {
        return postRepository.count();
    }

    public long postMembersCount() {
        return postMemberRepository.count();
    }

    public long postChainsCount() {
        return postChainRepository.count();
    }

    public void syncMember(MemberDto memberDto) {
        PostMember postMember = new PostMember(
                memberDto
        );

        postMemberRepository.save(postMember);
    }

    public Optional<PostMember> findMemberByUsername(String username) {
        return postMemberRepository.findByUsername(username);
    }

    public Post write(PostMember author, String title, String content) {
        return postRepository.save(
                new Post(
                        author,
                        title,
                        content
                )
        );
    }

    public Optional<Post> findPostById(int id) {
        return postRepository.findById(id);
    }

    public PostChain createChain(PostMember author, String title) {
        PostChain postChain = postChainRepository.save(new PostChain(author, title));

        return postChain;
    }

    public List<Post> getPosts() {
        return postRepository.findByOrderByIdDesc();
    }
}
