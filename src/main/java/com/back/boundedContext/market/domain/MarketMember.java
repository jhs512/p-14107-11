package com.back.boundedContext.market.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "MARKET_MEMBER")
@NoArgsConstructor
@Getter
public class MarketMember extends ReplicaMember {
    public MarketMember(
            int id,
            LocalDateTime createDate,
            LocalDateTime modifyDate,
            String username,
            String nickname
    ) {
        this.setId(id);
        this.setCreateDate(createDate);
        this.setModifyDate(modifyDate);
        this.setUsername(username);
        this.setNickname(nickname);
    }

    public MarketMember(MemberDto memberDto) {
        this(
                memberDto.id(),
                memberDto.createDate(),
                memberDto.modifyDate(),
                memberDto.username(),
                memberDto.nickname()
        );
    }
}
