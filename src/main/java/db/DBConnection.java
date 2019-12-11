package db;

import users.UserInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBConnection implements UserInterface {

    private  String name;
    private String code_wars_username;
//
    //database connection
    Connection conn;

    final String INSERT_STUDENT  =   "INSERT INTO users(fullname, codewarsusername) VALUES(?, ?)";
    final String CHECK_USER =        "SELECT * FROM users WHERE codewarsusername = ?";
    final String GET_SINGLE_USER =   "SELECT codewarsusername FROM users";
    final String  GET_CODEWAREUSER = "SELECT codewarsusername FROM user WHERE codewarsusername = ?";
    final String GET_ALL_USERS =     "SELECT * FROM users";

    PreparedStatement insert_user;
    PreparedStatement check_users;
    PreparedStatement get_all_users;
    PreparedStatement get_single_user;
    PreparedStatement get_user_By_codewaresusername;

    public DBConnection(){


//        this.conn = conn;
        try{
            String username = "anele";
             String password = "coder123";
            final String url = "jdbc:postgresql://localhost/students_table?user=anele&password=coder123&ssl=true";

            conn = DriverManager.getConnection(url, username, password);

        } catch ( SQLException e) {
        System.out.println("Failed to connect to the database: " + e);
        }

    }

    @Override
    public  void addUsers(String fullname, String codewarsusername) {

        if (!fullname.isEmpty()){
            this.name = fullname;
            this.code_wars_username = codewarsusername;
        }

        try {
            insert_user = conn.prepareStatement(INSERT_STUDENT);
            //prepared statements
            check_users = conn.prepareStatement(CHECK_USER);

            check_users.setString(1, this.code_wars_username);
            ResultSet rs = check_users.executeQuery();

            //if user doen't exists add user
            if (!rs.next()){
                insert_user.setString(1, this.name);
                insert_user.setString(2, this.code_wars_username);

                insert_user.execute();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        return fullname ;
    }


    @Override
    public String getSingleUser(String code_wars_username) {

            try {
                get_user_By_codewaresusername.setString(1,code_wars_username);
                ResultSet rs = get_user_By_codewaresusername.executeQuery();
                //If the user exists get its counter
                if (rs.next()) {
                    return rs.getString("codewarsusername");
                }
            }catch (SQLException e) {System.out.println("Error: " + e);}


        return code_wars_username;
    }

    @Override
    public List<String> getUsersByCodewarUsername() {
        
        List<String> storeUsername = new ArrayList<>();
        try {
            get_single_user = conn.prepareStatement(GET_SINGLE_USER);
//            get_single_user.setString(1, codewarsusername);
            ResultSet rs = get_single_user.executeQuery();
            System.out.println(rs.toString());
            //if user exists get its code wars user name

            while (rs.next()) {
//                System.out.println(rs.getString("codewarsusername"));
                storeUsername.add(rs.getString("codewarsusername"));}
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println(storeUsername);
        return storeUsername;
    }

}
