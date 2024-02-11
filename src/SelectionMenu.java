import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel {
    public static JPanel SelectionPanel;
    public static ButtonGroup Choises;
    public static JToggleButton[] Buttons;
    public static ButtonGroup Algorithms;
    public static String[] ButtonsNames = {"Start Point", "End Point", "Add Weights", "Remove Weights", "Add Obstacles", "Remove Obstacles", "DFS", "BFS", "Dijkstra"};

    public SelectionMenu() {
        SelectionPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        Choises = new ButtonGroup();
        Algorithms = new ButtonGroup();
        Buttons = new JToggleButton[9];
        JLabel text1 = new JLabel("Choises");
        text1.setOpaque(true);
        text1.setBackground(new Color(32, 60, 145));
        text1.setForeground(Color.WHITE);
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setVerticalAlignment(SwingConstants.CENTER);
        SelectionPanel.add(text1);
        Clicked handler = new Clicked();
        for (int cur = 0; cur < 6; cur++) {
            Buttons[cur] = new JToggleButton(ButtonsNames[cur]);
            Buttons[cur].setPreferredSize(new Dimension(100, 50));
            Buttons[cur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 0));
            int finalCur = cur;
            Buttons[cur].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (Buttons[finalCur].isSelected()) {
                        Buttons[finalCur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    } else {
                        Buttons[finalCur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 0));
                    }
                }
            });
            Buttons[cur].addActionListener(handler);
            Choises.add(Buttons[cur]);
            SelectionPanel.add(Buttons[cur]);
        }
        JLabel text2 = new JLabel("Pick Algorithm");
        text2.setOpaque(true);
        text2.setBackground(new Color(32, 60, 145));
        text2.setForeground(Color.WHITE);
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setVerticalAlignment(SwingConstants.CENTER);
        SelectionPanel.add(text2);
        for (int cur = 6; cur < 9; cur++) {
            Buttons[cur] = new JToggleButton(ButtonsNames[cur]);
            Buttons[cur].setPreferredSize(new Dimension(100, 50));
            Buttons[cur].setBackground(new Color(255));
            Buttons[cur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 0));
            int finalCur = cur;
            Buttons[cur].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (Buttons[finalCur].isSelected()) {
                        Buttons[finalCur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    } else {
                        Buttons[finalCur].setBorder(BorderFactory.createLineBorder(Color.GREEN, 0));
                        Buttons[finalCur].setForeground(Color.BLACK);
                    }
                }
            });
            Buttons[cur].addActionListener(handler);
            Choises.add(Buttons[cur]);
            SelectionPanel.add(Buttons[cur]);
        }
        add(SelectionPanel);
    }
}

class Clicked implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int val = MainWindow.ActiveAlgoritm();
        if (val == 7) {
            MainWindow.ClearVis();
            //dfs
            MainWindow.runDFS(MainWindow.StartPointRow, MainWindow.StartPointCol);
        } else if (val == 8) {
            //bfs
            MainWindow.ClearVis();
            MainWindow.runBFS(MainWindow.StartPointRow, MainWindow.StartPointCol);
        } else if (val == 9) {
            //dij
            MainWindow.ClearVis();
            MainWindow.runDIJ(MainWindow.StartPointRow, MainWindow.StartPointCol);
        } else {
            MainWindow.ClearVis();
        }
    }
}
