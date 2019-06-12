package JUnit;

import base.Validation;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    Validation validation = new Validation();
    JTextField textField = new JTextField();

    @org.junit.jupiter.api.Test
    void isNotEmpty() {
        textField.setText("");
        assertFalse(validation.isNotEmpty(textField));

        textField.setText(null);
        assertFalse(validation.isNotEmpty(textField));

        textField.setText("Avec du texte");
        assertTrue(validation.isNotEmpty(textField));
    }

    @org.junit.jupiter.api.Test
    void phoneIsValid() {
        textField.setText("0786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("117");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("+41786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("0041786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("+41(0)786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("0041(0)786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText("41786350084");
        assertTrue(validation.phoneIsValid(textField));

        textField.setText(null);
        assertFalse(validation.phoneIsValid(textField));

        textField.setText("078 635 00 84");
        assertFalse(validation.phoneIsValid(textField));

        textField.setText("794521313");
        assertFalse(validation.phoneIsValid(textField));

        textField.setText("078635008");
        assertFalse(validation.phoneIsValid(textField));

        textField.setText("0041078635008");
        assertFalse(validation.phoneIsValid(textField));

    }

    @org.junit.jupiter.api.Test
    void emailIsValid() {
        textField.setText("samuel.wenger@students.hevs.ch");
        assertTrue(validation.emailIsValid(textField));

        textField.setText("ROBIN.GALLAY@HOPITALVS.CH");
        assertTrue(validation.emailIsValid(textField));

        textField.setText("Samuel.Wenger@admin.vs.ch");
        assertTrue(validation.emailIsValid(textField));

        textField.setText("test_test@hotmail.com");
        assertTrue(validation.emailIsValid(textField));

        textField.setText("samuel wenger@students.hevs.ch");
        assertFalse(validation.emailIsValid(textField));

        textField.setText("samuel wenger@students");
        assertFalse(validation.emailIsValid(textField));


    }
}