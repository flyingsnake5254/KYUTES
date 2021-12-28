package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Group {
    private String groupName;
    private ArrayList<String> testSujects;
    private Integer peopleNumber;
    private Integer testSujectNumber;

    public Group(String groupName){
        this.groupName = groupName;
        groupInitial();
        this.testSujectNumber = testSujects.size();
    }
    public void groupInitial(){
        testSujects = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from " + this.groupName);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                testSujects.add(rs.getString("name"));
            }
            st.execute("select people_num from all_group where name='"
            + this.groupName +"'");
            rs = st.getResultSet();
            while(rs.next()){
                this.peopleNumber = Integer.parseInt(rs.getString("people_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<String> getTestSujects() {
        return testSujects;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public Integer getTestSujectNumber() {
        return testSujectNumber;
    }

    public void updateGroupPeopleNumber(int value){
        int newValue = this.peopleNumber + value;
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("update all_group set people_num='"+String.valueOf(newValue)+"'"
            +" where name='" + this.groupName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
