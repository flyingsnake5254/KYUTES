package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Suject {
    // 科目名
    private String name;
    // 題庫數量
    private int questionBankNumber;
    // 題目數量
    private int questionNumber;
    // 題庫
    private ArrayList<QuestionBank> questionBanks;

    public Suject(String name , int questionBankNumber , int questionNumber){
        this.name = name;
        this.questionBankNumber = questionBankNumber;
        this.questionNumber = questionNumber;
        sujectInitial();
    }
    public void sujectInitial(){
        questionBanks = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from " + this.name);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                this.questionBanks.add(
                        new QuestionBank(
                                rs.getString("name"),
                                Integer.parseInt(rs.getString("question_num")))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getQuestionBankNumber() {
        return questionBankNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public ArrayList<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public QuestionBank getQuestionBank(String qbName){
        QuestionBank questionBank = null;
        for(QuestionBank qb : this.getQuestionBanks()){
            if(qb.getName().equals(qbName)){
                questionBank = qb;
                break;
            }
        }
        return questionBank;
    }

    public void updateQuestionBankNumber(int value){
        int newValue = this.questionBankNumber + value;
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update suject set bank_num='"+String.valueOf(newValue)+"' " +
                    "where name='" + this.name + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertQuestionBank(String qbName){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("insert into " + this.name + "" +
                    "(name,question_num)" +
                    " values " +
                    "('" + qbName + "','0')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean questionBankHasCreated(String qbName){
        boolean state = false;
        for(QuestionBank qb : this.questionBanks){
            if(qb.getName().equals(qbName)){
                state = true;
                break;
            }
        }
        return state;
    }

    public void updateQuestionNumber(int value , String qbName){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update suject set question_num='"
            + String.valueOf(this.questionNumber + value) +"' where name='" + this.name +"'");
            QuestionBank questionBank = this.getQuestionBank(qbName);
            st.execute("update " + this.name + " set question_num='" + String.valueOf(questionBank.getQuestionNumber() + value) + "'" +
                    " where name='" + qbName +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(String qbName , String content){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("delete from " + qbName + " where content='" + content + "'");
            this.updateQuestionNumber(-1 , qbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestionBank(String qbName){
        Statement st = new GetDBdata().getStatement();
        try {
            QuestionBank questionBank = this.getQuestionBank(qbName);
            int questionNum = questionBank.getQuestionNumber();
            this.updateQuestionBankNumber(-1);
            this.updateQuestionNumber(-questionNum,qbName);
            st.execute("drop table " + qbName);
            st.execute("delete from " + this.name + " where name = '" + qbName + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
