package api;

import com.google.gson.Gson;
import db.DBConnection;
import spark.Route;
import users.User;
import users.UserInterface;

import java.util.ArrayList;
import java.util.List;


class CodewarsData {

    String username;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}


public class Api {


    private UserInterface user;

    public Api(UserInterface user) {
        this.user = user;
    }

    public List<CodewarsData> getCodewarsJson() {

        DBConnection connection = new DBConnection();
        List<User> list = connection.getUsersByCodewarUsername();
        List<CodewarsData> pushInto = new ArrayList<>();
        //
        for (User user : list) {


            CodewarsData cwd = new CodewarsData();

            cwd.setName(user.getName());
            cwd.setUsername(user.getUsername());

            pushInto.add(cwd);

        }

        return pushInto;
    }

    public Route addUser() {
        return (req, res) -> {
            res.type("application/json");
            System.out.println(req.body());

            System.out.println(req.body());

            User userInputs = new Gson().fromJson(req.body(), User.class);
            user.addUsers(userInputs.getName(), userInputs.getUsername());
            return new Gson().toJson(new User(userInputs.getName(), userInputs.getUsername()));
        };
    }

    public Route getSingleUser() {
        return (req, res) -> {

            return null;

        };
    }

    public Route getAllUsers() {
        return (req, res) -> {
            res.type("application/json");
            return getCodewarsJson();
        };
    }
}
