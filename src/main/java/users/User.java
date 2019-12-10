package users;

public class User {
    private String userName;
    private String code_wars_userName;

    public User(){
        setUserName(userName);
        setCode_wars_userName(code_wars_userName);
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setCode_wars_userName(String code_wars_userName){
        this.code_wars_userName = code_wars_userName;
    }
    public String getUserName(){
        return userName;
    }
    public String getCode_wars_userName(){
        return code_wars_userName;
    }
}
