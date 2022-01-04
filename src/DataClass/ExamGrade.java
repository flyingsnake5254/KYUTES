package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamGrade {
    private String account;
    private User user;
    private String examGrade;
    public ExamGrade(String account , String examGrade){
        this.account = account;
        this.examGrade = examGrade;
        Users users = new Users();
        this.user = users.getUser(this.account);
    }

    public String getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }

    public String getExamGrade() {
        return examGrade;
    }
}
