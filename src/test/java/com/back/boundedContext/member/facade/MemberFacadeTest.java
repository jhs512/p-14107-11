package com.back.boundedContext.member.facade;

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
}
