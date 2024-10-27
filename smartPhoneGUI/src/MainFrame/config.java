package MainFrame;

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
            con = DriverManager.getConnection(url , user, password);
            stmt = con.createStatement();

            String createDB = "CREATE DATABASE IF NOT EXISTS products";
            stmt.executeUpdate(createDB);
            System.out.println("Database created successfully.");

            String useDB = "USE products";
            stmt.executeUpdate(useDB);
            System.out.println("Switched to database 'products'");

            String createTB = "CREATE TABLE IF NOT EXISTS smartphone(" + "Identification_Number INT NOT NULL," +
                            "Brand VARCHAR(65) NOT NULL," +
                            "Color  VARCHAR(65) NOT NULL," +
                            "Price DOUBLE NOT NULL," +
                            "Quantity INT NOT NULL," +
                            "PRIMARY KEY (Identification_Number))";
            stmt.executeUpdate(createTB);
            System.out.println("Table created successfully.");

        }catch (SQLException e){
            e.printStackTrace();

        }
    }
}
