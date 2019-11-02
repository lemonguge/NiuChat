package cn.homjie.wechat.niu.chat;

import cn.homjie.wechat.niu.chat.ai.ChatProperties;
import io.netty.channel.ChannelOption;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * @author jiehong.jh
 * @date 2019/11/2
 */
@Configuration
@EnableConfigurationProperties(ChatProperties.class)
public class NiuChatConfiguration {

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
            .tcpConfiguration(client ->
                client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000));
    }
}
