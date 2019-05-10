import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;

public class Launcher extends JFrame {

    private int nbreApp = 2;

    //header
    private JLabel heure = new JLabel("14:18");
    private JLabel batterie = new JLabel("B");
    private JLabel wifi = new JLabel ("W");
    private JLabel reseau = new JLabel("R");
        //Panels
    private JPanel screen = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new GridLayout(1,2));
    private JPanel footer = new JPanel();
    private JPanel main = new JPanel();
        //SousPanels
    private JPanel leftheader = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel rightheader = new JPanel(new FlowLayout(FlowLayout.RIGHT));



    private JButton[] appsButtons = new JButton[nbreApp];
    private JPanel icons = new JPanel();



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


        screen.add(header, BorderLayout.NORTH);
        screen.add(main, BorderLayout.CENTER);
        screen.add(footer, BorderLayout.SOUTH);

        header.setOpaque(false);
        main.setOpaque(false);
        footer.setOpaque(false);
        leftheader.setOpaque(false);
        rightheader.setOpaque(false);

        screen.setOpaque(true);
        screen.setBackground(Color.RED);
        add(screen);



        //Définir les apps
        //String[] appsName = new String[nbreApp];

        //appsName[0] = "Contacts";
       // appsName[1] = "Gallerie";


        pack();


    }

}
