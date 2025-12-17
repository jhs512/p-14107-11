package com.back.boundedContext.member.domain;

import com.back.shared.member.domain.BaseMember;
import com.back.shared.member.domain.SourceMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {
    public Member(
            String username,
            String password,
            String nickname
    ) {
        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
    }
}
