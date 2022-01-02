/*
 * Created by JFormDesigner on Sat Dec 18 12:53:58 CST 2021
 */

package SystemManager;

import java.awt.*;
import javax.swing.*;

/**
 * @author peiChun lu
 */
public class CreateAccount extends JPanel {
    public CreateAccount() {
        initComponents();
        tabbedPaneInitial();
    }

    private void tabbedPaneInitial(){
        InputCSVfilePanel inputCSVfilePanel = new InputCSVfilePanel();
        inputCSVfilePanel.setSize(new Dimension(tabbedPane1.getWidth(), tabbedPane1.getHeight()));
        CreateManually createManually = new CreateManually();
        createManually.setSize(new Dimension(tabbedPane1.getWidth(),tabbedPane1.getHeight()));
        tabbedPane1.add("匯入CSV",inputCSVfilePanel);
        tabbedPane1.add("手動創建",createManually);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setPreferredSize(new Dimension(892, 621));
        setBackground(new Color(204, 204, 204));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
        , 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
         getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 1303, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 710, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //======== tabbedPane1 ========
        {
            tabbedPane1.setPreferredSize(new Dimension(892, 621));
            tabbedPane1.setBackground(new Color(204, 204, 204));
        }
        add(tabbedPane1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
