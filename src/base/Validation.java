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
