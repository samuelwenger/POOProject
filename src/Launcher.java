import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;

public class Launcher extends JFrame {
    
    <!-- Je suis dans dev-->

    private int nbreApp = 2;

    private JLabel titre = new JLabel("Launcher");
    private JButton[] appsButtons = new JButton[nbreApp];
    private JPanel icons = new JPanel();


    public Launcher () {

        //Définir le Layout de la page
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400,700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Définir les apps
        String[] appsName = new String[nbreApp];

        appsName[0] = "Contacts";
        appsName[1] = "Gallerie";

        //Création et ajout des boutons
        for(int i=0; i<appsButtons.length; i++){
            appsButtons[i] = new JButton(appsName[i]);
            appsButtons[i].setPreferredSize(new Dimension(80,80));
            icons.add(appsButtons[i]);
        }

        //Ajouter à la fenêtre
        add(titre, BorderLayout.NORTH);
        add(icons, BorderLayout.CENTER);

        pack();
        
        test();
    }

}
