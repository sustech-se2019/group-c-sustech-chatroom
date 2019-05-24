package sim.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "moment_content")
public class MomentContentVO {
    @Id
    @Column(name = "moment_id")
    private String momentId;

    @Column(name = "sender_id")
    private String senderId;

    private String content;

    @Column(name = "send_time")
    private Date sendTime;

    private List<String> thumbUpList;

    public List<String> getThumbUpList() {
        return thumbUpList;
    }

    public void setThumbUpList(List<String> thumbUpList) {
        this.thumbUpList = thumbUpList;
    }

    /**
     * @return moment_id
     */
    public String getMomentId() {
        return momentId;
    }

    /**
     * @param momentId
     */
    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    /**
     * @return sender_id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return sendTime
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
}