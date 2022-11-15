package org.example;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection("jdbc:oracle:thin:@172.22.112.100:1521:fbpool","C##FBPOOL164","oracle");
        System.out.println(dbConnection.connectToDb());
        System.out.println(dbConnection.disconnectFromDb());
    }


}