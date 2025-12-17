package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketFacade;
import com.back.shared.member.event.MemberJoinEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MarketEventListener {
    private final MarketFacade marketFacade;

    public MarketEventListener(
            MarketFacade marketFacade
    ) {
        this.marketFacade = marketFacade;
    }

    @KafkaListener(topics = "MemberJoinEvent", groupId = "market")
    public void handleEvent(MemberJoinEvent event) {
        marketFacade.syncMember(event.data());
    }
}
