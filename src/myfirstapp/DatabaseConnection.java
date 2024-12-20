package myfirstapp;

import java.sql.*;

public class DatabaseConnection {
public static Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/ahly_store", "root", "");
            return con;
       }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}