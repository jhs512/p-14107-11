package com.back.boundedContext.member.useCase;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJoinUseCase {
    private final MemberRepository memberRepository;

    public Member join(String username, String password, String nickname) {
        Member member = new Member(username, password, nickname);

        return memberRepository.save(member);
    }
}
