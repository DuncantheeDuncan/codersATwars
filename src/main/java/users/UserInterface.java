package users;

import java.util.List;
import java.util.Map;

public interface UserInterface {

    String getSingleUser(String codewarUsername);
    void addUsers(String fullname, String code_wars_username);
    List<User> getUsersByCodewarUsername();

}
