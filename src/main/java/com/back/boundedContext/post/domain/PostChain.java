package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "POST_POST_CHAIN")
@NoArgsConstructor
@AllArgsConstructor
public class PostChain extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private PostMember author;
    private String title;
    @OneToMany(mappedBy = "chain", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PostChainItem> items = new ArrayList<>();

    public PostChain(PostMember author, String title) {
        this.author = author;
        this.title = title;
    }

    public PostChainItem addItem(Post post) {
        PostChainItem postChainItem = new PostChainItem(this, post, items.size() + 1);

        items.add(postChainItem);

        return postChainItem;
    }
}
