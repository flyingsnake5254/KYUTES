import DataClass.GetDBdata;
import DataClass.User;
import Manager.AccountManagement;
import Manager.CreateAccount;
import Manager.GroupManage;
import Manager.SubjectManage;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class SystemManagerPage extends JFrame {
    private Statement st;
    private User nowUser;

    public SystemManagerPage(User nowUser) {
        this.nowUser = nowUser;
        st = new GetDBdata().getStatement();
        frameInitial();
        frameClose();
    }
    private void frameInitial(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String path = System.getProperty("user.dir");
        System.out.println(path);
        this.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        this.setVisible(true);
        this.setTitle("KYUTES 系統管理者");
        this.setSize(1000,800);
        initComponents();
        this.add(mainPanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void frameClose(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                nowUser.setOnline(false);
            }
        });
    }
    

    private void bAccountManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        AccountManagement accountManagement = new AccountManagement();
        accountManagement.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        accountManagement.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(accountManagement);
        this.invalidate();
        this.validate();
    }
    
    private void bGroupManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        GroupManage groupManage = new GroupManage();
        groupManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        groupManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(groupManage);
        this.invalidate();
        this.validate();
    }

    private void bBuildAccount(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        CreateAccount createAccount = new CreateAccount();
        createAccount.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        createAccount.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(createAccount);
        this.invalidate();
        this.validate();
    }

    private void bSubjectManage(ActionEvent e) {
        // TODO add your code here
        panel1.removeAll();
        SubjectManage subjectManage = new SubjectManage();
        subjectManage.setSize(new Dimension(panel1.getWidth(),panel1.getHeight()));
        subjectManage.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.add(subjectManage);
        this.invalidate();
        this.validate();
    }

    private void b_buildAccount(ActionEvent e) {
        // TODO add your code here
    }

    private void b_accountManage(ActionEvent e) {
        // TODO add your code here
    }

    private void b_groupManage(ActionEvent e) {
        // TODO add your code here
    }

    private void b_subjectManage(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        mainPanel = new JPanel();
        label1 = new JLabel();
        bBuildAccount = new JButton();
        panel1 = new JPanel();
        bAccountManage = new JButton();
        bGroupManage = new JButton();
        bSubjectManage = new JButton();

        //======== mainPanel ========
        {
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            mainPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0, 1287, 0};
            ((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 593, 0};
            ((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u7cfb\u7d71\u7ba1\u7406\u8005");
            mainPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bBuildAccount ----
            bBuildAccount.setText("\u5efa\u7acb\u4f7f\u7528\u8005\u5e33\u865f");
            bBuildAccount.addActionListener(e -> {
			b_buildAccount(e);
			bBuildAccount(e);
		});
            mainPanel.add(bBuildAccount, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== panel1 ========
            {
                panel1.setBackground(new Color(204, 204, 204));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 1312, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 733, Short.MAX_VALUE)
                );
            }
            mainPanel.add(panel1, new GridBagConstraints(1, 1, 2, 5, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- bAccountManage ----
            bAccountManage.setText("\u5e33\u865f\u7ba1\u7406");
            bAccountManage.addActionListener(e -> {
			b_accountManage(e);
			bAccountManage(e);
		});
            mainPanel.add(bAccountManage, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bGroupManage ----
            bGroupManage.setText("\u6e2c\u9a57\u7fa4\u7d44\u7ba1\u7406");
            bGroupManage.addActionListener(e -> {
			b_groupManage(e);
			bGroupManage(e);
		});
            mainPanel.add(bGroupManage, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSubjectManage ----
            bSubjectManage.setText("\u6e2c\u9a57\u79d1\u76ee\u7ba1\u7406");
            bSubjectManage.addActionListener(e -> {
			b_subjectManage(e);
			bSubjectManage(e);
		});
            mainPanel.add(bSubjectManage, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel mainPanel;
    private JLabel label1;
    private JButton bBuildAccount;
    private JPanel panel1;
    private JButton bAccountManage;
    private JButton bGroupManage;
    private JButton bSubjectManage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
