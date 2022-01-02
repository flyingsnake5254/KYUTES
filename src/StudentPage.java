import DataClass.GetDBdata;
import DataClass.User;
import ExamManager.ExamManage;
import Student.AccountManage;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sun Jan 02 17:07:29 CST 2022
 */



/**
 * @author peiChun lu
 */
public class StudentPage extends JFrame {
    private User nowUser;
    private String userAccount;
    private Statement st;
    public StudentPage(User user) {
        initComponents();
        this.nowUser = user;
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

        ((GridBagLayout)mainpanel.getLayout()).columnWidths = new int[] {0, 0, 0, (this.getWidth() - bAccountManage.getWidth() - 200), 0, 0};
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

    private void bAccountManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        AccountManage accountManage = new AccountManage();
        accountManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        accountManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(accountManage);
        this.invalidate();
        this.validate();
    }

    private void bPractice(ActionEvent e) {
        // TODO add your code here
    }

    private void bExam(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        mainpanel = new JPanel();
        label1 = new JLabel();
        bAccountManage = new JButton();
        panel1 = new JPanel();
        bPractice = new JButton();
        bExam = new JButton();
        bSearchGrade = new JButton();

        //======== mainpanel ========
        {
            mainpanel.setBackground(new Color(102, 255, 102));
            mainpanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,mainpanel. getBorder( )) ); mainpanel. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            mainpanel.setLayout(new GridBagLayout());
            ((GridBagLayout)mainpanel.getLayout()).columnWidths = new int[] {0, 0, 0, 838, 0, 0};
            ((GridBagLayout)mainpanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 472, 0, 0};
            ((GridBagLayout)mainpanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)mainpanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u5b78\u751f");
            mainpanel.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bAccountManage ----
            bAccountManage.setText("\u500b\u4eba\u5e33\u865f\u7ba1\u7406");
            bAccountManage.addActionListener(e -> bAccountManage(e));
            mainpanel.add(bAccountManage, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
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

            //---- bPractice ----
            bPractice.setText("\u7df4\u7fd2\u5340");
            bPractice.addActionListener(e -> bPractice(e));
            mainpanel.add(bPractice, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bExam ----
            bExam.setText("\u6e2c\u9a57\u5340");
            bExam.addActionListener(e -> bExam(e));
            mainpanel.add(bExam, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearchGrade ----
            bSearchGrade.setText("\u6e2c\u9a57\u6210\u7e3e\u67e5\u8a62");
            mainpanel.add(bSearchGrade, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel mainpanel;
    private JLabel label1;
    private JButton bAccountManage;
    private JPanel panel1;
    private JButton bPractice;
    private JButton bExam;
    private JButton bSearchGrade;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
