package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final String url,password,userid;;
    private Connection connection;

    public DbConnection(String url, String userid, String password){
        this.url = url;
        this.userid = userid;
        this.password = password;
    }


    public boolean connectToDb(){
        try {
            connection = DriverManager.getConnection(getUrl(),getUserid(),getPassword());

        }catch (SQLException exception){
            return  false;

        }
        return true;
    }


    public boolean disconnectFromDb(){   //ToDO: Abfragen ob DB schon offen ist
        try {
            if(connection.isClosed()){
                return  true;
            }
           connection.close();
        }catch (SQLException exception){
            return  false;

        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUserid() {
        return userid;
    }

    public Connection getConnection() {
        return connection;
    }
}
