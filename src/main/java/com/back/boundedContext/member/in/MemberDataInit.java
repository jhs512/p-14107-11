package com.back.boundedContext.member.in;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class MemberDataInit {
    private final MemberDataInit self;
    private final MemberFacade memberFacade;

    public MemberDataInit(
            @Lazy MemberDataInit self,
            MemberFacade memberFacade
    ) {
        this.self = self;
        this.memberFacade = memberFacade;
    }

    @Bean
    @Order(1)
    public ApplicationRunner memberDataInitApplicationRunner() {
        return args -> self.work();
    }

    @SchedulerLock(name = "skip-overlap:init:member:data")
    public void work() {
        self.makeBaseMembers();
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberFacade.count() > 0) return;

        Member systemMember = memberFacade.join("system", "1234", "시스템");
        Member holdingMember = memberFacade.join("holding", "1234", "홀딩");
        Member adminMember = memberFacade.join("admin", "1234", "관리자");
        Member user1Member = memberFacade.join("user1", "1234", "유저1");
        Member user2Member = memberFacade.join("user2", "1234", "유저2");
        Member user3Member = memberFacade.join("user3", "1234", "유저3");
    }
}
