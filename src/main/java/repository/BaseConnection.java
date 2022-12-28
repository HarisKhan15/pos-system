package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseConnection {


//        final String DB_URL = "jdbc:mysql://localhost:3306/POS";
//        final String USER = "root";
//        final String PASS = "password";
    final String DB_URL = "jdbc:mysql://localhost:3306/POS";
    final String USER = "root";
    final String PASS = "password";
        Connection conn=null;
        public BaseConnection() {
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
}
