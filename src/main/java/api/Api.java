package api;

import com.google.gson.Gson;
import spark.Route;
import users.User;
import users.UserInterface;


public class Api {



    private UserInterface user;

    public Api(UserInterface user){
        this.user = user;
    }



   public Route addUser(){
        return (req, res) ->{
            res.type("application/json");
            System.out.println(req.body());

            User userInputs = new Gson().fromJson(req.body(), User.class);

             user.addUsers(userInputs.getFullname(),  userInputs.getCodewarsUserName());


            return new Gson().toJson(new User(userInputs.getFullname(), userInputs.getCodewarsUserName()));
        };
    }

    public Route getSingleUser(){
        return (req, res)->{


            return null;


        };
    }

   public Route getAllUsers(){

       System.out.println(user.getUsersByCodewarUsername());
        return (req, res) -> user.getUsersByCodewarUsername();
    }
}
