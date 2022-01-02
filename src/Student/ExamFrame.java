/*
 * Created by JFormDesigner on Sun Jan 02 23:32:14 CST 2022
 */

package Student;

import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class ExamFrame extends JFrame {
    private User nowUser;
    private Exam exam;
    private ArrayList<Question> questions;
    public ExamFrame(User nowUser , Exam exam) {
        initComponents();
        this.nowUser = nowUser;
        this.exam = exam;
        this.questions = this.exam.getQuestions();
        initial();
        this.setVisible(true);
    }

    int count = 1;
    JPanel panel;
    private void initial(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));
        for(int i = 0 ; i < questions.size() ; i ++){
            panel.add(new ExamPanel(questions.get(i) , count ++));
        }
        scrollPane1.setViewportView(panel);
        revalidate();
    }

    ArrayList<String> youAns = new ArrayList<>();
    private void bSubmit(ActionEvent e) {
        // TODO add your code here
        System.out.println("click");
        for(int i = 0 ; i < panel.getComponentCount() ; i ++){
            Object obj = panel.getComponent(i);
            if(obj instanceof ExamPanel){
                System.out.println("!!!!!!!!!!!!!!!!");
                ExamPanel examPanel = (ExamPanel) obj;
                for(int j = 0 ; j < examPanel.getComponentCount() ; j ++){
                    Object obj2 = examPanel.getComponent(j);
                    if(obj2 instanceof JPanel) {
                        JPanel jp = (JPanel) obj2;
                        for(int k = 0 ; k < jp.getComponentCount() ; k ++){
                            Object obj3 = jp.getComponent(k);
                            if(obj3 instanceof JLabel){
                                JLabel l = (JLabel) obj3;
                                l.setVisible(true);
                                youAns.add(l.getText());
                                l.setText("答案：" + questions.get(i).getAnswer());
                            }
                        }
                    }
                }
            }
        }
        int correct = 0;
        for(int i = 0 ; i < questions.size() ; i ++){
            if(questions.get(i).getAnswer().equals(youAns.get(i))){
                correct ++;
            }
        }
        double grade = Math.round(getExamGrade(correct , questions.size());
        Dialog.message("得分：" + String.valueOf(grade));
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("insert into examGrade (id,account,examGrade) values " +
                    "('" + exam.getID() + "','" + nowUser.getAccount() + "','" + String.valueOf(grade) + "')");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private double getExamGrade(int correct , int totalQuestionNum){
        if(correct == 0) return 0;
        if(correct == totalQuestionNum) return 100;
        return (100 * ((double) correct / totalQuestionNum));
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        bSubmit = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {805, 81, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {603, 40, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //======== panel2 ========
            {

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                            .addContainerGap())
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            panel1.add(panel2, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- bSubmit ----
            bSubmit.setText("\u4ea4\u5377");
            bSubmit.addActionListener(e -> bSubmit(e));
            panel1.add(bSubmit, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JButton bSubmit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
