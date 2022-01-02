/*
 * Created by JFormDesigner on Wed Dec 22 21:10:31 CST 2021
 */

package SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DataClass.Data;
import DataClass.GetDBdata;

/**
 * @author peiChun lu
 */
public class GroupCreate extends JPanel {
    public GroupCreate() {
        initComponents();
        comboInit();
    }
    
    private void comboInit(){
        cb_suject.addItem("全部");
        for(int i = 0 ; i < Data.SUBJECTS.length ; i ++){
            cb_suject.addItem(Data.SUBJECTS[i]);
        }
        cb_grade.addItem("全部");
        cb_grade.addItem("一年級");
        cb_grade.addItem("二年級");
        cb_grade.addItem("三年級");
        cb_grade.addItem("四年級");
        
    }

    private void b_search(ActionEvent e) {
        // TODO add your code here
        String selectSuject = cb_suject.getSelectedItem().toString();
        String selectGrade = cb_grade.getSelectedItem().toString();
        // get DB data
        ArrayList<String> accounts, names, identity, subjects, grade;
        accounts = new ArrayList<>();
        names = new ArrayList<>();
        identity = new ArrayList<>();
        subjects = new ArrayList<>();
        grade = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from user");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                accounts.add(rs.getString("account"));
                names.add(rs.getString("user_name"));
                identity.add(rs.getString("identity"));
                subjects.add(rs.getString("department"));
                grade.add(rs.getString("grade"));
            }
            
            ArrayList<Integer> matchIndex = new ArrayList<>();
            // check
            for(int i = 0 ; i < accounts.size() ; i ++){
                
                // check identity
                if(!identity.get(i).equals("student")){
                    continue;
                }
                // check suject
                if(!selectSuject.equals("全部")){
                    if(!subjects.get(i).equals(selectSuject))
                        continue;
                }
                // check grade
                if(!selectGrade.equals("全部")){
                    if(!grade.get(i).equals(selectGrade))
                        continue;
                }
                matchIndex.add(i);
            }
            // show data
            if(matchIndex.size() == 0){
                JOptionPane.showMessageDialog(null,
                        "無符合資料","訊息",JOptionPane.DEFAULT_OPTION);
            }
            else{
                // table setting
                DefaultTableModel dtm = new DefaultTableModel(matchIndex.size(),4);
                dataTable.setModel(dtm);
                dataTable.getColumnModel().getColumn(0).setHeaderValue("帳號");
                dataTable.getColumnModel().getColumn(1).setHeaderValue("姓名");
                dataTable.getColumnModel().getColumn(2).setHeaderValue("科系");
                dataTable.getColumnModel().getColumn(3).setHeaderValue("年級");
    
                for(int i = 0 ; i < matchIndex.size() ; i ++){
                    dataTable.setValueAt(accounts.get(matchIndex.get(i)),i,0);
                    dataTable.setValueAt(names.get(matchIndex.get(i)),i,1);
                    dataTable.setValueAt(subjects.get(matchIndex.get(i)),i,2);
                    dataTable.setValueAt(grade.get(matchIndex.get(i)),i,3);
                }
                dataTable.setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void b_create(ActionEvent e) {
        // TODO add your code here
        boolean createState = true;
        if(tf_groupName.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,
                    "群組名稱不可為空",
                    "錯誤",JOptionPane.ERROR_MESSAGE);
        }
        else{
            ArrayList<String> allGroupName = new ArrayList<>();
            Statement ss = new GetDBdata().getStatement();
            try {
                ss.execute("select * from all_group");
                ResultSet rs = ss.getResultSet();
                while(rs.next()){
                    allGroupName.add(rs.getString("name"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                createState = false;
            }
            boolean hasCreate = false;
            String groupName = tf_groupName.getText().trim();
            for(int i = 0 ; i < allGroupName.size() ; i ++){
                if(groupName.equals(allGroupName.get(i))){
                    hasCreate = true;
                    break;
                }
            }


            if(hasCreate){
                JOptionPane.showMessageDialog(null,
                        "此群組已存在","錯誤",JOptionPane.ERROR_MESSAGE);
            }
            else{
                int[] selectRow = dataTable.getSelectedRows();
                if(selectRow.length == 0){
                    Statement st = new GetDBdata().getStatement();
                    try {
                        st.execute("insert into all_group (name,people_num,suject_num) values " +
                                "('"+groupName+"','0','0')");
                        st.execute("create table "+groupName+" (" +
                                "name varchar(30) not null default ''," +
                                "primary key(name))");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        createState = false;
                    }
                }
                else{
                    ArrayList<String> selectAccount = new ArrayList<>();
                    for(int i = 0 ; i < selectRow.length ; i ++){
                        selectAccount.add(dataTable.getValueAt(i,0).toString());
                    }
                    Statement st = new GetDBdata().getStatement();
                    try {
                        st.execute("insert into all_group (name,people_num,suject_num) values " +
                                "('"+groupName+"','" + selectAccount.size() + "','0')");
                        st.execute("create table "+groupName+" (" +
                                "name varchar(30) not null default ''," +
                                "primary key(name))");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        createState = false;
                    }

                    for(int i = 0 ; i < selectAccount.size() ; i ++){

                        try {
                            st.execute("update user set student_group='"+groupName+"' " +
                                    "where account='"+selectAccount.get(i)+"'");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            createState = false;
                        }
                    }
                }
                if(createState)
                    JOptionPane.showMessageDialog(null,
                            "創建成功","訊息",JOptionPane.DEFAULT_OPTION);
                else
                    JOptionPane.showMessageDialog(null,
                            "創建失敗","錯誤",JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        tf_groupName = new JTextField();
        b_create = new JButton();
        label2 = new JLabel();
        cb_suject = new JComboBox();
        label3 = new JLabel();
        cb_grade = new JComboBox();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        dataTable = new JTable();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
        , 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
         getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 145, 54, 75, 0, 537, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 522, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44\u540d\u7a31");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_groupName, new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_create ----
        b_create.setText("\u5efa\u7acb");
        b_create.addActionListener(e -> b_create(e));
        add(b_create, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("\u79d1\u7cfb");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_suject, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("\u5e74\u7d1a");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label3, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_grade, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_search ----
        b_search.setText("\u67e5\u8a62");
        b_search.addActionListener(e -> b_search(e));
        add(b_search, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(dataTable);
        }
        add(scrollPane1, new GridBagConstraints(1, 3, 6, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JTextField tf_groupName;
    private JButton b_create;
    private JLabel label2;
    private JComboBox cb_suject;
    private JLabel label3;
    private JComboBox cb_grade;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable dataTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
