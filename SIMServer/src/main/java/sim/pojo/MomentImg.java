package sim.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "moment_img")
public class MomentImg {
    @Id
    @Column(name = "moment_id")
    private String momentId;

    @Column(name = "img_path")
    private String imgPath;

    private Integer rank;

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
     * @return img_path
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }
}