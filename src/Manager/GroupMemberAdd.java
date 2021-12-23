/*
 * Created by JFormDesigner on Thu Dec 23 00:48:30 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class GroupMemberAdd extends JPanel {
    public GroupMemberAdd() {
        initComponents();
        table1.setVisible(false);
        b_add.setVisible(false);
        initCombo();
    }
    
    private void initCombo(){
        cb_group.addItem("請選擇群組");
        ArrayList<String> groups = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select name from all_group");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                groups.add(rs.getString("name"));
            }
            for(int i = 0 ; i < groups.size() ; i ++){
                cb_group.addItem(groups.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < SecretData.SUBJECTS.length ; i ++){
            cb_suject.addItem(SecretData.SUBJECTS[i]);
        }
        cb_grade.addItem("一年級");
        cb_grade.addItem("二年級");
        cb_grade.addItem("三年級");
        cb_grade.addItem("四年級");
    }

    String selectGroup;
    private void b_search(ActionEvent e) {
        // TODO add your code here
        selectGroup = cb_group.getSelectedItem().toString();
        if(selectGroup.equals("請選擇群組")){
            JOptionPane.showMessageDialog(
                    null,
                    "尚未選擇群組",
                    "錯誤",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else{
            String selectSuject = cb_suject.getSelectedItem().toString();
            String selectGrade = cb_grade.getSelectedItem().toString();
            ArrayList<String> accounts = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> sujects = new ArrayList<>();
            ArrayList<String> grade = new ArrayList<>();
            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("select account,user_name,department,grade from user " +
                        "where student_group <> '"+selectGroup+"' and identity='student'" +
                        " and department='"+selectSuject+"' and grade='" +
                        selectGrade+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    accounts.add(rs.getString("account"));
                    names.add(rs.getString("user_name"));
                    sujects.add(rs.getString("department"));
                    grade.add(rs.getString("grade"));
                }
                DefaultTableModel df = new DefaultTableModel(accounts.size(),4);
                table1.setModel(df);
                table1.getColumnModel().getColumn(0).setHeaderValue("帳號");
                table1.getColumnModel().getColumn(1).setHeaderValue("姓名");
                table1.getColumnModel().getColumn(2).setHeaderValue("科系");
                table1.getColumnModel().getColumn(3).setHeaderValue("年級");
                for(int i = 0 ; i < accounts.size() ; i ++){
                    table1.setValueAt(accounts.get(i),i,0);
                    table1.setValueAt(names.get(i),i,1);
                    table1.setValueAt(sujects.get(i),i,2);
                    table1.setValueAt(grade.get(i),i,3);
                }
                table1.setVisible(true);
                b_add.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void b_add(ActionEvent e) {
        // TODO add your code here
        int[] selectIndex = table1.getSelectedRows();
        if(selectIndex.length == 0){
            JOptionPane.showMessageDialog(
                    null,
                    "尚未選取成員",
                    "錯誤",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else{
            // table user student_group add content
            // all_group people_num +1
            DefaultTableModel df = (DefaultTableModel) table1.getModel();
            boolean addState = true;
            ArrayList<String> selectAccount = new ArrayList<>();
            for(int i = 0 ; i < selectIndex.length ; i ++){
                selectAccount.add(table1.getValueAt(selectIndex[i],0).toString());
            }
            int people_numAdd = selectAccount.size();

            // update table
            for(int i = 0 ; i < selectAccount.size() ; i ++){
                Statement st = new GetDBdata().getStatement();
                try {
                    st.execute("update user set student_group='"+selectGroup+
                            "' where account='"+selectAccount.get(i)+"'");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    addState = false;
                }
            }
            String oriPeopleNum = "";
            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("select people_num from all_group where name='"+
                        selectGroup+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    oriPeopleNum = rs.getString("people_num");
                }
                String newPeopleNum = String.valueOf(Integer.parseInt(oriPeopleNum)+people_numAdd);
                st.execute("update all_group set people_num='"+newPeopleNum+"'" +
                        " where name='"+selectGroup+"'");
                for(int i = 0 ; i < selectAccount.size() ; i ++){
                    for(int j = 0 ; j < table1.getRowCount() ; j ++){
                        if(table1.getValueAt(j,0).toString().equals(selectAccount.get(i))){
                            df.removeRow(j);
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                addState = false;
            }
            if(addState)
                JOptionPane.showMessageDialog(
                        null,
                        "新增群組成員成功",
                        "訊息",JOptionPane.DEFAULT_OPTION
                );
            else
                JOptionPane.showMessageDialog(
                        null,
                        "新增群組成員失敗",
                        "錯誤",
                        JOptionPane.ERROR_MESSAGE
                );
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        cb_group = new JComboBox();
        label2 = new JLabel();
        cb_suject = new JComboBox();
        label3 = new JLabel();
        cb_grade = new JComboBox();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        b_add = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
        ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
        .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
        propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
        ;} } );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 175, 0, 143, 0, 0, 0, 315, 67, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 501, 33, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_group, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("\u79d1\u7cfb");
        add(label2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_suject, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("\u5e74\u7d1a");
        add(label3, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_grade, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_search ----
        b_search.setText("\u67e5\u8a62");
        b_search.addActionListener(e -> b_search(e));
        add(b_search, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, new GridBagConstraints(1, 2, 9, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_add ----
        b_add.setText("\u65b0\u589e");
        b_add.addActionListener(e -> b_add(e));
        add(b_add, new GridBagConstraints(9, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JComboBox cb_group;
    private JLabel label2;
    private JComboBox cb_suject;
    private JLabel label3;
    private JComboBox cb_grade;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton b_add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
