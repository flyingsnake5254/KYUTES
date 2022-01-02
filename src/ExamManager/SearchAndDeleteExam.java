/*
 * Created by JFormDesigner on Sun Jan 02 09:34:31 CST 2022
 */

package ExamManager;

import java.awt.event.*;

import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class SearchAndDeleteExam extends JPanel {
    public SearchAndDeleteExam() {
        initComponents();
        pageInitial();
    }

    private void pageInitial(){
        // set group comboBox
        cbGroup.addItem("全部");
        Groups groups = new Groups();
        for(Group g : groups.getGroups()){
            cbGroup.addItem(g.getGroupName());
        }

        table1.setVisible(false);
        bDelete.setVisible(false);
        lExamQuestion.setVisible(false);

    }
    String selectGroup = "" , selectSuject = "";
    private void bSearch(ActionEvent e) {
        // TODO add your code here
        // 群組 科目 startTime endTime questionNum
        Exams exams = new Exams();
        selectGroup = cbGroup.getSelectedItem().toString();
        ArrayList<Exam> examArrayList = new ArrayList<>();
        if(selectGroup.equals("全部")){
            examArrayList = exams.getExamInfos();
        }
        else{
            selectSuject = cbSuject.getSelectedItem().toString();
            if(selectSuject.equals("全部")){
                examArrayList = exams.getGroupExam(selectGroup);

            }
            else{
                examArrayList = exams.getGroupSujectExam(selectGroup,selectSuject);
            }
        }

        // show table
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
        bDelete.setVisible(true);
        lExamQuestion.setVisible(true);
    }

    private void cbGroup(ActionEvent e) {
        // TODO add your code here
        if(cbGroup.getSelectedItem().toString().equals("全部")){
            lSuject.setVisible(false);
            cbSuject.setVisible(false);
        }
        else{
            lSuject.setVisible(true);
            cbSuject.setVisible(true);
            cbSuject.removeAllItems();
            String sGroup = cbGroup.getSelectedItem().toString();
            Groups groups = new Groups();
            Group group = groups.getGroup(sGroup);
            ArrayList<Suject> sujects = group.getExamSujects();
            if(sujects.size() == 0){
                Dialog.message("此群組尚未有測驗科目");
            }
            else{
                cbSuject.addItem("全部");
                for(Suject suject : sujects){
                    cbSuject.addItem(suject.getName());
                }
            }
        }
    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        int[] selectIndex = table1.getSelectedRows();
        ArrayList<String> examID = new ArrayList<>();
        for(int i = 0 ; i < selectIndex.length ; i ++){
            examID.add(table1.getValueAt(selectIndex[i],0).toString());
        }

        for(int i = 0 ; i < examID.size() ; i ++){
            Exams exams = new Exams();
            exams.deleteExam(examID.get(i));
            // remove table row
            for(int j = 0 ; j < table1.getRowCount() ; j ++){
                if(table1.getValueAt(j,0).toString().equals(examID.get(i))){
                    DefaultTableModel df = (DefaultTableModel) table1.getModel();
                    df.removeRow(j);
                }
            }
        }
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(e.getClickCount() == 2){
            int[] selectIndex = table1.getSelectedRows();
            Exams exams = new Exams();
            Exam selectExam = exams.getExam(table1.getValueAt(selectIndex[0],0).toString());
            if(selectExam != null){
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                ArrayList<Question> questions = selectExam.getQuestions();
                for(int i = 0 ; i < questions.size() ; i ++){
                    panel.add(new QuestionPanel(questions.get(i) , i + 1));
                }
                scrollPane2.setViewportView(panel);
                scrollPane2.revalidate();
            }
        }
        /*
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.red);
        for(int i = 0 ; i < count ; i ++){
            panel.add(new JLabel(String.valueOf(i + 1)));
            panel.add(new QuestionPanel());
        }
        scrollPane2.setViewportView(panel);
        scrollPane2.revalidate();
        count ++;
         */
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        cbGroup = new JComboBox();
        lSuject = new JLabel();
        cbSuject = new JComboBox();
        bSearch = new JButton();
        bDelete = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        lExamQuestion = new JLabel();
        scrollPane2 = new JScrollPane();

        //======== this ========
        setBackground(new Color(153, 255, 204));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(255, 255, 102));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 153, 0, 169, 0, 0, 152, 198, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 142, 35, 376, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u6e2c\u9a57\u7fa4\u7d44");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbGroup ----
            cbGroup.addActionListener(e -> cbGroup(e));
            panel1.add(cbGroup, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lSuject ----
            lSuject.setText("\u6e2c\u9a57\u79d1\u76ee");
            panel1.add(lSuject, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbSuject, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearch ----
            bSearch.setText("\u67e5\u8a62");
            bSearch.addActionListener(e -> bSearch(e));
            panel1.add(bSearch, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bDelete ----
            bDelete.setText("\u522a\u9664");
            bDelete.addActionListener(e -> bDelete(e));
            panel1.add(bDelete, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(2, 0));
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        table1MouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 9, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lExamQuestion ----
            lExamQuestion.setText("\u6e2c\u9a57\u984c\u76ee");
            panel1.add(lExamQuestion, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(scrollPane2, new GridBagConstraints(1, 4, 9, 3, 0.0, 0.0,
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
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JComboBox cbGroup;
    private JLabel lSuject;
    private JComboBox cbSuject;
    private JButton bSearch;
    private JButton bDelete;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel lExamQuestion;
    private JScrollPane scrollPane2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
