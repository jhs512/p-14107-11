package com.back.global.jpa.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@MappedSuperclass
@Getter
@Setter(value = PROTECTED)
public abstract class BaseEntity {
    public abstract int getId();

    public String getModelTypeCode() {
        return getClass().getSimpleName();
    }
}