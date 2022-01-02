package DataClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Groups {
    private ArrayList<Group> groups;
    public Groups(){
        groupsInitial();
    }
    public void groupsInitial(){
        groups = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select name from all_group");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                this.groups.add(new Group(rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Group getGroup(String groupName){
        Group group = null;
        for(Group g : this.groups){
            if(g.getGroupName().equals(groupName)){
                group = g;
                break;
            }
        }
        return group;
    }

}
