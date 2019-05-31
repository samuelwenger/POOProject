package base;

import javax.swing.*;
import java.awt.*;

public class Validation {

    public boolean isNotEmpty(JTextField textField)
    {
        if (textField.getText().isEmpty())
        {
            textField.setBackground(Color.red);
            textField.setForeground(Color.WHITE);
            return false;
        }
        else
            return true;
    }


    public void resetValidation(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
    }

}
