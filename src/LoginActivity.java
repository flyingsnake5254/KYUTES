import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LoginActivity extends JPanel {
    public LoginActivity() {
        initComponents();
        frameInitial();
    }

    private void frameInitial(){
        frame1.pack();
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String path = System.getProperty("user.dir");
        System.out.println(path);
        frame1.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(frame1.getOwner());
    }
    String inputAccount = "";
    String inputPassword = "";
    private void bLogin(ActionEvent e) {
        // TODO add your code here
        inputAccount = tfAccount.getText();
        inputPassword = tfPassword.getText();
        Users users = new Users();
        if(users.checkAccount(inputAccount,inputPassword)){
            User user = users.getUser(inputAccount);
            if(user.isOnline()){
                Dialog.wrong("此帳號已在其他裝置登入");
            }
            else{
                if(user.isFirstLogin()){
                    Dialog.wrong("第一次登入請點選'第一次登入'按鈕");
                }
                else{
                    if(user.getIdentity().equals(Data.USER_IDENTITY_SYSTEM_MANAGER))
                        new SystemManagerPage(user);
                    else if(user.getIdentity().equals(Data.USER_IDENTITY_EXAM_MANAGER))
                        new ExamManagerPage(user);
                    else if(user.getIdentity().equals(Data.USER_IDENTITY_STUDENT))
                        new StudentPage(user);
                    user.setOnline(true);
                    frame1.dispose();
                }
            }
        }
        else{
            Dialog.wrong("帳號或密碼錯誤");
        }
    }

    private void bFirstLogin(ActionEvent e) {
        // TODO add your code here
        inputAccount = tfAccount.getText();
        inputPassword = tfPassword.getText();
        Users users = new Users();
        if(users.checkAccount(inputAccount,inputPassword)){
            User user = users.getUser(inputAccount);
            if(!user.isFirstLogin()){
                Dialog.wrong("此帳號不是第一次登入");
            }
            else{
                if(user.isOnline()){
                    Dialog.wrong("此帳號已在其他裝置登入");
                }
                else{
                    new FirstLoginFrame(user);
                    frame1.dispose();
                }
            }
        }
        else{
            Dialog.wrong("帳號或密碼錯誤");
        }
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        frame1 = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        tfAccount = new JTextField();
        label3 = new JLabel();
        tfPassword = new JPasswordField();
        bLogin = new JButton();
        bFirstLogin = new JButton();

        //======== frame1 ========
        {
            frame1.setTitle("KYUTES");
            var frame1ContentPane = frame1.getContentPane();

            //---- label1 ----
            label1.setText("KYUTES \u767b\u5165");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 20));

            //---- label2 ----
            label2.setText("\u5e33\u865f");
            label2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));
            label2.setHorizontalAlignment(SwingConstants.CENTER);

            //---- tfAccount ----
            tfAccount.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- label3 ----
            label3.setText("\u5bc6\u78bc");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));

            //---- tfPassword ----
            tfPassword.setFont(new Font("Consolas", Font.PLAIN, 18));

            //---- bLogin ----
            bLogin.setText("\u767b\u5165");
            bLogin.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));
            bLogin.addActionListener(e -> bLogin(e));

            //---- bFirstLogin ----
            bFirstLogin.setText("\u7b2c\u4e00\u6b21\u767b\u5165");
            bFirstLogin.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 18));
            bFirstLogin.addActionListener(e -> bFirstLogin(e));

            GroupLayout frame1ContentPaneLayout = new GroupLayout(frame1ContentPane);
            frame1ContentPane.setLayout(frame1ContentPaneLayout);
            frame1ContentPaneLayout.setHorizontalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGroup(frame1ContentPaneLayout.createParallelGroup()
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(bFirstLogin)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                        .addComponent(bLogin))
                                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(tfAccount, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(126, Short.MAX_VALUE))
            );
            frame1ContentPaneLayout.setVerticalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(31, 31, 31)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(bLogin)
                            .addComponent(bFirstLogin))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JFrame frame1;
    private JLabel label1;
    private JLabel label2;
    private JTextField tfAccount;
    private JLabel label3;
    private JPasswordField tfPassword;
    private JButton bLogin;
    private JButton bFirstLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
