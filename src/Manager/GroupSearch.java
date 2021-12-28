/*
 * Created by JFormDesigner on Wed Dec 22 20:20:26 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;
import DataClass.GetDBdata;

/**
 * @author peiChun lu
 */
public class GroupSearch extends JPanel {
    public GroupSearch() {
        initComponents();
        cb_all.addItem("全部");
        b_delete.setVisible(false);
        table1.setVisible(false);
    }

    private void b_search(ActionEvent e) {
        // TODO add your code here
        if(cb_all.getSelectedItem().toString().equals("全部")){
            ArrayList<String> groupName = new ArrayList<>();
            ArrayList<String> peopleNum = new ArrayList<>();
            ArrayList<String> sujectNum = new ArrayList<>();
            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("select * from all_group");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    groupName.add(rs.getString("name"));
                    peopleNum.add(rs.getString("people_num"));
                    sujectNum.add(rs.getString("suject_num"));
                }
                DefaultTableModel df = new DefaultTableModel(groupName.size(),3);
                table1.setModel(df);
                table1.getColumnModel().getColumn(0).setHeaderValue("群組名稱");
                table1.getColumnModel().getColumn(1).setHeaderValue("群組人數");
                table1.getColumnModel().getColumn(2).setHeaderValue("群組科目數量");
                for(int i = 0 ; i < groupName.size() ; i ++){
                    table1.setValueAt(groupName.get(i),i,0);
                    table1.setValueAt(peopleNum.get(i),i,1);
                    table1.setValueAt(sujectNum.get(i),i,2);
                }
                table1.setVisible(true);
                b_delete.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                table1.setVisible(false);
                b_delete.setVisible(false);
                JOptionPane.showMessageDialog(
                        null,
                        "無法讀取資料庫",
                        "錯誤",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }

    private void b_delete(ActionEvent e) {
        // TODO add your code here
        int[] selectIndex = table1.getSelectedRows();
        ArrayList<String> selectGroup = new ArrayList<>();
        for(int i = 0 ; i < selectIndex.length ; i ++){
            selectGroup.add(table1.getValueAt(selectIndex[i],0).toString());
        }
        Statement st = new GetDBdata().getStatement();
        DefaultTableModel df = (DefaultTableModel) table1.getModel();
        for(int i = 0 ; i < selectGroup.size() ; i ++){
            try {
                st.execute("delete from all_group where name='"+selectGroup.get(i)+"'");
                st.execute("drop table "+selectGroup.get(i));
                st.execute("update user set student_group='' where student_group='"
                +selectGroup.get(i)+"'");
                // delete table row
                for(int j = 0 ; j < table1.getRowCount() ; j ++){
                    if(table1.getModel().getValueAt(j,0).toString().equals(selectGroup.get(i))){

                        df.removeRow(j);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        cb_all = new JComboBox();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        b_delete = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
        .border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
        .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
        awt.Font.BOLD,12),java.awt.Color.red), getBorder()))
        ; addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
        ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
        ;
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 86, 0, 617, 65, 71, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 448, 37, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cb_all, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_search ----
        b_search.setText("\u67e5\u8a62");
        b_search.addActionListener(e -> b_search(e));
        add(b_search, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, new GridBagConstraints(1, 3, 6, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- b_delete ----
        b_delete.setText("\u522a\u9664");
        b_delete.setBackground(Color.red);
        b_delete.addActionListener(e -> b_delete(e));
        add(b_delete, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JComboBox cb_all;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton b_delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
