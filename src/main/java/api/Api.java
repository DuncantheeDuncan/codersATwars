package api;

import com.google.gson.Gson;
import db.DBConnection;
import spark.Route;
import users.User;
import users.UserInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
        for (User user : list) {


            CodewarsData cwd = new CodewarsData();

            cwd.setName(user.getFullname());
            cwd.setUsername(user.getCodewarsUserName());

            pushInto.add(cwd);


//            try {
//                URL url = new URL("https://www.codewars.com/api/v1/users/" + user.getCodewarsUserName());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Accept", "application/json");
//
//                if (conn.getResponseCode() != 200) {
//                    throw new RuntimeException("Failed : HTTP error code : "
//                            + conn.getResponseCode());
//                }
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        (conn.getInputStream())));
//
//                String output;
////            System.out.println("Output from Server .... \n");
//                while ((output = br.readLine()) != null) {
//
//                    CodewarsData data = new Gson()
//                            .fromJson(output, CodewarsData.class);
//
//                    pushInto.add(data);
//
//                }
//                conn.disconnect();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        return pushInto;
    }

    public Route addUser() {
        return (req, res) -> {
            res.type("application/json");
            System.out.println(req.body());

            User userInputs = new Gson().fromJson(req.body(), User.class);

            user.addUsers(userInputs.getFullname(), userInputs.getCodewarsUserName());


            return new Gson().toJson(new User(userInputs.getFullname(), userInputs.getCodewarsUserName()));
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
