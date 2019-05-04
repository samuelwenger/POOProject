import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;

public class Launcher extends JFrame {

    private JLabel titre = new JLabel("Launcher");
    private JButton[] apps = new JButton[2];
    private JPanel icons = new JPanel();


    public Launcher () {

        //Définir le Layout de la page
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400,700));

        //Définir les apps
        apps[0] = new JButton("app1");
        apps[1] = new JButton("app2");

        //Ajouter les boutons
        for(int i=0; i<apps.length; i++){
            icons.add(apps[i]);
            apps[i].setPreferredSize(new Dimension(80,80));
        }


        //Ajouter à la fenêtre
        add(titre, BorderLayout.NORTH);
        add(icons, BorderLayout.CENTER);

        pack();
    }

}
