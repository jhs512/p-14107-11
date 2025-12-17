package com.back.shared.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@MappedSuperclass
@Getter
@Setter(value = PROTECTED)
public abstract class BaseMember {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
}
