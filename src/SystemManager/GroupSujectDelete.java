/*
 * Created by JFormDesigner on Thu Dec 23 19:56:47 CST 2021
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
public class GroupSujectDelete extends JPanel {
    public GroupSujectDelete() {
        initComponents();
        initCombo();
        table1.setVisible(false);
        bDelete.setVisible(false);
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
                cbGroup.addItem(groups.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String selectGroup;
    private void b_search(ActionEvent e) {
        // TODO add your code here
        boolean searchState = true;
        selectGroup = cbGroup.getSelectedItem().toString();
        // get group suject
        ArrayList<String> sujects = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select name from "+selectGroup);
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                sujects.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            searchState = false;
        }
        // show on table
        DefaultTableModel df = new DefaultTableModel(sujects.size(),1);
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("科目");
        for(int i = 0 ; i < sujects.size() ; i ++){
            table1.setValueAt(sujects.get(i),i,0);
        }
        table1.setVisible(true);
        bDelete.setVisible(true);
    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        // sub table all_group's suject_num
        // delete from group's table
        boolean deleteState = true;
        int[] selectIndex = table1.getSelectedRows();
        if(selectIndex.length == 0){
            JOptionPane.showMessageDialog(
                    null,
                    "尚未選擇欲刪除之科目",
                    "錯誤",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else{
            // get select suject
            ArrayList<String> selectSuject = new ArrayList<>();
            for(int i = 0 ; i < selectIndex.length ; i ++){
                selectSuject.add(table1.getValueAt(selectIndex[i],0).toString());
            }

            // delete suject from table
            Statement st = new GetDBdata().getStatement();
            for(int i = 0 ; i < selectSuject.size() ; i ++){
                try {
                    st.execute("delete from "+selectGroup+" where name='"+
                    selectSuject.get(i)+"'");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    deleteState = false;
                }
            }
            int subNum = selectSuject.size();
            String oriNum = "";
            try {
                st.execute("select suject_num from all_group where name='"+selectGroup+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    oriNum = rs.getString("suject_num");
                }
                String newNum = String.valueOf(Integer.parseInt(oriNum)-subNum);
                st.execute("update all_group set suject_num='"+newNum+"' " +
                        "where name='"+selectGroup+"'");
            } catch (SQLException ex) {
                ex.printStackTrace();
                deleteState = false;
            }
            if(deleteState){
                JOptionPane.showMessageDialog(null,
                        "刪除成功","訊息",JOptionPane.DEFAULT_OPTION);
                // delete table row
                for(int i = 0 ; i < selectSuject.size() ; i ++){
                    for(int j = 0 ; j < table1.getRowCount() ; j ++){
                        if(table1.getValueAt(j,0).toString().equals(selectSuject.get(i))){
                            DefaultTableModel df = (DefaultTableModel) table1.getModel();
                            df.removeRow(j);
                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(
                        null,
                        "刪除失敗",
                        "錯誤",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        cbGroup = new JComboBox();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bDelete = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
        swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border
        . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067"
        ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
        .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 178, 0, 595, 87, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 506, 31, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u7fa4\u7d44");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(cbGroup, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
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
    private JComboBox cbGroup;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
