package com.back.boundedContext.member.facade;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.useCase.MemberJoinUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberJoinUseCase memberJoinUseCase;

    @Transactional
    public Member join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }
}
