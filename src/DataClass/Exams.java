package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Exams {
    private ArrayList<Exam> examInfos;
    private ArrayList<String> examID;
    public Exams(){
        examInfosInitial();
    }

    public void examInfosInitial(){
        examInfos = new ArrayList<>();
        examID = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from examInfo");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                examInfos.add(new Exam(
                        rs.getString("id"),
                        rs.getString("groupName"),
                        rs.getString("sujectName"),
                        rs.getString("startTime"),
                        rs.getString("endTime")
                ));
            }
            for(Exam e : examInfos){
                examID.add(e.getID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Exam> getExamInfos() {
        return examInfos;
    }

    public ArrayList<Exam> getGroupExam(String groupName){
        ArrayList<Exam> examArrayList = new ArrayList<>();
        for(Exam exam : this.examInfos){
            if(exam.getGroupName().equals(groupName)){
                examArrayList.add(exam);
            }
        }
        return examArrayList;
    }

    public ArrayList<Exam> getGroupSujectExam(String groupName , String sujectName){
        ArrayList<Exam> examArrayList = new ArrayList<>();
        for(Exam exam : this.examInfos){
            if(exam.getGroupName().equals(groupName) && exam.getSujectName().equals(sujectName)){
                examArrayList.add(exam);
            }
        }
        return examArrayList;
    }

    public Exam getExam(String id){
        Exam exam = null;
        for(Exam exam1 : this.examInfos){
            if(exam1.getID().equals(id)){
                exam = exam1;
                break;
            }
        }
        return exam;
    }

    public ArrayList<String> getExamID() {
        return examID;
    }

    public String generateID(){
        Random random = new Random();
        IntStream intStream = random.ints(1,10000001,99999999);
        int[] arr = intStream.toArray();
        String sID = String.valueOf(arr[0]);
        while(this.examID.contains(sID)){
            intStream = random.ints(1,10000001,99999999);
            arr = intStream.toArray();
            sID = String.valueOf(arr[0]);
        }
        return sID;
    }

    public void insertToExamInfo(String id , String gName , String sName , String startTime , String endTime){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("insert into examInfo(" +
                    "id,groupName,sujectName,startTime,endTime) values " +
                    "('"+id+"','"+gName+"','"+sName+"','"+startTime+"','"+endTime+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createExam(String groupName , String sujectName , String startTime ,
                                  String endTime , ArrayList<Question> questions , ArrayList<String> qbList){
        String examID = this.generateID();
        this.insertToExamInfo(examID , groupName , sujectName , startTime , endTime);
        for(int i = 0 ; i < questions.size() ; i ++){
            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("insert into exam " +
                        "(id,qbName,content) " +
                        "values " +
                        "('" + examID + "','" + qbList.get(i) + "','" + questions.get(i).getContent() + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Dialog.message("創建成功");
    }

    public void deleteExam(String eID){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("delete from examInfo where id='" + eID + "'");
            st.execute("delete from exam where id='" + eID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
