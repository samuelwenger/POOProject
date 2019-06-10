package app;

import base.OwnButton;
import base.Start;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockPanel extends Start {

    private MainFrame mainFrame;

    private OwnButton unlock = new OwnButton(new ImageIcon("img/icons/unlock.png"),70,70);


    public LockPanel(MainFrame mainFrame){

        this.mainFrame=mainFrame;

        unlock.addActionListener(new OpenLauncher());

        getButtonpanel().add(unlock);
        getButtonpanel().add(getOff());
    }

    public class OpenLauncher implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

}
