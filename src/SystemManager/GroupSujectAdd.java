/*
 * Created by JFormDesigner on Thu Dec 23 19:56:31 CST 2021
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
import DataClass.GetDBdata;

/**
 * @author peiChun lu
 */
public class GroupSujectAdd extends JPanel {
    public GroupSujectAdd() {
        initComponents();
        initCombo();
        table1.setVisible(false);
        b_add.setVisible(false);
    }

    private void initCombo(){
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
    }

    String selectGroup;
    private void b_search(ActionEvent e) {
        // TODO add your code here
        selectGroup = cb_group.getSelectedItem().toString();
        Statement st = new GetDBdata().getStatement();
        //取得所有科目
        ArrayList<String> sujects = new ArrayList<>();
        ArrayList<String> question_num = new ArrayList<>();
        try {
            st.execute("select * from suject");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                sujects.add(rs.getString("name"));
                question_num.add(rs.getString("question_num"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // 取得此群組的科目
        ArrayList<String> groupSuject = new ArrayList<>();
        try {
            st.execute("select name from "+selectGroup);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                groupSuject.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // 可新增科目
        ArrayList<String> addSuject = new ArrayList<>();
        for(int i = 0 ; i < sujects.size() ; i ++){
            if(!groupSuject.contains(sujects.get(i)))
                addSuject.add(sujects.get(i));
        }

        // show table
        DefaultTableModel df = new DefaultTableModel(addSuject.size(),2);
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("科目");
        table1.getColumnModel().getColumn(1).setHeaderValue("題目數量");
        for(int i = 0 ; i < addSuject.size() ; i ++){
            table1.setValueAt(addSuject.get(i),i,0);
            table1.setValueAt(question_num.get(sujects.indexOf(addSuject.get(i))),i,1);
        }
        table1.setVisible(true);
        b_add.setVisible(true);

    }

    private void b_add(ActionEvent e) {
        // TODO add your code here
        // add all_group suject_num
        // add to table group's column:name
        boolean addState = true;
        int[] selectIndex = table1.getSelectedRows();
        int addNum = selectIndex.length;
        if(addNum != 0){
            Statement st = new GetDBdata().getStatement();
            String oriNum = "";
            try {
                st.execute("select suject_num from all_group where name='"+selectGroup+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    oriNum = rs.getString("suject_num");
                }
                String newNum = String.valueOf(Integer.parseInt(oriNum)+addNum);
                st.execute("update all_group set suject_num='"+newNum+"' " +
                        "where name='"+selectGroup+"'");
            } catch (SQLException ex) {
                ex.printStackTrace();
                addState = false;
            }
            // 取得選取科目
            ArrayList<String> selectSuject = new ArrayList<>();
            for(int i = 0 ; i < selectIndex.length ; i ++){
                selectSuject.add(table1.getValueAt(selectIndex[i],0).toString());
            }
            for(int i = 0 ; i < selectSuject.size() ; i ++){
                try {
                    st.execute("insert into "+selectGroup+"" +
                            "(name) values ('"+selectSuject.get(i)+"')");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    addState = false;
                }
            }


            if(addState) {
                JOptionPane.showMessageDialog(
                        null,
                        "新增成功",
                        "訊息",
                        JOptionPane.DEFAULT_OPTION
                );
                //delete row
                for(int i = 0 ; i < selectSuject.size() ; i ++){
                    for(int j = 0 ; j < table1.getRowCount() ; j ++){
                        if(table1.getValueAt(j,0).toString().equals(selectSuject.get(i))){
                            DefaultTableModel df = (DefaultTableModel) table1.getModel();
                            df.removeRow(j);
                        }
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null,
                        "新增失敗",
                        "錯誤",
                        JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(
                    null,
                    "尚未選取科目",
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
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        b_add = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
        ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
        .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
        propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
        ;} } );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 156, 0, 622, 63, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 506, 33, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_group, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_search ----
        b_search.setText("\u78ba\u8a8d");
        b_search.addActionListener(e -> b_search(e));
        add(b_search, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, new GridBagConstraints(1, 2, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_add ----
        b_add.setText("\u65b0\u589e");
        b_add.addActionListener(e -> b_add(e));
        add(b_add, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JComboBox cb_group;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton b_add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
