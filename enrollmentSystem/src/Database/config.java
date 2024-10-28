package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class config {
    Connection con;
    Statement stmt;

    public config(){
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            String db = "CREATE DATABASE IF NOT EXISTS Schooldb";
            stmt.executeUpdate(db);
            System.out.println("Database created successfully.");

            String dbSelected = "USE Schooldb";
            stmt.executeUpdate(dbSelected);
            System.out.println("Switched to database 'Schooldb");

            String sTable = "CREATE TABLE IF NOT EXISTS student(" + "ID INT NOT NULL," +
                    "STNAME VARCHAR(65) NOT NULL," +
                    "ADDRESS  VARCHAR(65) NOT NULL," +
                    "COURSE VARCHAR(65) NOT NULL," +
                    "YEAR INT NOT NULL," +
                    "PRIMARY KEY (ID))";
                            stmt.executeUpdate(sTable);
                            System.out.println("'student' table created successfully.");

            String subTable = "CREATE TABLE IF NOT EXISTS subject (" + "CODE INT NOT NULL," +
                            "DESCRIPTION VARCHAR(65) NOT NULL," +
                            "SCHEDULE VARCHAR(65) NOT NULL," +
                            "TEACHER VARCHAR(65) NOT NULL," +
                            "UNITS INT NOT NULL," +
                            "PRIMARY KEY(CODE))";
                            stmt.executeUpdate(subTable);
                            System.out.println("'subject' table created successfully.");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("FAILED TO CONNECT TO DATABASE.");
        }
    }
}
