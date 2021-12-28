package DataClass;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class User {
    private String account;
    private String password;
    private String userName;
    private String phoneNumber;
    private String userEmail;
    private String department;
    private String grade;
    private String identity;
    private boolean firstLogin;
    private boolean state;
    private String createTime;
    private String studentGroup;

    public User(String account, String password, String userName, String phoneNumber,
                String userEmail, String department, String grade, String identity,
                String strFirstLogin, String strState, String createTime, String studentGroup) {
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.department = department;
        this.grade = grade;
        this.identity = identity;
        this.createTime = createTime;
        this.studentGroup = studentGroup;
        if(strFirstLogin.equals("true")) this.firstLogin = true;
        else this.firstLogin = false;
        if(strState.equals("online")) this.state = true;
        else this.state = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDepartment() {
        return department;
    }

    public String getGrade() {
        return grade;
    }

    public String getIdentity() {
        return identity;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public boolean isOnline() {
        return state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setOnline(boolean state){
        Statement st = new GetDBdata().getStatement();
        try {
            if(state)
                st.execute("update user set state='online' where account='"+getAccount()+"'");
            else
                st.execute("update user set state='offline' where account='"+getAccount()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createAccount(String act){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("insert into user" +
                    "(account)" +
                    " values " +
                    "('" + act + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPassword(String act , String pwd){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set password='" + pwd + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDepartment(String act , String dpt){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set department='" + dpt + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setGrade(String act , String gd){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set grade='" + gd + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setIdentity(String act , String idt){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set identity='" + idt + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setCreateTime(String act , String t){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set create_time='" + t + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUserName(String act , String n){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set user_name='" + n + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPhoneNumber(String act , String num){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set phone_number='" + num + "' where account='" + act + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUserEmail(String act , String e){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update user set user_email='" + e + "' where account='" + act + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getYear(){
        String[] s = this.createTime.split("-");
        return s[0];
    }

    public String getMonth(){
        String[] s = this.createTime.split("-");
        return s[1];
    }

    public String getDay(){
        String[] s = this.createTime.split("-");
        return s[2];
    }

    public String getCNIdentity(){
        if(this.identity.equals(Data.USER_IDENTITY_STUDENT))
            return Data.USER_IDENTITY_STUDENT_CN;
        else if(this.identity.equals(Data.USER_IDENTITY_EXAM_MANAGER))
            return Data.USER_IDENTITY_EXAM_MANAGER_CN;
        else return Data.USER_IDENTITY_SYSTEM_MANAGER_CN;
    }

    public static void deleteUser(String name){
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("delete from user where account='" + name + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
