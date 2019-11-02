package cn.homjie.wechat.niu.chat.ai.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jiehong.jh
 * @date 2019/11/1
 */
@Getter
@Setter
@ToString
public class ChatResponse {

    private Integer ret;
    private String msg;
    private Data data;

    @Getter
    @Setter
    @ToString
    public static class Data {
        private String session;
        private String answer;
    }
}
