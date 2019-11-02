package cn.homjie.wechat.niu.chat;

import java.util.concurrent.TimeUnit;

import cn.homjie.wechat.niu.chat.ai.ChatClient;
import cn.homjie.wechat.niu.chat.ai.http.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class NiuChatApplicationTests {

    @Autowired
    private ChatClient chatClient;

    @Test
    void chatGet() {
        String session = String.valueOf(TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis()));
        ChatResponse response = chatClient.doGet(session, "想你想的睡不着").block();
        log.info("response: {}", response);
    }
}
