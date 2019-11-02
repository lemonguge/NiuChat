package cn.homjie.wechat.niu.chat.ai;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiehong.jh
 * @date 2019/11/2
 */
@ConfigurationProperties(prefix = "wechat.ai")
@Getter
@Setter
public class ChatProperties {
    private Integer appId;
    private String appKey;
}
