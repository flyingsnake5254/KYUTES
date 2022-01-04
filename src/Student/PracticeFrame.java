/*
 * Created by JFormDesigner on Sun Jan 02 20:49:26 CST 2022
 */

package Student;

import java.awt.event.*;
import javax.swing.plaf.*;
import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class PracticeFrame extends JFrame {
    private User nowUser;
    private String selectSuject;
    private ArrayList<String> qbName;
    private int degreeValue = 1;
    private ArrayList<Question> questionsList;
    private ArrayList<String> questionsQB;
    private ArrayList<String> studentAns;
    private int questionTotalNum;
    int count = 0;
    int correct = 0;
    public PracticeFrame(User nowUser , String selectSuject , ArrayList<String> qbName , int questionTotalNum) {
        initComponents();
        this.nowUser = nowUser;
        this.selectSuject = selectSuject;
        this.qbName = qbName;
        this.questionTotalNum = questionTotalNum;
        this.nowUser = nowUser;
//        ((GridBagLayout)mainpanel.getLayout()).columnWidths = new int[] {0, 0, 0, (this.getWidth() - bAccountManage.getWidth() - 200), 0, 0};
//        ((GridBagLayout)mainpanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, (this.getHeight() - 200), 0, 0};
        pageInitial();
    }

    Question q;
    ButtonGroup bg;
    private void pageInitial(){
        questionsList = new ArrayList<>();
        questionsQB = new ArrayList<>();
        studentAns = new ArrayList<>();
        bg = new ButtonGroup();
        bg.add(c1);
        bg.add(c2);
        bg.add(c3);
        bg.add(c4);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String path = System.getProperty("user.dir");
        this.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        this.setVisible(true);
        this.setTitle("KYUTES 學生");
        this.setSize(1000,800);
        this.add(mainpanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        c1.setVisible(false);
        c2.setVisible(false);
        c3.setVisible(false);
        c4.setVisible(false);
        lAns.setVisible(false);
        tfAns.setVisible(false);
        setQuestion();
    }
    
    private void setQuestion(){
        q = generatedQuestion(degreeValue);
        questionsList.add(q);
        lQuestionNum.setText(String.valueOf(++count));
        taContent.setText(q.getContent());

        if(q.getQuestionType().equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG)){
            c1.setVisible(true);
            c2.setVisible(true);
            c3.setVisible(false);
            c4.setVisible(false);
            lAns.setVisible(false);
            tfAns.setVisible(false);
            
            c1.setText("O");
            c2.setText("X");
        }
        else if(q.getQuestionType().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
            c1.setVisible(true);
            c2.setVisible(true);
            c3.setVisible(true);
            c4.setVisible(true);
            lAns.setVisible(false);
            tfAns.setVisible(false);

            c1.setText(q.getC1());
            c2.setText(q.getC2());
            c3.setText(q.getC3());
            c4.setText(q.getC4());
        }
        
        else{
            c1.setVisible(false);
            c2.setVisible(false);
            c3.setVisible(false);
            c4.setVisible(false);
            lAns.setVisible(true);
            tfAns.setVisible(true);
            tfAns.setText("");
        }
    }

    private void bNext(ActionEvent e) {
        // TODO add your code here
        String nowAns = "";
        if(q.getQuestionType().equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG)){
            if(c1.isSelected()) nowAns = c1.getText();
            else if(c2.isSelected()) nowAns = c2.getText();
            else nowAns = "";
        }
        else if(q.getQuestionType().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
            if(c1.isSelected()) nowAns = c1.getText();
            else if(c2.isSelected()) nowAns = c2.getText();
            else if(c3.isSelected()) nowAns = c3.getText();
            else if(c4.isSelected()) nowAns = c4.getText();
            else nowAns = "";
        }
        else{
            if(tfAns.getText() != null) nowAns = tfAns.getText();
            else nowAns = "";
        }
        studentAns.add(nowAns);
        if(count < questionTotalNum){
            if(nowAns.equals(q.getAnswer())) {
                degreeValue = adjustQuestion(degreeValue, true);
                correct ++;
                System.out.println("O");
            }
            else {
                degreeValue = adjustQuestion(degreeValue, false);
                System.out.println("X");
            }
            setQuestion();
        }
        else{
            if(nowAns.equals(q.getAnswer())) {
                degreeValue = adjustQuestion(degreeValue, true);
                correct ++;
                System.out.println("O");
            }
            else {
                degreeValue = adjustQuestion(degreeValue, false);
                System.out.println("X");
            }
            System.out.println(correct + " " + questionTotalNum);
            Dialog.message("分數：" + String.valueOf(Math.round(getExamGrade(correct , questionTotalNum))));
            new ShowExam(questionsList , studentAns , correct);
            this.dispose();
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        mainpanel = new JPanel();
        panel1 = new JPanel();
        lQuestionNum = new JLabel();
        lDegree = new JLabel();
        taContent = new JTextArea();
        c1 = new JRadioButton();
        c2 = new JRadioButton();
        c3 = new JRadioButton();
        c4 = new JRadioButton();
        lAns = new JLabel();
        tfAns = new JTextField();
        bNext = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainpanel ========
        {
            mainpanel.setBackground(new Color(214, 214, 214));
            mainpanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
            .BOLD,12),java.awt.Color.red),mainpanel. getBorder()));mainpanel. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r"
            .equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== panel1 ========
            {
                panel1.setBackground(new Color(214, 214, 214));
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 264, 311, 67, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 160, 0, 0, 0, 0, 0, 36, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                panel1.add(lQuestionNum, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(lDegree, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(taContent, new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(c1, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(c2, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(c3, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(c4, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lAns ----
                lAns.setText("\u7b54\u6848\uff1a");
                panel1.add(lAns, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel1.add(tfAns, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bNext ----
                bNext.setText("next");
                bNext.addActionListener(e -> bNext(e));
                panel1.add(bNext, new GridBagConstraints(4, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }

            GroupLayout mainpanelLayout = new GroupLayout(mainpanel);
            mainpanel.setLayout(mainpanelLayout);
            mainpanelLayout.setHorizontalGroup(
                mainpanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            mainpanelLayout.setVerticalGroup(
                mainpanelLayout.createParallelGroup()
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private int adjustQuestion(int value , boolean b){
        if(b){
            if(value < 10) value ++;
        }
        else{
            if(value > 1) value --;
        }
        return value;
    }

    private String adjustDegree(int value){
        if(value >= 1 && value <= 3) return Data.DEGREE_OF_DIFFICULTY_EASY;
        else if(value >= 4 && value <= 7) return Data.DEGREE_OF_DIFFICULTY_MEDIUM;
        else return Data.DEGREE_OF_DIFFICULTY_DIFFICULT;
    }

    private double getExamGrade(int correct , int totalQuestionNum){
        if(correct == 0) return 0;
        if(correct == totalQuestionNum) return 100;
        return (100 * ((double) correct / totalQuestionNum));
    }

    private Question generatedQuestion(int degreeValue){
        Random random = new Random();
        IntStream intStream = random.ints(1,0,qbName.size());
        int[] arr = intStream.toArray();
        String degree = adjustDegree(degreeValue);
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(selectSuject);
        // 隨機題庫
        QuestionBank questionBank = suject.getQuestionBank(qbName.get(arr[0]));
        ArrayList<Question> questions = questionBank.getQuestions();
        questionsQB.add(questionBank.getName());
        //隨機題目
        Question question = null;
        while(question == null){
            intStream = random.ints(1,0,questions.size());
            arr = intStream.toArray();
            if(questions.get(arr[0]).getDegreeOfDifficulty().equals(degree)){
                question = questions.get(arr[0]);
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        return question;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel mainpanel;
    private JPanel panel1;
    private JLabel lQuestionNum;
    private JLabel lDegree;
    private JTextArea taContent;
    private JRadioButton c1;
    private JRadioButton c2;
    private JRadioButton c3;
    private JRadioButton c4;
    private JLabel lAns;
    private JTextField tfAns;
    private JButton bNext;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
