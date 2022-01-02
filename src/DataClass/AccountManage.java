/*
 * Created by JFormDesigner on Sun Jan 02 18:04:09 CST 2022
 */

package DataClass;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class AccountManage extends JPanel {
    private User nowUser;
    public AccountManage(User nowUser) {
        initComponents();
        this.nowUser = nowUser;
        pageInit();
    }

    private void pageInit(){
        ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 1330, 0, 0};
        ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 700, 0, 0};
        tabbedPane1.add("帳號資料修改",new AccountDataEdit(this.nowUser));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
        new javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e"
        ,javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
        ,new java.awt.Font("D\u0069al\u006fg",java.awt.Font.BOLD,12)
        ,java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
        ){if("\u0062or\u0064er".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {1027, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {675, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
            panel1.add(tabbedPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
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
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
