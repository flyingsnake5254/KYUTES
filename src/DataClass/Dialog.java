package DataClass;

import javax.swing.*;

public class Dialog {
    public static void wrong(String message){
        JOptionPane.showMessageDialog(
                null,
                message,
                "錯誤",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void message(String msg){
        JOptionPane.showMessageDialog(
                null,
                msg,
                "訊息",
                JOptionPane.DEFAULT_OPTION
        );
    }
}
