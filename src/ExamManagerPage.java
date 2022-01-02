import DataClass.GetDBdata;
import DataClass.User;
import ExamManager.ExamManage;
import ExamManager.QuestionBankManage;
import ExamManager.QuestionManage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamManagerPage extends JFrame {
    private String userAccount;
    private Statement st;
    public ExamManagerPage(User nowUser) {
        st = new GetDBdata().getStatement();
        this.userAccount = nowUser.getAccount();
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String path = System.getProperty("user.dir");
        System.out.println(path);
        this.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        this.setVisible(true);
        this.setTitle("KYUTES 評量管理者");
        this.setSize(1000,800);
        initComponents();
        this.add(mainpanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ((GridBagLayout)mainpanel.getLayout()).columnWidths = new int[] {0, 0, 0, (this.getWidth() - bQuestionBankManage.getWidth() - 200), 0, 0};
        ((GridBagLayout)mainpanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, (this.getHeight() - 200), 0, 0};
        frameClose();
    }

    private void frameClose(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                String sql = "update user set state='offline' where account='"+userAccount+"'";
                try {
                    st.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void bQuestionBankManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        QuestionBankManage questionBankManage = new QuestionBankManage();
        questionBankManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        questionBankManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(questionBankManage);
        this.invalidate();
        this.validate();
    }

    private void bQuestionManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        QuestionManage questionManage = new QuestionManage();
        questionManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        questionManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(questionManage);
        this.invalidate();
        this.validate();
    }

    private void bExamManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        ExamManage examManage = new ExamManage();
        examManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        examManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(examManage);
        this.invalidate();
        this.validate();
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        mainpanel = new JPanel();
        label1 = new JLabel();
        bQuestionBankManage = new JButton();
        panel1 = new JPanel();
        bQuestionManage = new JButton();
        bExamManage = new JButton();

        //======== mainpanel ========
        {
            mainpanel.setBackground(new Color(102, 255, 102));
            mainpanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,mainpanel. getBorder( )) ); mainpanel. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            mainpanel.setLayout(new GridBagLayout());
            ((GridBagLayout)mainpanel.getLayout()).columnWidths = new int[] {0, 0, 0, 838, 0, 0};
            ((GridBagLayout)mainpanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 472, 0, 0};
            ((GridBagLayout)mainpanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)mainpanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u8a55\u91cf\u7ba1\u7406\u54e1");
            mainpanel.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bQuestionBankManage ----
            bQuestionBankManage.setText("\u984c\u5eab\u7ba1\u7406");
            bQuestionBankManage.addActionListener(e -> bQuestionBankManage(e));
            mainpanel.add(bQuestionBankManage, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== panel1 ========
            {
                panel1.setBackground(new Color(153, 153, 255));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 883, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 622, Short.MAX_VALUE)
                );
            }
            mainpanel.add(panel1, new GridBagConstraints(2, 2, 3, 6, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- bQuestionManage ----
            bQuestionManage.setText("\u8a66\u984c\u7ba1\u7406");
            bQuestionManage.addActionListener(e -> bQuestionManage(e));
            mainpanel.add(bQuestionManage, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bExamManage ----
            bExamManage.setText("\u6e2c\u9a57\u8a66\u5377\u7ba1\u7406");
            bExamManage.addActionListener(e -> bExamManage(e));
            mainpanel.add(bExamManage, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel mainpanel;
    private JLabel label1;
    private JButton bQuestionBankManage;
    private JPanel panel1;
    private JButton bQuestionManage;
    private JButton bExamManage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
