/*
 * Created by JFormDesigner on Tue Dec 21 16:56:36 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class SubjectSearch extends JPanel {
    
    public SubjectSearch() {
        initComponents();
        cb_suject.addItem("全部");
        table1.setVisible(false);
        b_delete.setVisible(false);
    }

    private void b_search(ActionEvent e) {
        // TODO add your code here
        ArrayList<String> sujects = new ArrayList<>();
        ArrayList<String> question_num = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from suject");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                sujects.add(rs.getString("name"));
                question_num.add(rs.getString("question_num"));
            }
            DefaultTableModel df = new DefaultTableModel(sujects.size(),2);
            table1.setModel(df);
            table1.getColumnModel().getColumn(0).setHeaderValue("科目名稱");
            table1.getColumnModel().getColumn(1).setHeaderValue("題目數量");
            for(int i = 0 ; i < sujects.size() ; i ++){
                table1.setValueAt(sujects.get(i),i,0);
                table1.setValueAt(question_num.get(i),i,1);
            }
            table1.setVisible(true);
            b_delete.setVisible(true);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void b_delete(ActionEvent e) {
        // TODO add your code here
        int[] select = table1.getSelectedRows();
        ArrayList<String> selectSuject = new ArrayList<>();
        for(int i = 0 ; i < select.length ; i ++){
            selectSuject.add(table1.getModel().getValueAt(select[i],0).toString());
        }
        // delete from suject table
        Statement st = new GetDBdata().getStatement();
        for(int i = 0 ; i < selectSuject.size() ; i ++){
            try {
                st.execute("delete from suject where name='" + selectSuject.get(i) + "'");
                st.execute("drop table " + selectSuject.get(i));
                //取得有這科目的所有群組
                // all_group suject num - 1
                // table group name delete
                ArrayList<String> allGroups = new ArrayList<>();
                ArrayList<String> groupSujectNum = new ArrayList<>();
                st.execute("select name,suject_num from all_group");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    allGroups.add(rs.getString("name"));
                    groupSujectNum.add(rs.getString("suject_num"));
                }
                ArrayList<String> groupHasSuject = new ArrayList<>();
                for(int j = 0 ; j < allGroups.size() ; j ++){
                    ArrayList<String> groupSuject = new ArrayList<>();
                    st.execute("select name from " + allGroups.get(j));
                    rs = st.getResultSet();
                    while(rs.next()){
                        groupSuject.add(rs.getString("name"));
                    }
                    if(groupSuject.contains(selectSuject.get(i))){
                        groupHasSuject.add(allGroups.get(j));
                    }
                }
                for(int j = 0 ; j < groupHasSuject.size() ; j ++){
                    st.execute("update all_group set suject_num='"
                            + (Integer.parseInt(groupSujectNum.get(allGroups.indexOf(groupHasSuject.get(j))))-1)
                    + "' where name='"+groupHasSuject.get(j)+"'");
                    st.execute("delete from "+groupHasSuject.get(j)
                            +" where name='"+selectSuject.get(i)+"'");
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            for(int j = 0 ; j < table1.getRowCount() ; j ++){
                if(table1.getModel().getValueAt(j,0).equals(selectSuject.get(i))){
                    DefaultTableModel dfm = (DefaultTableModel) table1.getModel();
                    dfm.removeRow(j);
                    break;
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        cb_suject = new JComboBox();
        b_search = new JButton();
        b_delete = new JButton();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder ( 0
        , 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
        , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,
         getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));
            panel1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {46, 55, 148, 0, 75, 406, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {45, 0, 482, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_suject, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_search ----
            b_search.setText("\u641c\u5c0b");
            b_search.addActionListener(e -> b_search(e));
            panel1.add(b_search, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_delete ----
            b_delete.setText("\u522a\u9664\u79d1\u76ee");
            b_delete.setBackground(Color.red);
            b_delete.addActionListener(e -> b_delete(e));
            panel1.add(b_delete, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== panel2 ========
            {
                panel2.setBackground(new Color(214, 214, 214));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                );
            }
            panel1.add(panel2, new GridBagConstraints(1, 2, 5, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JComboBox cb_suject;
    private JButton b_search;
    private JButton b_delete;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
