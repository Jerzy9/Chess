package Main;

import javax.swing.*;

public class Main extends JFrame {
    private JFrame jFrame;
    private MainPanel mainPanel;

    public static void main(String arg[]) {
        new Main();
    }

    public Main() {
        jFrame = new JFrame("Chess");
        mainPanel = new MainPanel();
        jFrame.add(mainPanel);

        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(450,0);
        jFrame.pack();
    }
}
