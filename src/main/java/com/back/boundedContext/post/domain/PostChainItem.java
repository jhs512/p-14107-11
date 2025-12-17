package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "POST_POST_CHAIN_ITEM")
@NoArgsConstructor
@AllArgsConstructor
public class PostChainItem extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private PostChain chain;
    @ManyToOne(fetch = LAZY)
    private Post post;
    private int no;
}
