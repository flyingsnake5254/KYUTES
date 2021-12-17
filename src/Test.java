import java.sql.*;

public class Test {
    public static void main(String[] args){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功連接MySQL");
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/account_and_password","root","Holmes5254");
            Statement st = connection.createStatement();
            //撈出剛剛新增的資料
            st.execute("SELECT * FROM t1");
            ResultSet rs = st.getResultSet();

            while(rs.next())
            {
                System.out.println(rs.getString("account")+" "+rs.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
