package edu.nju.whattoeat.vo;

/**
 * Created by WillLester on 2017/3/22.
 * 发送的消息，可能是语音
 */

public class Message {
    private String content;
    private boolean isSend;

    public Message(String content, boolean isSend) {
        this.content = content;
        this.isSend = isSend;
    }

    public String getContent() {
        return content;
    }

    public boolean isSend() {
        return isSend;
    }
}
