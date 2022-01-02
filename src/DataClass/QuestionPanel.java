/*
 * Created by JFormDesigner on Sun Jan 02 14:23:55 CST 2022
 */

package DataClass;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class QuestionPanel extends JPanel {
    Question question ;
    int questionNum;
    private String yourAns;
    private boolean showYourAns;
    public QuestionPanel(Question question , int questionNum , boolean showYourAns , String yourAns) {
        initComponents();
        tpContent.setEditable(false);
        this.showYourAns = showYourAns;
        this.yourAns = yourAns;
        this.question = question;
        this.questionNum = questionNum;
        panelInitial();
    }
    
    private void panelInitial(){
        lQuestionNum.setText(String.valueOf(this.questionNum));
        lQuestionNum.setVisible(true);
        tpContent.setText(this.question.getContent());
        tpContent.setVisible(true);
        if(question.getQuestionType().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
            lChoice1.setVisible(true);
            lChoice2.setVisible(true);
            lChoice3.setVisible(true);
            lChoice4.setVisible(true);
            lC1.setText(this.question.getC1());
            lC2.setText(this.question.getC2());
            lC3.setText(this.question.getC3());
            lC4.setText(this.question.getC4());
            lC1.setVisible(true);
            lC2.setVisible(true);
            lC3.setVisible(true);
            lC4.setVisible(true);
        }
        else{
            lChoice1.setVisible(false);
            lChoice2.setVisible(false);
            lChoice3.setVisible(false);
            lChoice4.setVisible(false);

            lC1.setVisible(false);
            lC2.setVisible(false);
            lC3.setVisible(false);
            lC4.setVisible(false);
        }
        
        lAnswer.setVisible(true);
        lAns.setText(this.question.getAnswer());
        lAns.setVisible(true);
        if(showYourAns){
            lYourAns2.setVisible(true);
            lYourAns.setVisible(true);
            lYourAns2.setText(yourAns);
            if(this.question.getAnswer().equals(yourAns)){
                lYourAns2.setForeground(Color.BLACK);
            }
            else lYourAns2.setForeground(Color.red);
        }
        else{
            lYourAns2.setVisible(false);
            lYourAns.setVisible(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        lQuestionNum = new JLabel();
        scrollPane1 = new JScrollPane();
        tpContent = new JTextPane();
        lChoice1 = new JLabel();
        lC1 = new JLabel();
        lChoice2 = new JLabel();
        lC2 = new JLabel();
        lChoice3 = new JLabel();
        lC3 = new JLabel();
        lChoice4 = new JLabel();
        lC4 = new JLabel();
        lAnswer = new JLabel();
        lAns = new JLabel();
        lYourAns = new JLabel();
        lYourAns2 = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 619, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 160, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            panel1.add(lQuestionNum, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tpContent);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice1 ----
            lChoice1.setText("A.");
            panel1.add(lChoice1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lC1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice2 ----
            lChoice2.setText("B.");
            panel1.add(lChoice2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lC2, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice3 ----
            lChoice3.setText("C.");
            panel1.add(lChoice3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lC3, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice4 ----
            lChoice4.setText("D.");
            panel1.add(lChoice4, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lC4, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lAnswer ----
            lAnswer.setText("\u7b54\u6848\uff1a");
            panel1.add(lAnswer, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lAns, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lYourAns ----
            lYourAns.setText("\u4f60\u7684\u7b54\u6848\uff1a");
            panel1.add(lYourAns, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(lYourAns2, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
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
    private JLabel lQuestionNum;
    private JScrollPane scrollPane1;
    private JTextPane tpContent;
    private JLabel lChoice1;
    private JLabel lC1;
    private JLabel lChoice2;
    private JLabel lC2;
    private JLabel lChoice3;
    private JLabel lC3;
    private JLabel lChoice4;
    private JLabel lC4;
    private JLabel lAnswer;
    private JLabel lAns;
    private JLabel lYourAns;
    private JLabel lYourAns2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
