import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

public class LoginActivity extends JPanel {
    private ArrayList<String> accounts;
    private ArrayList<String> passwords;
    private ArrayList<String> identity;
    private ArrayList<String> firstLogin;
    String tAccount;
    private Statement st;
    public LoginActivity() {
        initComponents();
        getDBdata();
        buttonLoginListener();
    }

    private void getDBdata(){
        accounts = new ArrayList<>();
        passwords = new ArrayList<>();
        identity = new ArrayList<>();
        firstLogin = new ArrayList<>();

        st = new GetDBdata().getStatement();
        try {
            st.execute("SELECT * FROM user");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                accounts.add(rs.getString("account"));
                passwords.add(rs.getString("password"));
                identity.add(rs.getString("identity"));
                firstLogin.add(rs.getString("first_login"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void setOnline(){
        String sql = "update user set state='online' where account='"+tAccount+"'";
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"未知錯誤","錯誤",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buttonLoginListener(){
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tAccount = tfAccount.getText();
                String tPassword = tfPassword.getText();

                if(accounts.contains(tAccount) && passwords.get(accounts.indexOf(tAccount)).equals(tPassword)){
                    String iden = identity.get(accounts.indexOf(tAccount));
                    String state = firstLogin.get(accounts.indexOf(tAccount));
                    if(state.equals("true")){
                        JOptionPane.showMessageDialog(null,"初次登入者，請點選\"第一次登入\"按鈕!","注意",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        if(iden.equals("manager")){
                            frame.dispose();
                            setOnline();
                            new MamagerPage(tAccount);
                        }
                        else if(iden.equals("teacher")){
                            frame.dispose();
                            setOnline();
                            new TeacherPage(tAccount);
                        }
                        else if(iden.equals("student")){
                            frame.dispose();
                            setOnline();
                            new StudentPage(tAccount);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"未知錯誤","錯誤",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"帳號或密碼錯誤!","錯誤",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }


    private JFrame frame;
    private JLabel labelTitle;
    private JLabel labelAccount;
    private JTextField tfAccount;
    private JLabel labelPassword;
    private JPasswordField tfPassword;
    private JButton buttonLogin;
    private JButton buttonFirstTimeLogin;

    private void initComponents() {
        frame = new JFrame();
        labelTitle = new JLabel();
        labelAccount = new JLabel();
        tfAccount = new JTextField();
        labelPassword = new JLabel();
        tfPassword = new JPasswordField();
        buttonLogin = new JButton();
        buttonFirstTimeLogin = new JButton();

        //======== frame ========
        {
            frame.setTitle("KYUTES");
            var frameContentPane = frame.getContentPane();

            //---- labelTitle ----
            labelTitle.setText("KYUTES \u767b\u5165");
            labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
            labelTitle.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 20));

            //---- labelAccount ----
            labelAccount.setText("\u5e33\u865f");
            labelAccount.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));
            labelAccount.setHorizontalAlignment(SwingConstants.CENTER);

            //---- tfAccount ----
            tfAccount.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- labelPassword ----
            labelPassword.setText("\u5bc6\u78bc");
            labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
            labelPassword.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));

            //---- tfPassword ----
            tfPassword.setFont(new Font("Consolas", Font.PLAIN, 18));

            //---- buttonLogin ----
            buttonLogin.setText("\u767b\u5165");
            buttonLogin.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));

            //---- buttonFirstTimeLogin ----
            buttonFirstTimeLogin.setText("\u7b2c\u4e00\u6b21\u767b\u5165");
            buttonFirstTimeLogin.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));

            GroupLayout frameContentPaneLayout = new GroupLayout(frameContentPane);
            frameContentPane.setLayout(frameContentPaneLayout);
            frameContentPaneLayout.setHorizontalGroup(
                frameContentPaneLayout.createParallelGroup()
                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                        .addGroup(frameContentPaneLayout.createParallelGroup()
                            .addGroup(frameContentPaneLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(GroupLayout.Alignment.TRAILING, frameContentPaneLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonFirstTimeLogin)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                        .addComponent(buttonLogin))
                                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelAccount, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                            .addComponent(labelPassword, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(tfAccount, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                            .addGroup(frameContentPaneLayout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(labelTitle, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(126, Short.MAX_VALUE))
            );
            frameContentPaneLayout.setVerticalGroup(
                frameContentPaneLayout.createParallelGroup()
                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(labelTitle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAccount, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword))
                        .addGap(31, 31, 31)
                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonLogin)
                            .addComponent(buttonFirstTimeLogin))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
            frame.pack();
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String path = System.getProperty("user.dir");
            System.out.println(path);
            frame.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
            frame.setVisible(true);
            frame.setLocationRelativeTo(frame.getOwner());
        }
    }

}
