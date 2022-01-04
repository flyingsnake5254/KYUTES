package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExamGrades {
    private ArrayList<ExamGrade> examGrades;
    private String id;
    public ExamGrades(String id){
        this.id = id;
        initial();
    }
    private void initial(){
        examGrades = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from examGrade where id='" + id + "'");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                this.examGrades.add(new ExamGrade(rs.getString("account"),rs.getString("examGrade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ExamGrade> getExamGrades() {
        return examGrades;
    }

    public String getId() {
        return id;
    }
}
