/*
 * Created by JFormDesigner on Fri Dec 31 22:13:58 CST 2021
 */

package ExamManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;

/**
 * @author peiChun lu
 */
public class ExamManage extends JPanel {
    public ExamManage() {
        initComponents();
        pageInit();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 1002, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 652, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
            panel1.add(tabbedPane1, new GridBagConstraints(0, 0, 2, 2, 0.0, 0.0,
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

    private void pageInit(){
        ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 1330, 0, 0};
        ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 700, 0, 0};
        tabbedPane1.add("查詢與刪除測驗",new SearchAndDeleteExam());
        tabbedPane1.add("建立測驗",new CreateExam());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
