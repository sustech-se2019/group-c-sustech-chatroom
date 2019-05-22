package sim.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "chat_history")
public class ChatHistory {
    @Id
    @Column(name = "msg_id")
    private String msgId;

    @Column(name = "from_id")
    private String fromId;

    @Column(name = "to_id")
    private String toId;

    private String msg;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "sign_flag")
    private Integer signFlag;

    private Integer type;

    /**
     * @return msg_id
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * @param msgId
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * @return from_id
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * @param fromId
     */
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    /**
     * @return to_id
     */
    public String getToId() {
        return toId;
    }

    /**
     * @param toId
     */
    public void setToId(String toId) {
        this.toId = toId;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return send_time
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return sign_flag
     */
    public Integer getSignFlag() {
        return signFlag;
    }

    /**
     * @param signFlag
     */
    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}