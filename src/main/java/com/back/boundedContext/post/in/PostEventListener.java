package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.member.event.MemberJoinEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PostEventListener {
    private final PostFacade postFacade;

    public PostEventListener(
            PostFacade postFacade
    ) {
        this.postFacade = postFacade;
    }

    @KafkaListener(topics = "MemberJoinEvent", groupId = "post")
    public void handleEvent(MemberJoinEvent event) {
        postFacade.syncMember(event.data());
    }
}
