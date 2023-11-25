package model;

import java.util.Objects;

public class User {
    private String userName;
    private String code;

    public User(String userName, String code){
        this.userName = userName;
        this.code = code;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserCode() {
        return code;
    }

    public void setUserCode(String code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return "User{" +
                "userName = " + userName + "\'" +
                "code = " + code + "\'" +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.userName, user.userName) && Objects.equals(this.code, user.code);
    }

    @Override
    public int hashCode() {return Objects.hash(getUserName(), getUserCode());}
}
