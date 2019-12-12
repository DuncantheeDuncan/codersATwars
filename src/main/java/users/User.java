package users;

import java.io.Serializable;

public class User implements Serializable {


    private String fullname;
    private String codewarsusername;

    public User(String fullname, String codewarsusername){

        this.fullname = fullname;
        this.codewarsusername = codewarsusername;
    }

    public String getFullname() {
        return this.fullname;
    }


    public String getCodewarsUserName() {
        return this.codewarsusername;
    }

}
