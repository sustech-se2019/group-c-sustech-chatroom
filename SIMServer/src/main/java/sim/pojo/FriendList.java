package sim.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "friend_list")
public class FriendList {
    @Id
    @Column(name = "owner_id")
    private String ownerId;

    @Id
    @Column(name = "friend_id")
    private String friendId;

    private String tag;

    /**
     * @return owner_id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return friend_id
     */
    public String getFriendId() {
        return friendId;
    }

    /**
     * @param friendId
     */
    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}