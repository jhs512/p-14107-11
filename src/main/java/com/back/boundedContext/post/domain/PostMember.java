package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
    public PostMember(
            int id,
            LocalDateTime createDate,
            LocalDateTime modifyDate,
            String username,
            String password,
            String nickname
    ) {
        this.setId(id);
        this.setCreateDate(createDate);
        this.setModifyDate(modifyDate);
        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
    }

    public PostMember(MemberDto memberDto) {
        this(
                memberDto.id(),
                memberDto.createDate(),
                memberDto.modifyDate(),
                memberDto.username(),
                memberDto.password(),
                memberDto.nickname()
        );
    }
}
