import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Dec 17 13:55:13 CST 2021
 */



/**
 * @author unknown
 */
public class LoginForm extends JPanel {
    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        frame1 = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();

        //======== frame1 ========
        {
            frame1.setTitle("KYUTES");
            var frame1ContentPane = frame1.getContentPane();

            //---- label1 ----
            label1.setText("KYUTES Login");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Consolas", Font.BOLD, 20));

            //---- label2 ----
            label2.setText("account");
            label2.setFont(new Font("Consolas", Font.BOLD, 18));
            label2.setHorizontalAlignment(SwingConstants.CENTER);

            //---- textField2 ----
            textField2.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- label3 ----
            label3.setText("password");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("Consolas", Font.BOLD, 18));

            //---- passwordField1 ----
            passwordField1.setFont(new Font("Consolas", Font.PLAIN, 18));

            //---- button1 ----
            button1.setText("Login");
            button1.setFont(new Font("Consolas", Font.BOLD, 18));

            GroupLayout frame1ContentPaneLayout = new GroupLayout(frame1ContentPane);
            frame1ContentPane.setLayout(frame1ContentPaneLayout);
            frame1ContentPaneLayout.setHorizontalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(91, Short.MAX_VALUE)
                        .addComponent(label3)
                        .addGap(290, 290, 290))
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGroup(frame1ContentPaneLayout.createParallelGroup()
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(button1)
                                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(81, Short.MAX_VALUE))
            );
            frame1ContentPaneLayout.setVerticalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(button1)
                        .addContainerGap(42, Short.MAX_VALUE))
            );
            frame1.pack();
            String path = System.getProperty("user.dir");
            System.out.println(path);
            frame1.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JFrame frame1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
