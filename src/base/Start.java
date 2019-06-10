package base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Start extends JPanel {

    private OwnPanel contentPanel = new OwnPanel (new ImageIcon("img/phoneBackground.png").getImage());


    private OwnPanel buttonpanel = new OwnPanel(new FlowLayout());
    private OwnPanel header = new OwnPanel(new GridLayout(1,2));
    private OwnPanel main = new OwnPanel (new GridLayout(5,1));
    private OwnButton off = new OwnButton(new ImageIcon("img/icons/shutdown.png"),70,70);


    // Heure

/*
    Date datesystem = new Date();
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");
    String formattedtime = heureFormat.format(datesystem);
    private JLabel heure = new JLabel(formattedtime);
*/

    private JLabel heure = new JLabel();
    final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
    private Timer timer = new Timer(0, new CurrentTime());


    // Date
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM");
    String formatteddate = dateFormat.format(datesystem);
    private JLabel date = new JLabel(formatteddate);


    public Start() {
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

        timer.start();

        main.add(heure);
        main.add(date);

        contentPanel.add(header,BorderLayout.NORTH);
        contentPanel.add(buttonpanel, BorderLayout.SOUTH);
        contentPanel.add(main,BorderLayout.CENTER);

        add(contentPanel);
    }

    public class SwitchOff implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public OwnPanel getButtonpanel(){
        return buttonpanel;
    }

    public OwnButton getOff() {
        return off;
    }

    public String getFormattedtime(){
        return formattedtime;
    }

    public String getFormatteddate() {
        return formatteddate;
    }

    public OwnPanel getHeader() {
        return header;
    }

    public OwnPanel getMain(){
        return main;
    }

    public class CurrentTime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            heure.setText(DATEFORMAT.format(now.getTime()));
        }
    }

}