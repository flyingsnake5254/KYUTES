/*
 * Created by JFormDesigner on Mon Jan 03 01:29:47 CST 2022
 */

package Student;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.plaf.*;

import DataClass.*;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class SearchGrade extends JPanel {
    private User nowUser;
    public SearchGrade(User nowUser) {
        initComponents();
        this.nowUser = nowUser;
        pageInitial();
    }

    private void pageInitial(){
        table1.setVisible(false);
        Groups groups = new Groups();
        Group group = groups.getGroup(nowUser.getStudentGroup());
        cbSuject.addItem("全部");
        for(Suject s : group.getExamSujects())
            cbSuject.addItem(s.getName());
    }

    private void bSearch(ActionEvent e) {
        // TODO add your code here
        if(cbSuject.getSelectedItem() != null){
            HashMap<String,String> grade = new HashMap<>();
            Statement st = new GetDBdata().getStatement();
            ArrayList<Exam> show = new ArrayList<>();
            try {
                st.execute("select id,examGrade from examGrade where account='"+nowUser.getAccount()+"'");
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    grade.put(rs.getString("id") , rs.getString("examGrade"));
                }
                String selectSuject = cbSuject.getSelectedItem().toString();
                Exams exams = new Exams();
                ArrayList<Exam> examArrayList;
                if(selectSuject.equals("全部")){
                    examArrayList = exams.getGroupExam(nowUser.getStudentGroup());
                }
                else{
                    examArrayList = exams.getGroupSujectExam(nowUser.getStudentGroup() , selectSuject);
                }
                for(int i = 0 ; i < examArrayList.size() ; i ++){
                    if(grade.containsKey(examArrayList.get(i).getID())){
                        show.add(examArrayList.get(i));
                    }
                }

                DefaultTableModel df = new DefaultTableModel(show.size() , 5){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(df);
                table1.getColumnModel().getColumn(0).setHeaderValue("測驗ID");
                table1.getColumnModel().getColumn(1).setHeaderValue("科目");
                table1.getColumnModel().getColumn(2).setHeaderValue("測驗開始時間");
                table1.getColumnModel().getColumn(3).setHeaderValue("測驗結束時間");
                table1.getColumnModel().getColumn(4).setHeaderValue("成績");
                for(int i = 0 ; i < show.size() ; i ++){
                    table1.setValueAt(show.get(i).getID() , i , 0);
                    table1.setValueAt(show.get(i).getSujectName() , i , 1);
                    table1.setValueAt(show.get(i).getStartTime() , i , 2);
                    table1.setValueAt(show.get(i).getEndTime() , i , 3);
                    table1.setValueAt(grade.get(show.get(i).getID()) , i , 4);
                }
                table1.setVisible(true);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        cbSuject = new JComboBox();
        bSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 181, 0, 0, 617, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 571, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u6e2c\u9a57\u6210\u7e3e\u67e5\u8a62");
            panel1.add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u79d1\u76ee");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbSuject, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearch ----
            bSearch.setText("\u67e5\u8a62");
            bSearch.addActionListener(e -> bSearch(e));
            panel1.add(bSearch, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 5, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JComboBox cbSuject;
    private JButton bSearch;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
