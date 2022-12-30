package domain;

public class Users {
    private String userId;
    private String userPass;
    private String userName;
    private String userDesignation;
    private String userEmail;

    public Users(String userId, String userPass, String userName, String userDesignation, String userEmail) {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userDesignation = userDesignation;
        this.userEmail = userEmail;
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
