package sim.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "moment_comments")
public class MomentComments {
    @Id
    @Column(name = "moment_id")
    private String momentId;

    private String comment;

    @Column(name = "from_id")
    private String fromId;

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
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
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
}