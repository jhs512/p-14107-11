package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketMemberRepository marketMemberRepository;

    public long marketMembersCount() {
        return marketMemberRepository.count();
    }

    public void syncMember(MemberDto memberDto) {
        MarketMember marketMember = new MarketMember(
                memberDto
        );

        marketMemberRepository.save(marketMember);
    }

    public Optional<MarketMember> findMemberByUsername(String username) {
        return marketMemberRepository.findByUsername(username);
    }
}
