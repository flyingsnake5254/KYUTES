/*
 * Created by JFormDesigner on Sun Jan 02 22:45:29 CST 2022
 */

package Student;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.plaf.*;

import DataClass.*;
import DataClass.Dialog;
import ExamManager.CreateExam;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class ExamArea extends JPanel {
    private User nowUser;
    public ExamArea(User nowUser) {
        this.nowUser = nowUser;
        initComponents();
        pageInitial();
    }

    private void pageInitial(){
        table1.setVisible(false);
        if(nowUser.getStudentGroup().equals("")){
            Dialog.wrong("未加入群組");
        }
        else{
            cbSuject.addItem("全部");
            Groups groups = new Groups();
            Group group = groups.getGroup(nowUser.getStudentGroup());
            for(Suject s : group.getExamSujects())
                cbSuject.addItem(s.getName());
        }
    }

    private void bSearch(ActionEvent e) {
        // TODO add your code here
        Exams exams = new Exams();
        ArrayList<Exam> examArrayList;
        if(cbSuject.getSelectedItem().toString().equals("全部")){
            examArrayList = exams.getGroupExam(nowUser.getStudentGroup());
        }
        else{
            examArrayList = exams.getGroupSujectExam(nowUser.getStudentGroup() , cbSuject.getSelectedItem().toString());
        }
        DefaultTableModel df = new DefaultTableModel(examArrayList.size() , 6){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("測驗編號");
        table1.getColumnModel().getColumn(1).setHeaderValue("測驗群組");
        table1.getColumnModel().getColumn(2).setHeaderValue("測驗科目");
        table1.getColumnModel().getColumn(3).setHeaderValue("測驗開始時間");
        table1.getColumnModel().getColumn(4).setHeaderValue("測驗結束時間");
        table1.getColumnModel().getColumn(5).setHeaderValue("題目數");

        int nowRow = 0;
        for(Exam exam : examArrayList){
            table1.setValueAt(exam.getID(),nowRow,0);
            table1.setValueAt(exam.getGroupName(),nowRow,1);
            table1.setValueAt(exam.getSujectName(),nowRow,2);
            table1.setValueAt(exam.getStartTime(),nowRow,3);
            table1.setValueAt(exam.getEndTime(),nowRow,4);
            table1.setValueAt(exam.getQuestions().size(),nowRow,5);
            nowRow ++;
        }
        table1.setVisible(true);
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(e.getClickCount() == 2){
            int[] selectIndex = table1.getSelectedRows();
            String examID = table1.getValueAt(selectIndex[0] , 0).toString();
            Exams exams = new Exams();
            Exam exam = exams.getExam(examID);
            if(dateCheck(exam.getStartTime() , exam.getEndTime())){
                Statement st = new GetDBdata().getStatement();
                ArrayList<String> hasTest = new ArrayList<>();
                try {
                    st.execute("select account from examState where id='" + examID + "'");
                    ResultSet rs = st.getResultSet();
                    while(rs.next()){
                        hasTest.add(rs.getString("account"));
                    }
                    if(hasTest.contains(nowUser.getAccount())){
                        Dialog.wrong("已參加過此測驗");
                    }
                    else{
                        st.execute("insert into examState (id,account) values ('" + exam.getID() + "','" + nowUser.getAccount() + "')");
                        new ExamFrame(this.nowUser , exam);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                Dialog.wrong("現在非測驗時間");
            }

        }
    }

    public boolean dateCheck(String startTime , String endTime){
        boolean state = false;


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            String[] t1 = LocalTime.now().toString().split(":");
            Date now = simpleDateFormat2.parse(LocalDate.now().toString() + " " + t1[0] + ":" + t1[1]);
            Date date1 = simpleDateFormat.parse(startTime);
            Date date2 = simpleDateFormat.parse(endTime);
            if((now.after(date1) || now.equals(date1)) && (now.before(date2) || now.equals(date2))) return true;
            else return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return state;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        cbSuject = new JComboBox();
        bSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
        javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
        . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
        .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
        PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .
        equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 165, 84, 692, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 592, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
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

                //---- table1 ----
                table1.setBackground(new Color(214, 214, 214));
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        table1MouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0,
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
    private JComboBox cbSuject;
    private JButton bSearch;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
