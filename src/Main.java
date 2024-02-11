import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MainWindow Game = new MainWindow();
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        Game.setSize(screenWidth * 6 / 10, screenHeight * 8 / 10 + 5);
        Game.setResizable(false);
        Game.setVisible(true);
    }
}