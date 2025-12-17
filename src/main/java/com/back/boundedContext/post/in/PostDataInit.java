package com.back.boundedContext.post.in;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostChain;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.app.PostFacade;
import com.back.standard.util.Util;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class PostDataInit {
    private final PostDataInit self;
    private final PostFacade postFacade;

    public PostDataInit(
            @Lazy PostDataInit self,
            PostFacade postFacade
    ) {
        this.self = self;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postDataInitApplicationRunner() {
        return args -> {
            waitUntilBasePostMembersSynced();
            self.makeBasePosts();
            self.makeBasePostChains();
        };
    }

    private void waitUntilBasePostMembersSynced() {
        Util
                .thread
                .waitUntil(
                        () -> postFacade.postMembersCount() >= 6
                );
    }

    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        PostMember user1PostMember = postFacade.findMemberByUsername("user1").get();
        PostMember user2PostMember = postFacade.findMemberByUsername("user2").get();
        PostMember user3PostMember = postFacade.findMemberByUsername("user3").get();

        Post post1 = postFacade.write(user1PostMember, "제목1", "내용1");
        Post post2 = postFacade.write(user1PostMember, "제목2", "내용3");
        Post post3 = postFacade.write(user1PostMember, "제목3", "내용3");

        Post post4 = postFacade.write(user2PostMember, "제목4", "내용4");
        Post post5 = postFacade.write(user3PostMember, "제목5", "내용5");

        Post post6 = postFacade.write(user3PostMember, "제목6", "내용6");
    }

    @Transactional
    public void makeBasePostChains() {
        if (postFacade.postChainsCount() > 0) return;

        PostMember user1PostMember = postFacade.findMemberByUsername("user1").get();
        PostMember user2PostMember = postFacade.findMemberByUsername("user2").get();
        PostMember user3PostMember = postFacade.findMemberByUsername("user3").get();

        Post post1 = postFacade.findPostById(1).get();
        Post post2 = postFacade.findPostById(2).get();
        Post post3 = postFacade.findPostById(3).get();

        Post post4 = postFacade.findPostById(4).get();
        Post post5 = postFacade.findPostById(5).get();

        Post post6 = postFacade.findPostById(6).get();

        PostChain postChain1 = postFacade.createChain(user1PostMember, "글 시리즈 1");
        postChain1.addItem(post1);
        postChain1.addItem(post2);
        postChain1.addItem(post3);

        PostChain postChain2 = postFacade.createChain(user2PostMember, "글 시리즈 2");
        postChain2.addItem(post4);
        postChain2.addItem(post5);

        PostChain postChain3 = postFacade.createChain(user3PostMember, "글 시리즈 3");
        postChain3.addItem(post6);
    }
}
