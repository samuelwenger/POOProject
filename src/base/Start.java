package base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe abastraite permettant la configuration de l'affiche utilisé dans Launcher et LockPanel
 */
public abstract class Start extends JPanel {

    private OwnPanel contentPanel = new OwnPanel (new ImageIcon(this.getClass().getResource("/phoneBackground.png")).getImage());

    private OwnPanel buttonpanel = new OwnPanel(new FlowLayout());
    private OwnPanel header = new OwnPanel(new GridLayout(1,2));
    private OwnPanel main = new OwnPanel (new GridLayout(5,1));
    private OwnButton off = new OwnButton(new ImageIcon(this.getClass().getResource("/icons/shutdown.png")),70,70);

    private JLabel heure = new JLabel();
    private JLabel heuresmall = new JLabel();
    final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
    private Timer timer = new Timer(0, new CurrentTime());

    // Date
    Date datefordate= new Date();
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM");
    String formatteddate = dateFormat.format(datefordate);
    private JLabel date = new JLabel(formatteddate);


    public Start() {

        timer.start();
        contentPanel.setPreferredSize(new Dimension(400,700));

        heure.setHorizontalAlignment(JLabel.CENTER);
        heure.setVerticalAlignment(JLabel.BOTTOM);
        heure.setFont(new Font("Bahnschrift", Font.PLAIN,75));
        heure.setForeground(Color.WHITE);

        date.setHorizontalAlignment(JLabel.CENTER);
        date.setVerticalAlignment(JLabel.TOP);
        date.setFont(new Font("Bahnschrift", Font.PLAIN,20));
        date.setForeground(Color.WHITE);

        off.addActionListener(new SwitchOff());

        main.add(heure);
        main.add(date);

        contentPanel.add(header,BorderLayout.NORTH);
        contentPanel.add(buttonpanel, BorderLayout.SOUTH);
        contentPanel.add(main,BorderLayout.CENTER);

        add(contentPanel);
    }

    /**
     * Ecouteur permettant d'éteindre le téléphone (sortie de l'application)
     */
    public class SwitchOff implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Ecouteur permettant la mise à jour automatique de l'heure
     */
    public class CurrentTime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            String text = DATEFORMAT.format(now.getTime());
            heure.setText(text);
            heuresmall.setText(text);
            repaint();
        }
    }

    public OwnPanel getButtonpanel(){
        return buttonpanel;
    }

    public OwnButton getOff() {
        return off;
    }

    public JLabel getHeureSmall () {
        return heuresmall;
    }

    public OwnPanel getHeader() {
        return header;
    }

    public OwnPanel getMain(){
        return main;
    }



}