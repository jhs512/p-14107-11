package com.back.boundedContext.post.out;

import com.back.boundedContext.post.domain.PostChain;
import com.back.boundedContext.post.domain.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostChainRepository extends JpaRepository<PostChain, Integer> {
}
