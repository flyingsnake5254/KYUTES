/*
 * Created by JFormDesigner on Fri Dec 31 22:16:21 CST 2021
 */

package ExamManager;

import javax.swing.event.*;
import DataClass.*;
import org.jdesktop.swingx.JXDatePicker;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class CreateExam extends JPanel {
    public CreateExam() {
        initComponents();
        pageInitial();
    }
    
    private void changeVisibility(boolean b){
        lDegree.setVisible(b);
        slider1.setVisible(b);
        lStart.setVisible(b);
        lEnd.setVisible(b);
        startHour.setVisible(b);
        endHour.setVisible(b);
        startMinute.setVisible(b);
        endMinute.setVisible(b);
        lStartH.setVisible(b);
        lStartM.setVisible(b);
        lEndH.setVisible(b);
        lEndM.setVisible(b);
        lQB.setVisible(b);
        cbQB.setVisible(b);
        lQuestionNum.setVisible(b);
        spinner1.setVisible(b);
        bAdd.setVisible(b);
        table1.setVisible(b);
        bCreate.setVisible(b);
        datepickFrom.setVisible(b);
        datepickTo.setVisible(b);
    }
    
    private void pageInitial(){


        ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, getWidth(), 0, 0};
        ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, getHeight(), 0, 0};
        Groups groups = new Groups();
        for(Group g : groups.getGroups()){
            cbGroup.addItem(g.getGroupName());
        }
        slider1.setMinimum(0);

        int maxValue = Integer.parseInt(spinner1.getValue().toString());
        slider1.setMaximum(maxValue);
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("簡單"));
        labelTable.put(slider1.getMaximum(), new JLabel("困難"));
        slider1.setLabelTable(labelTable);
        slider1.setPaintLabels(true);
        slider1.setValue(1);

        datePickInitial();
        changeVisibility(false);
    }

    boolean locked = false;
    private void bSelect(ActionEvent e) {
        // TODO add your code here
        if(bSelect.getText().equals("確定")){
            changeVisibility(true);
            for(int i = 0 ; i < addQuestion.size() ; i ++){
                DefaultTableModel df = (DefaultTableModel) table1.getModel();
                df.removeRow(0);
            }
            addQuestion.clear();
            qbNames.clear();
            
            locked = true;
            selectGroup = cbGroup.getSelectedItem().toString();
            selectSuject = cbSuject.getSelectedItem().toString();
            cbGroup.removeAllItems();
            cbSuject.removeAllItems();
            cbGroup.addItem(selectGroup);
            cbSuject.addItem(selectSuject);
            bSelect.setText("變更");

            datepickFrom.setDate(new Date());
            datepickTo.setDate(new Date());
        }
        else if(bSelect.getText().equals("變更")){
            changeVisibility(false);
            locked = false;
            cbGroup.removeAllItems();
            cbSuject.removeAllItems();
            Groups groups = new Groups();
            for(Group g : groups.getGroups()){
                cbGroup.addItem(g.getGroupName());
            }
            bSelect.setText("確定");
        }
    }

    String selectGroup = "";
    String selectSuject = "";
    private void cbGroup(ActionEvent e) {
        // TODO add your code here
        cbSuject.removeAllItems();

        if(cbGroup.getSelectedItem() != null){
            if(!locked){
                selectGroup = cbGroup.getSelectedItem().toString();
                Groups groups = new Groups();
                Group group = groups.getGroup(selectGroup);
                ArrayList<Suject> sujects = group.getExamSujects();
                for(Suject s : sujects){
                    cbSuject.addItem(s.getName());
                }
            }
        }

    }

    private void cbSuject(ActionEvent e) {
        // TODO add your code here
        if(cbSuject.getSelectedItem() != null){
            cbQB.removeAllItems();
            String selectSuject = cbSuject.getSelectedItem().toString();
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(selectSuject);
            ArrayList<QuestionBank> questionBanks = suject.getQuestionBanks();
            for(QuestionBank qb : questionBanks)
                cbQB.addItem(qb.getName());
        }
    }

    private void spinner1StateChanged(ChangeEvent e) {
        // TODO add your code here
        int maxValue = Integer.parseInt(spinner1.getValue().toString());
        slider1.setMaximum(maxValue);
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("簡單"));
        labelTable.put(slider1.getMaximum(), new JLabel("困難"));
        slider1.setLabelTable(labelTable);
        slider1.setPaintLabels(true);
    }
    
    JXDatePicker datepickFrom , datepickTo;
    private void datePickInitial(){
        //---- datepickFrom ----
        datepickFrom = new JXDatePicker();
        Date date = new Date();
        datepickFrom.setDate(date);
        datepickFrom.setBounds(137, 83, 177, 24);
        panel1.add(datepickFrom, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        //---- datepickTo ----
        datepickTo = new JXDatePicker();
        Date date2 = new Date();
        datepickTo.setDate(date2);
        datepickTo.setBounds(137, 83, 177, 24);
        panel1.add(datepickTo, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
    }

    ArrayList<Question> addQuestion = new ArrayList<>();
    ArrayList<String> qbNames = new ArrayList<>();
    private void bAdd(ActionEvent e) {
        // TODO add your code here
        if(cbQB.getSelectedItem() != null && selectSuject != ""){
            int nowRow = 0;
            String selectQB = cbQB.getSelectedItem().toString();
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(selectSuject);
            QuestionBank questionBank = suject.getQuestionBank(selectQB);
            int questionNum = Integer.parseInt(spinner1.getValue().toString());
            int easy = 0 , medium = questionNum / 2 , difficult = slider1.getValue();

            int questionP = 3 * medium;
            easy = questionP - medium - difficult;
            double rand = 0;
            double easyP = (double) easy / questionP;
            double mediumP = easyP + ((double) medium / questionP);
            double difficultP = mediumP + ((double) difficult / questionP);

            int count = 0;
            while(count < questionNum){
                System.out.println(addQuestion.size());
                rand = Math.random();
                if(rand <= easyP){
                    Question q = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_EASY);
                    if(!addQuestion.contains(q)){
                        addQuestion.add(q);
                        qbNames.add(cbQB.getSelectedItem().toString());
                        count ++;
                    }
                }
                else if(rand <= mediumP){
                    Question q = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_MEDIUM);
                    if(!addQuestion.contains(q)){
                        addQuestion.add(q);
                        qbNames.add(cbQB.getSelectedItem().toString());
                        count ++;
                    }
                }
                else if(rand <= difficultP){
                    Question q = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_DIFFICULT);
                    if(!addQuestion.contains(q)){
                        addQuestion.add(q);
                        qbNames.add(cbQB.getSelectedItem().toString());
                        count ++;
                    }
                }

            }
            // show table
            DefaultTableModel df = new DefaultTableModel(addQuestion.size() , 5);
            table1.setModel(df);
            table1.getColumnModel().getColumn(0).setHeaderValue("題庫");
            table1.getColumnModel().getColumn(1).setHeaderValue("題目類型");
            table1.getColumnModel().getColumn(2).setHeaderValue("難易度");
            table1.getColumnModel().getColumn(3).setHeaderValue("內容");
            table1.getColumnModel().getColumn(4).setHeaderValue("答案");

            for(int i = 0 ; i < addQuestion.size() ; i ++){
                table1.setValueAt(qbNames.get(i) , nowRow , 0);
                table1.setValueAt(addQuestion.get(i).getQuestionType() , nowRow , 1);
                table1.setValueAt(addQuestion.get(i).getDegreeOfDifficulty() , nowRow , 2);
                table1.setValueAt(addQuestion.get(i).getContent() , nowRow , 3);
                table1.setValueAt(addQuestion.get(i).getAnswer() , nowRow , 4);
                nowRow ++;
            }

            table1.setVisible(true);
        }
    }

    private void bCreate(ActionEvent e) {
        // TODO add your code here
        String[] dateStart = datepickFrom.getDate().toString().split("\\s+");
        String[] dateEnd = datepickTo.getDate().toString().split("\\s+");
        String startTime = dateStart[5] + "/"
                + Data.MONTH.get(dateStart[1]) + "/"
                + dateStart[2] + " "
                + startHour.getValue().toString() + ":"
                + startMinute.getValue().toString();
        String endTime = dateEnd[5] + "/"
                + Data.MONTH.get(dateEnd[1]) + "/"
                + dateEnd[2] + " "
                + endHour.getValue().toString() + ":"
                + endMinute.getValue().toString();
        Exams exams = new Exams();
        exams.createExam(selectGroup , selectSuject , startTime , endTime , addQuestion , qbNames);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel2 = new JPanel();
        panel1 = new JPanel();
        label8 = new JLabel();
        label1 = new JLabel();
        cbGroup = new JComboBox();
        label2 = new JLabel();
        cbSuject = new JComboBox();
        bSelect = new JButton();
        lStart = new JLabel();
        startHour = new JSpinner();
        lStartH = new JLabel();
        startMinute = new JSpinner();
        lStartM = new JLabel();
        lDegree = new JLabel();
        slider1 = new JSlider();
        lEnd = new JLabel();
        endHour = new JSpinner();
        lEndH = new JLabel();
        endMinute = new JSpinner();
        lEndM = new JLabel();
        lQB = new JLabel();
        cbQB = new JComboBox();
        lQuestionNum = new JLabel();
        spinner1 = new JSpinner();
        bAdd = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bCreate = new JButton();

        //======== this ========
        setBackground(new Color(153, 255, 153));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
        .EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax
        .swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,
        12),java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans
        .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.
        getPropertyName()))throw new RuntimeException();}});

        //======== panel2 ========
        {
            panel2.setBackground(new Color(255, 153, 153));
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {1030, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {676, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //======== panel1 ========
            {
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 222, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 457, 0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label8 ----
                label8.setText("             ");
                panel1.add(label8, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label1 ----
                label1.setText("\u6e2c\u9a57\u7fa4\u7d44");
                panel1.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- cbGroup ----
                cbGroup.addActionListener(e -> cbGroup(e));
                panel1.add(cbGroup, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("\u6e2c\u9a57\u79d1\u76ee");
                panel1.add(label2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- cbSuject ----
                cbSuject.addActionListener(e -> cbSuject(e));
                panel1.add(cbSuject, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bSelect ----
                bSelect.setText("\u78ba\u5b9a");
                bSelect.addActionListener(e -> bSelect(e));
                panel1.add(bSelect, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lStart ----
                lStart.setText("\u958b\u59cb\u6e2c\u9a57\u6642\u9593");
                panel1.add(lStart, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- startHour ----
                startHour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
                panel1.add(startHour, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lStartH ----
                lStartH.setText("\u6642");
                panel1.add(lStartH, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- startMinute ----
                startMinute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
                panel1.add(startMinute, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lStartM ----
                lStartM.setText("\u5206");
                panel1.add(lStartM, new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lDegree ----
                lDegree.setText("\u96e3\u6613\u5ea6");
                panel1.add(lDegree, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(slider1, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lEnd ----
                lEnd.setText("\u7d50\u675f\u6e2c\u9a57\u6642\u9593");
                panel1.add(lEnd, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- endHour ----
                endHour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
                panel1.add(endHour, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lEndH ----
                lEndH.setText("\u6642");
                panel1.add(lEndH, new GridBagConstraints(9, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- endMinute ----
                endMinute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
                panel1.add(endMinute, new GridBagConstraints(10, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lEndM ----
                lEndM.setText("\u5206");
                panel1.add(lEndM, new GridBagConstraints(11, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lQB ----
                lQB.setText("\u984c\u5eab");
                panel1.add(lQB, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(cbQB, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lQuestionNum ----
                lQuestionNum.setText("\u984c\u6578");
                panel1.add(lQuestionNum, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- spinner1 ----
                spinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
                spinner1.addChangeListener(e -> spinner1StateChanged(e));
                panel1.add(spinner1, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bAdd ----
                bAdd.setText("\u6dfb\u52a0");
                bAdd.addActionListener(e -> bAdd(e));
                panel1.add(bAdd, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                panel1.add(scrollPane1, new GridBagConstraints(0, 4, 15, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bCreate ----
                bCreate.setText("\u5efa\u7acb\u6e2c\u9a57");
                bCreate.addActionListener(e -> bCreate(e));
                panel1.add(bCreate, new GridBagConstraints(14, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            panel2.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label8;
    private JLabel label1;
    private JComboBox cbGroup;
    private JLabel label2;
    private JComboBox cbSuject;
    private JButton bSelect;
    private JLabel lStart;
    private JSpinner startHour;
    private JLabel lStartH;
    private JSpinner startMinute;
    private JLabel lStartM;
    private JLabel lDegree;
    private JSlider slider1;
    private JLabel lEnd;
    private JSpinner endHour;
    private JLabel lEndH;
    private JSpinner endMinute;
    private JLabel lEndM;
    private JLabel lQB;
    private JComboBox cbQB;
    private JLabel lQuestionNum;
    private JSpinner spinner1;
    private JButton bAdd;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
