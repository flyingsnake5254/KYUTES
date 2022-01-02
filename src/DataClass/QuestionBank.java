package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {
    private String name;
    private int questionNumber;
    private ArrayList<Question> questions;
    public QuestionBank(String name , int questionNumber){
        this.name = name;
        this.questionNumber = questionNumber;
        questionBankInitial();
    }
    public void questionBankInitial(){
        questions = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from " + this.name);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                this.questions.add(new Question(
                        rs.getInt("questionID"),
                        rs.getString("content"),
                        rs.getString("answer"),
                        rs.getString("degreeOfDifficulty"),
                        rs.getString("questionType"),
                        rs.getString("choice1"),
                        rs.getString("choice2"),
                        rs.getString("choice3"),
                        rs.getString("choice4")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createQB(Suject suject , String qbName){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("create table " + qbName + " (" +
                    "questionID int not null auto_increment," +
                    "content varchar(500) not null default ''," +
                    "answer varchar(500) not null default ''," +
                    "questionType varchar(20) not null default ''," +
                    "degreeOfDifficulty varchar(20) not null default ''," +
                    "choice1 varchar(50) not null default ''," +
                    "choice2 varchar(50) not null default ''," +
                    "choice3 varchar(50) not null default ''," +
                    "choice4 varchar(50) not null default ''," +
                    "primary key(questionID))");
            suject.updateQuestionBankNumber(1);
            suject.insertQuestionBank(qbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static boolean questionHasCreated(String sujectName , String qbName , String conent){
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(sujectName);
        QuestionBank qb = suject.getQuestionBank(qbName);
        boolean state = false;
        for(Question q : qb.getQuestions()){
            if(q.getContent().equals(conent)){
                state = true;
                break;
            }
        }
        return state;
    }

    public Question getDegreeQuestion(String degree){
        Question question = null;
        if(this.questions != null && this.questions.size() != 0){
            ArrayList<Question> pickQuestion = new ArrayList<>();
            if(degree.equals(Data.DEGREE_OF_DIFFICULTY_EASY)){
                for(Question q : this.questions){
                    if(q.getDegreeOfDifficulty().equals(Data.DEGREE_OF_DIFFICULTY_EASY))
                        pickQuestion.add(q);
                }
            }
            else if(degree.equals(Data.DEGREE_OF_DIFFICULTY_MEDIUM)){
                for(Question q : this.questions){
                    if(q.getDegreeOfDifficulty().equals(Data.DEGREE_OF_DIFFICULTY_MEDIUM))
                        pickQuestion.add(q);
                }
            }
            else if(degree.equals(Data.DEGREE_OF_DIFFICULTY_DIFFICULT)){
                for(Question q : this.questions){
                    if(q.getDegreeOfDifficulty().equals(Data.DEGREE_OF_DIFFICULTY_DIFFICULT))
                        pickQuestion.add(q);
                }
            }

            if(pickQuestion.size() != 0){
                Random random = new Random();
                question = pickQuestion.get(random.nextInt(pickQuestion.size()));
            }
        }
        return question;
    }

    public Question getQuestion(String content){
        Question question = null;
        for(Question q : this.questions){
            if(q.getContent().equals(content)){
                question = q;
                break;
            }
        }
        return question;
    }
}
