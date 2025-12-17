package com.back.boundedContext.member.facade;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MemberFacadeTest {
    @Autowired
    private MemberFacade memberFacade;

    @Test
    @DisplayName("회원가입을 하면 번호(id)와 생성날짜(createDate)와 최근수정날짜(modifyDate)가 자동으로 생성됩니다.")
    public void t001() {
        Member user1Member = memberFacade.join("t001-user1", "1234", "t001-유저11");

        assertThat(user1Member.getId()).isGreaterThan(0);
        assertThat(user1Member.getCreateDate()).isNotNull();
        assertThat(user1Member.getModifyDate()).isNotNull();
    }

    @Test
    @DisplayName("6명의 회원은 항상 존재해야 합니다.")
    public void t002() {
        Member systemMember = memberFacade.findByUsername("system").get();
        assertThat(systemMember.getId()).isEqualTo(1);

        Member holdingMember = memberFacade.findByUsername("holding").get();
        assertThat(holdingMember.getId()).isEqualTo(2);

        Member adminMember = memberFacade.findByUsername("admin").get();
        assertThat(adminMember.getId()).isEqualTo(3);

        Member user1Member = memberFacade.findByUsername("user1").get();
        assertThat(user1Member.getId()).isEqualTo(4);

        Member user2Member = memberFacade.findByUsername("user2").get();
        assertThat(user2Member.getId()).isEqualTo(5);

        Member user3Member = memberFacade.findByUsername("user3").get();
        assertThat(user3Member.getId()).isEqualTo(6);
    }
}
