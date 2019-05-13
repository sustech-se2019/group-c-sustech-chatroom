package sim.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @Column(name = "from_id")
    private String fromId;

    @Id
    @Column(name = "to_id")
    private String toId;

    private String msg;

    private Date date;

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
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}