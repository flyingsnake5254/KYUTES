import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginActivity extends JPanel {
    public LoginActivity() {
        initComponents();
        buttonLoginListener();
    }

    private void initComponents() {

        frame = new JFrame();
        labelTitle = new JLabel();
        labelAccount = new JLabel();
        tfAccount = new JTextField();
        labelPassword = new JLabel();
        tfPassword = new JPasswordField();
        buttonLogin = new JButton();

        //======== frame ========
        {
            frame.setTitle("KYUTES");
            var frameContentPane = frame.getContentPane();

            //---- labelTitle ----
            labelTitle.setText("KYUTES Login");
            labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
            labelTitle.setFont(new Font("Consolas", Font.BOLD, 20));

            //---- labelAccount ----
            labelAccount.setText("account");
            labelAccount.setFont(new Font("Consolas", Font.BOLD, 18));
            labelAccount.setHorizontalAlignment(SwingConstants.CENTER);

            //---- tfAccount ----
            tfAccount.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- labelPassword ----
            labelPassword.setText("password");
            labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
            labelPassword.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- tfPassword ----
            tfPassword.setFont(new Font("Consolas", Font.PLAIN, 18));

            //---- buttonLogin ----
            buttonLogin.setText("Login");
            buttonLogin.setFont(new Font("Consolas", Font.BOLD, 18));

            GroupLayout frameContentPaneLayout = new GroupLayout(frameContentPane);
            frameContentPane.setLayout(frameContentPaneLayout);
            frameContentPaneLayout.setHorizontalGroup(
                frameContentPaneLayout.createParallelGroup()
                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                        .addContainerGap(91, Short.MAX_VALUE)
                        .addComponent(labelPassword)
                        .addGap(290, 290, 290))
                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                        .addGroup(frameContentPaneLayout.createParallelGroup()
                            .addGroup(frameContentPaneLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonLogin)
                                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                                        .addComponent(labelAccount, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(tfAccount, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                            .addGroup(frameContentPaneLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(labelTitle, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(81, Short.MAX_VALUE))
            );
            frameContentPaneLayout.setVerticalGroup(
                frameContentPaneLayout.createParallelGroup()
                    .addGroup(frameContentPaneLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(labelTitle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAccount, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(frameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPassword)
                            .addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(buttonLogin)
                        .addContainerGap(42, Short.MAX_VALUE))
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

    private void buttonLoginListener(){
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tAccount = tfAccount.getText();
                String tPassword = tfPassword.getText();
                System.out.println(tAccount+"\n"+tPassword);
                frame.dispose();
                new StudentPage();
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

}
