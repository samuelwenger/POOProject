package app;

import app.MainFrame;
import base.OwnButton;
import base.OwnPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LockPanel extends JPanel {

    private MainFrame mainFrame;

    private OwnPanel contentPanel = new OwnPanel (new ImageIcon("img/Background.jpg").getImage());
    private OwnButton unlock = new OwnButton(new ImageIcon("img/icons/edit.png"),40,40);
    private OwnButton off = new OwnButton(new ImageIcon("img/icons/right.png"),40,40);

    private OwnPanel buttonpanel = new OwnPanel(new FlowLayout());
    private OwnPanel main = new OwnPanel (new GridLayout(4,1));


    // Heure
    Date datesystem = new Date();
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");
    String formattedtime = heureFormat.format(datesystem);
    private JLabel heure = new JLabel(formattedtime);

    // Date
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM");
    String formatteddate = dateFormat.format(datesystem);
    private JLabel date = new JLabel(formatteddate);


    public LockPanel(MainFrame mainFrame){

        this.mainFrame=mainFrame;

        contentPanel.setPreferredSize(new Dimension(400,700));

        heure.setHorizontalAlignment(JLabel.CENTER);
        heure.setVerticalAlignment(JLabel.BOTTOM);
        heure.setFont(new Font("Bahnschrift", Font.PLAIN,75));

        date.setHorizontalAlignment(JLabel.CENTER);
        date.setVerticalAlignment(JLabel.TOP);
        date.setFont(new Font("Bahnschrift", Font.PLAIN,20));

        main.add(heure);
        main.add(date);

        unlock.addActionListener(new OpenLauncher());
        off.addActionListener(new SwitchOff());

        buttonpanel.add(unlock);
        buttonpanel.add(off);

        contentPanel.add(buttonpanel, BorderLayout.SOUTH);
        contentPanel.add(main,BorderLayout.CENTER);

        add(contentPanel);
    }

    public class OpenLauncher implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

    public class SwitchOff implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
