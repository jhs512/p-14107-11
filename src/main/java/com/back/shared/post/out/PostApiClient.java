package com.back.shared.post.out;

import com.back.global.app.AppConfig;
import com.back.shared.post.dto.PostDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class PostApiClient {
    private static final RestClient restClient = RestClient.builder()
            .baseUrl(AppConfig.getSiteBackUrl() + "/post/api/v1")
            .build();

    public static List<PostDto> getItems() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
