package model;

import java.util.Objects;

public class User {
    private String userName;

    public User(String userName){
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString(){
        return "User{" +
                "userName = " + userName + "\'" +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.userName, user.userName);
    }

    @Override
    public int hashCode() {return Objects.hash(getUserName());}
}
