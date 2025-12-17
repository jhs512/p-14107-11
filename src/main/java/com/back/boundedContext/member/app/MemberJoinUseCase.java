package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.kafka.KafkaEventPublisher;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.member.event.MemberJoinEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJoinUseCase {
    private final KafkaEventPublisher kafkaEventPublisher;
    private final MemberRepository memberRepository;

    public Member join(String username, String password, String nickname) {
        Member member = memberRepository.save(
                new Member(username, password, nickname)
        );

        kafkaEventPublisher.send(new MemberJoinEvent(new MemberDto(member)));

        return member;
    }
}
