package cn.homjie.wechat.niu.chat.ai;

import java.net.URI;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.homjie.wechat.niu.chat.ai.http.ChatRequest;
import cn.homjie.wechat.niu.chat.ai.http.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author jiehong.jh
 * @date 2019/11/2
 */
@Component
@Slf4j
public class ChatClient {

    @Autowired
    private ChatProperties properties;
    @Autowired
    private WebClient webClient;

    public Mono<ChatResponse> doGet(String session, String question) {
        Objects.requireNonNull(session);
        Objects.requireNonNull(question);
        ChatRequest request = new ChatRequest();
        request.setAppId(properties.getAppId());
        request.setAppKey(properties.getAppKey());
        request.setTimeStamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        request.setNonceStr(RandomStringUtils.randomAlphanumeric(32));
        request.setSession(session);
        request.setQuestion(question);
        return doGet(request);
    }

    public Mono<ChatResponse> doGet(ChatRequest request) {
        return webClient.get()
            .uri(URI.create(request.doSignUrl()))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ChatResponse.class);
    }
}
