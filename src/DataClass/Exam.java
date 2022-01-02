package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Exam {
    private String ID;
    private String groupName;
    private String sujectName;
    private String startTime;
    private String endTime;
    private int startYear , endYear;
    private int startMonth , endMonth;
    private int startDay , endDay;
    private int startHour , endHour;
    private int startMinute , endMinute;
    private ArrayList<Question> questions;

    public Exam(String ID, String groupName, String sujectName, String startTime, String endTime) {
        this.ID = ID;
        this.groupName = groupName;
        this.sujectName = sujectName;
        this.startTime = startTime;
        this.endTime = endTime;
        // start time
        String[] dateAndTime = this.startTime.split("\\s+");
        String[] s1 = dateAndTime[0].split("/");
        String[] s2 = dateAndTime[1].split(":");
        this.startYear = Integer.parseInt(s1[0]);
        this.startMonth = Integer.parseInt(s1[1]);
        this.startDay = Integer.parseInt(s1[2]);
        this.startHour = Integer.parseInt(s2[0]);
        this.startMinute = Integer.parseInt(s2[1]);
        // end time
        dateAndTime = this.endTime.split("\\s+");
        s1 = dateAndTime[0].split("/");
        s2 = dateAndTime[1].split(":");
        this.endYear = Integer.parseInt(s1[0]);
        this.endMonth = Integer.parseInt(s1[1]);
        this.endDay = Integer.parseInt(s1[2]);
        this.endHour = Integer.parseInt(s2[0]);
        this.endMinute = Integer.parseInt(s2[1]);

        questionsInitial();
    }

    public void questionsInitial(){
        this.questions = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from exam where id='" + this.ID + "'");
            ResultSet rs = st.getResultSet();
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(this.sujectName);
            while(rs.next()){
                QuestionBank questionBank = suject.getQuestionBank(rs.getString("qbName"));
                this.questions.add(questionBank.getQuestion(rs.getString("content")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getID() {
        return ID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getSujectName() {
        return sujectName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }
}
