import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class MamagerPage extends JFrame {
    private String userAccount;
    private Statement st;

    public MamagerPage(String userAccount) {
        st = new GetDBdata().getStatement();
        this.userAccount = userAccount;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String path = System.getProperty("user.dir");
        System.out.println(path);
        this.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        this.setVisible(true);
        this.setTitle("KYUTES Manager");
        initComponents();
        frameClose();
        buttonBuildAccount();
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

    // 建立使用者帳號
    private void buttonBuildAccount(){
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildAccount();
            }
        });
    }

    private void buildAccount(){

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Manager");

        //---- button1 ----
        button1.setText("\u5efa\u7acb\u4f7f\u7528\u8005\u5e33\u865f");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(button1)))
                    .addContainerGap(846, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1)
                    .addContainerGap(520, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
