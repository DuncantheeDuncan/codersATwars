package main;
import api.Api;
import db.DBConnection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import transformer.JsonTransformer;
import users.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

//    static Connection getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        String database_url = processBuilder.environment().get("DATABASE_URL");
//        if (database_url != null) {
//
//            URI uri = new URI(database_url);
//            String[] hostParts = uri.getUserInfo().split(":");
//            String username = "coder";
//            String password = "coder123";
//            String host = uri.getHost();
//
//            int port = uri.getPort();
//
//            String path = uri.getPath();
//            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);
//
//            return DriverManager.getConnection(url, username, password);
//
//        }
//
//        return DriverManager.getConnection(defualtJdbcUrl);
//
//    }

    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        Api api = new Api(db);

        staticFiles.location("/public");

        try {
//            get("/api/codewars/users/:users", api.getSingleUser(), new JsonTransformer());

            post("/api/users/add", api.addUser(), new JsonTransformer());

            get("/api/users/getUsers", api.getAllUsers(), new JsonTransformer());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }



}
