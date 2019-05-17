import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Launcher extends JFrame{

    //formatteddate
    Date datesystem = new Date();
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");
    String formattedtime = heureFormat.format(datesystem);

        //date
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM");
    String formatteddate = dateFormat.format(datesystem);


    //header
    private JLabel heure = new JLabel(formattedtime);
    private JLabel batterie = new JLabel("B");
    private JLabel wifi = new JLabel ("W");
    private JLabel reseau = new JLabel("R");
    //Panels
    private JPanel screen = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new GridLayout(1,2));
    private JPanel footer = new JPanel(new GridLayout(1,4,10,0));
    private JPanel main = new JPanel(new GridLayout(4,1));
    //SousPanels
    private JPanel leftheader = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel rightheader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JLabel heureMain = new JLabel(formattedtime);
    private JLabel dateMain = new JLabel(formatteddate);


    //Buttons
    private JButton contact = new JButton();
    private JButton galerie = new JButton();
    private JButton appel = new JButton ();



    public Launcher () {

        //Définir le Layout de la page
        setPreferredSize(new Dimension(400,700));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //ajout de la barre supérieur

        leftheader.add(heure);
        rightheader.add(batterie);
        rightheader.add(wifi);
        rightheader.add(reseau);

        header.add(leftheader);
        header.add(rightheader);

        //ajout du main
        heureMain.setHorizontalAlignment(JLabel.CENTER);
        heureMain.setVerticalAlignment(JLabel.BOTTOM);
        heureMain.setFont(new Font("Bahnschrift", Font.PLAIN,75));

        dateMain.setHorizontalAlignment(JLabel.CENTER);
        dateMain.setVerticalAlignment(JLabel.TOP);
        dateMain.setFont(new Font("Bahnschrift", Font.PLAIN,20));

        main.add(heureMain);
        main.add(dateMain);

        //ajout du footer
        footer.setPreferredSize(new Dimension(400,100));
        contact.setIcon(new ImageIcon("C:\\Users\\rgall\\IdeaProjects\\POO-Projet-RGSW\\img\\contact.png"));
        footer.add(contact);
        footer.add(galerie);
        footer.add(appel);


        screen.add(header, BorderLayout.NORTH);
        screen.add(footer, BorderLayout.SOUTH);
        screen.add(main, BorderLayout.CENTER);


        header.setOpaque(false);
        main.setOpaque(false);
        footer.setOpaque(false);
        leftheader.setOpaque(false);
        rightheader.setOpaque(false);

        screen.setOpaque(true);
        screen.setBackground(Color.CYAN);
        add(screen);

        pack();


    }

}
