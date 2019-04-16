package sim.pojo;

import javax.persistence.Id;

public class Users {
    @Id
    private String id;

    private String username;

    private String password;

    private String nickname;

    private Double gpa;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return gpa
     */
    public Double getGpa() {
        return gpa;
    }

    /**
     * @param gpa
     */
    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}