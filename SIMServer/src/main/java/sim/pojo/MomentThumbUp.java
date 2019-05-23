package sim.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "moment_thumb_up")
public class MomentThumbUp {
    @Id
    @Column(name = "moment_id")
    private String momentId;

    @Id
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