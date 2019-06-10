package app;

import base.OwnButton;
import base.OwnPanel;
import base.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HeureApp extends JPanel{

    private MainFrame mainFrame;

    private JPanel panelHeure = new JPanel();
    private JPanel contentPanel = new JPanel();

    private OwnButton back= new OwnButton(new ImageIcon("img/icons/back.png"),40,40);

    //heure
    private JLabel heure = new JLabel();
    final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");
    private Timer timer = new Timer(0, new CurrentTime());

    public HeureApp(MainFrame mainFrame){

        this.mainFrame=mainFrame;

        timer.start();

        contentPanel.setPreferredSize(new Dimension(400,700));

        heure.setHorizontalAlignment(JLabel.CENTER);
        heure.setVerticalAlignment(JLabel.BOTTOM);
        heure.setFont(new Font("Bahnschrift", Font.PLAIN,80));
        heure.setForeground(Color.BLACK);

        back.addActionListener(new Back());
        contentPanel.add(heure);
        contentPanel.add(back);

        add(contentPanel);

    }

    public class CurrentTime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            heure.setText(DATEFORMAT.format(now.getTime()));
            repaint();
        }
    }

    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }
}