package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Sujects {
    private ArrayList<String> names;
    private ArrayList<Integer> question_nums;
    private ArrayList<Integer> bank_nums;
    public Sujects(){
        dataInitial();
    }

    public void dataInitial(){
        names = new ArrayList<>();
        question_nums = new ArrayList<>();
        bank_nums = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from suject");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                names.add(rs.getString("name"));
                bank_nums.add(Integer.parseInt(rs.getString("bank_num")));
                question_nums.add(Integer.parseInt(rs.getString("question_num")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Integer> getQuestion_nums() {
        return question_nums;
    }

    public ArrayList<Integer> getBank_nums() {
        return bank_nums;
    }
}
