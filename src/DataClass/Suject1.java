package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Suject1 {
    private ArrayList<String> names;
    private ArrayList<Integer> question_nums;
    private String suject_name;
    public Suject1(String suject_name){
        this.suject_name = suject_name;
        dataInitial();
    }
    public void dataInitial(){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from " + suject_name);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                names.add(rs.getString("name"));
                question_nums.add(Integer.parseInt(rs.getString("question_num")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public String getSuject_name() {
        return suject_name;
    }

    public ArrayList<Integer> getQuestion_nums() {
        return question_nums;
    }
}
