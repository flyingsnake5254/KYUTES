/*
 * Created by JFormDesigner on Thu Dec 23 00:48:04 CST 2021
 */

package SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;
import DataClass.GetDBdata;

/**
 * @author peiChun lu
 */
public class GroupMemberDelete extends JPanel {
    public GroupMemberDelete() {
        initComponents();
        initCombo();
        table1.setVisible(false);
        bDelete.setVisible(false);
    }
    
    private void initCombo(){
        cbGroupName.addItem("請選擇群組");
        ArrayList<String> groupName = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select name from all_group");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                groupName.add(rs.getString("name"));
            }
            for(int i = 0 ; i < groupName.size() ; i ++){
                cbGroupName.addItem(groupName.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "發生錯誤",
                    "錯誤",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void bSearch(ActionEvent e) {
        // TODO add your code here
        String selectGroup = cbGroupName.getSelectedItem().toString();
        if(selectGroup.equals("請選擇群組")){
            JOptionPane.showMessageDialog(
                    null,
                    "尚未選擇群組",
                    "錯誤",JOptionPane.ERROR_MESSAGE
            );
            table1.setVisible(false);
            bDelete.setVisible(false);
        }
        else{
            boolean searchState = true;
            ArrayList<String> accounts = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> sujects = new ArrayList<>();
            ArrayList<String> grade = new ArrayList<>();

            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("select account,user_name,department,grade from user where " +
                        "student_group='"+selectGroup+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    accounts.add(rs.getString("account"));
                    names.add(rs.getString("user_name"));
                    sujects.add(rs.getString("department"));
                    grade.add(rs.getString("grade"));
                }
                if(accounts.size() == 0){
                    JOptionPane.showMessageDialog(
                            null,
                            "此群組尚無成員",
                            "訊息",
                            JOptionPane.DEFAULT_OPTION
                    );
                }
                else{
                    DefaultTableModel df = new DefaultTableModel(accounts.size(),5);
                    table1.setModel(df);
                    table1.getColumnModel().getColumn(0).setHeaderValue("帳號");
                    table1.getColumnModel().getColumn(1).setHeaderValue("姓名");
                    table1.getColumnModel().getColumn(2).setHeaderValue("科系");
                    table1.getColumnModel().getColumn(3).setHeaderValue("年級");
                    table1.getColumnModel().getColumn(4).setHeaderValue("群組");
                    for(int i = 0 ; i < accounts.size() ; i ++){
                        table1.setValueAt(accounts.get(i),i,0);
                        table1.setValueAt(names.get(i),i,1);
                        table1.setValueAt(sujects.get(i),i,2);
                        table1.setValueAt(grade.get(i),i,3);
                        table1.setValueAt(selectGroup,i,4);
                    }
                    table1.setVisible(true);
                    bDelete.setVisible(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                searchState = false;
            }

            if(!searchState){
                JOptionPane.showMessageDialog(
                        null,
                        "發生錯誤","錯誤",JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        int[] selectIndex = table1.getSelectedRows();
        if(selectIndex.length == 0){
            JOptionPane.showMessageDialog(null,
                    "尚未選取欲刪除之帳號",
                    "錯誤",JOptionPane.ERROR_MESSAGE);
        }
        else{
            //1. user table student_group ""
            //2. all_group people_num -1
            ArrayList<String> selectAccount = new ArrayList<>();
            String selectGroup = table1.getValueAt(selectIndex[0],4).toString();
            for(int i = 0 ; i < selectIndex.length ; i ++){
                selectAccount.add(table1.getValueAt(selectIndex[i],0).toString());
            }
            for(int i = 0 ; i < selectAccount.size() ; i ++){
                Statement st = new GetDBdata().getStatement();
                try {
                    st.execute("update user set student_group='' where account='"+selectAccount.get(i)
                    +"'");
                    String oriNum = "";
                    st.execute("select people_num from all_group where name='"+selectGroup+"'");
                    ResultSet rs = st.getResultSet();
                    while(rs.next()){
                        oriNum=rs.getString("people_num");
                    }
                    String aftNum = String.valueOf(Integer.parseInt(oriNum)-1);
                    st.execute("update all_group set people_num='"+aftNum+"' where name='"+selectGroup+"'");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                for(int j = 0 ; j < table1.getRowCount() ; j ++){
                    if(table1.getValueAt(j,0).equals(selectAccount.get(i))){
                        DefaultTableModel df = (DefaultTableModel) table1.getModel();
                        df.removeRow(j);
                    }
                }
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        cbGroupName = new JComboBox();
        bSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bDelete = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 194, 0, 589, 73, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 527, 34, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cbGroupName, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bSearch ----
        bSearch.setText("\u67e5\u8a62");
        bSearch.addActionListener(e -> bSearch(e));
        add(bSearch, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, new GridBagConstraints(1, 2, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bDelete ----
        bDelete.setText("\u522a\u9664");
        bDelete.addActionListener(e -> bDelete(e));
        add(bDelete, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JComboBox cbGroupName;
    private JButton bSearch;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
