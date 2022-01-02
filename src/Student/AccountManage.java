/*
 * Created by JFormDesigner on Sun Jan 02 18:04:09 CST 2022
 */

package Student;

import ExamManager.CreateQuestionBank;
import ExamManager.SearchAndDeleteQuestionBank;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class AccountManage extends JPanel {
    public AccountManage() {
        initComponents();
        pageInit();
    }

    private void pageInit(){
        ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 1330, 0, 0};
        ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 700, 0, 0};
        tabbedPane1.add("帳號資料修改",new AccountDataEdit());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 255, 255));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 1012, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 627, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            panel1.add(tabbedPane1, new GridBagConstraints(0, 0, 3, 3, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 1060, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 1030, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 680, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
