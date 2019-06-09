package base;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe permet la validation des données saisies par l'utilisateur
 * au moment de la création d'un nouveau contact ou lors de la modification d'un contact existant.
 */
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


    public boolean phoneIsValid(JTextField textField){
        String swissPhoneRegex = "(?:(?:|0{1,2}|\\+{0,2})41(?:|\\(0\\))|0)([1-9]\\d)(\\d{3})(\\d{2})(\\d{2})";
        String emergencyPhoneRegex = "\\d{3}";

        if(textField.getText().matches(swissPhoneRegex) == false && textField.getText().matches(emergencyPhoneRegex) == false){
            textField.setBackground(Color.red);
            textField.setForeground(Color.WHITE);
            return false;
        }
        else{
            return true;
        }
    }

    public boolean emailIsValid(JTextField textField){
        String emailPattern = "\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        String voidEmail = "";

        if(textField.getText().matches(emailPattern) == false && textField.getText().matches(voidEmail) == false){
            textField.setBackground(Color.red);
            textField.setForeground(Color.WHITE);
            return false;
        }
        else{
            return true;
        }
    }



    /**
     * Cette méthode permet de réinitialliser l'affichage des JTextFields colorés suite à une
     * erreur de validation au moment de la sauvegarde précédente.
     *
     * @param textField
     */
    public void resetValidation(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
    }


}
