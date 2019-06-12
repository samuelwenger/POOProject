package app;

import base.OwnButton;
import base.OwnPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Cette classe gère l'application HeureApp qui donne l'heure en temps réel
 */
public class HeureApp extends JPanel{

    private MainFrame mainFrame;

    private OwnPanel panelHeure = new OwnPanel(new FlowLayout());
    private OwnPanel panelBack = new OwnPanel(new FlowLayout());
    private OwnPanel contentPanel = new OwnPanel(new ImageIcon("img/phoneBackground.png").getImage());

    private OwnButton back= new OwnButton(new ImageIcon("img/icons/backwhite.png"),40,40);

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
        heure.setForeground(Color.WHITE);

        back.addActionListener(new Back());

        panelHeure.add(heure);
        panelBack.add(back);
        contentPanel.add(panelHeure,BorderLayout.NORTH);
        contentPanel.add(panelBack,BorderLayout.SOUTH);


        add(contentPanel);

    }

    /**
     * Cet écouteur permet la mise à jour automatique de l'heure
     */
    public class CurrentTime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            heure.setText(DATEFORMAT.format(now.getTime()));
            repaint();
        }
    }

    /**
     * Cette classe gère l'écouteur permettant le retour sur le Launcher
     */
    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }
}