package cn.homjie.wechat.niu.chat;

import cn.homjie.wechat.niu.chat.ai.http.ChatRequest;
import org.junit.jupiter.api.Test;

/**
 * @author jiehong.jh
 * @date 2019/11/2
 */
public class ChatRequestTest {

    @Test
    void doSign() {
        ChatRequest request = new ChatRequest();
        request.setAppKey("a95eceb1ac8c24ee28b70f7dbba912bf");
        request.put("app_id", "10000");
        request.put("time_stamp", "1493449657");
        request.put("nonce_str", "20e3408a79");
        request.put("key1", "腾讯AI开放平台");
        request.put("key2", "示例仅供参考");
        request.doSignUrl();
        System.out.println("BE918C28827E0783D1E5F8E6D7C37A61".equals(request.getSign()));
    }
}
