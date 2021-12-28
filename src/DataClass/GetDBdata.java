package DataClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBdata {
    private Connection connection;
    private Statement statement;
    public GetDBdata() {
        this.connection = null;
        this.statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/" + Data.DB_NAME
                    ,"root", Data.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Statement getStatement(){
        return this.statement;
    }
}
