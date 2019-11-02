package cn.homjie.wechat.niu.chat.ai.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author jiehong.jh
 * @date 2019/11/1
 */
@Getter
@ToString
public class ChatRequest extends TreeMap<String, String> {

    private static final String URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

    private Integer appId;
    private String appKey;
    private Long timeStamp;
    private String nonceStr;
    private String session;
    private String question;
    private String sign;

    public String doSignUrl() {
        List<String> list = new ArrayList<>(size());
        for (Entry<String, String> entry : entrySet()) {
            String entryKey = entry.getKey();
            if (!"app_key".equals(entryKey)) {
                String entryValue = encode(entry.getValue());
                list.add(entryKey + "=" + entryValue);
            }
        }
        String origin = String.join("&", list) + "&app_key=" + appKey;
        setSign(Hex.encodeHexString(DigestUtils.md5(origin), false));
        return URL + "?" + origin + "&sign=" + sign;
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAppId(Integer appId) {
        Objects.requireNonNull(appId);
        this.appId = appId;
        put("app_id", appId.toString());
    }

    public void setAppKey(String appKey) {
        Objects.requireNonNull(appKey);
        this.appKey = appKey;
        put("app_key", appKey);
    }

    public void setTimeStamp(Long timeStamp) {
        Objects.requireNonNull(timeStamp);
        this.timeStamp = timeStamp;
        put("time_stamp", timeStamp.toString());
    }

    public void setNonceStr(String nonceStr) {
        Objects.requireNonNull(nonceStr);
        this.nonceStr = nonceStr;
        put("nonce_str", nonceStr);
    }

    public void setSession(String session) {
        Objects.requireNonNull(session);
        this.session = session;
        put("session", session);
    }

    public void setQuestion(String question) {
        Objects.requireNonNull(question);
        this.question = question;
        put("question", question);
    }

    public void setSign(String sign) {
        Objects.requireNonNull(sign);
        this.sign = sign;
        put("sign", sign);
    }
}
