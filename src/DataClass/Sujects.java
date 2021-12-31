package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sujects {
    private ArrayList<Suject> sujects;

    public Sujects(){
        sujectsInitial();
    }
    public void sujectsInitial(){
        sujects = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from suject");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                sujects.add(new Suject(
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("bank_num")),
                        Integer.parseInt(rs.getString("question_num"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Suject> getSujects() {
        return sujects;
    }

    public Suject getSuject(String sujectName){
        Suject suject = null;
        for(Suject s : sujects){
            if(s.getName().equals(sujectName)) {
                suject = s;
                break;
            }
        }
        return suject;
    }
}
