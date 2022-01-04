package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Users {
    private ArrayList<User> users;
    public Users(){
        dataInitial();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void dataInitial(){
        users = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from user");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                users.add(new User(
                        rs.getString("account"),
                        rs.getString("password"),
                        rs.getString("user_name"),
                        rs.getString("phone_number"),
                        rs.getString("user_email"),
                        rs.getString("department"),
                        rs.getString("grade"),
                        rs.getString("identity"),
                        rs.getString("first_login"),
                        rs.getString("state"),
                        rs.getString("create_time"),
                        rs.getString("student_group")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public User getUser(String account){
        for(int i = 0 ; i < users.size() ; i ++){
            if(users.get(i).getAccount().equals(account))
                return users.get(i);
        }
        return null;
    }

    // 確認帳密是否正確
    public boolean checkAccount(String account, String password){
        boolean state = false;
        for(int i = 0 ; i < users.size() ; i ++){
            if(users.get(i).getAccount().equals(account) && users.get(i).getPassword().equals(password)){
                state = true;
                break;
            }
        }
        return state;
    }

    public boolean accountHasCreated(String account){
        boolean state = false;
        for(int i = 0 ; i < users.size() ; i ++){
            if(users.get(i).getAccount().equals(account)){
                state = true;
                break;
            }
        }
        return state;
    }


}
