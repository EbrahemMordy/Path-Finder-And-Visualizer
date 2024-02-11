import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel {
    public static JButton[][] cells;
    public static JPanel gridPanel;
    public static int rows = 18, cols = 22;

    public Grid() {
        cells = new JButton[rows][cols];
        gridPanel = new JPanel(new GridLayout(rows, cols, 1, 1));
        gridPanel.setBackground(Color.GRAY);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setOpaque(true);
                cells[i][j].setBorderPainted(false);
                cells[i][j].setBackground(Color.black);
                cells[i][j].setPreferredSize(new Dimension(35, 35));
                cells[i][j].addActionListener(new ButtonClickListener(i, j));
                gridPanel.add(cells[i][j]);
            }
        }
        cells[0][0].setBackground(Color.GREEN);
        cells[rows - 1][cols - 1].setBackground(Color.BLUE);
        add(gridPanel);
    }

}

class ButtonClickListener implements ActionListener {
    private int row;
    private int col;

    public ButtonClickListener(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int val = MainWindow.ActiveChoice();
        if (val == 0) {
            val = MainWindow.ActiveAlgoritm();
        }
        if (val == 1) {
            MainWindow.ClearVis();
            if (row == MainWindow.EndPointRow && col == MainWindow.EndPointCol) {
                JOptionPane.showMessageDialog(null, "Its End Point Cannot Be The Start Point");
            } else {
                Grid.cells[MainWindow.StartPointRow][MainWindow.StartPointCol].setBackground(Color.BLACK);
                Grid.cells[row][col].setBackground(Color.GREEN);
                if (MainWindow.Weight[row][col] > 1) {
                    MainWindow.Weight[row][col] = 1;
                    String buttonText = "<html><div style='text-align: center;'>" + "</div></html>";
                    Grid.cells[row][col].setText(buttonText);
                }
                MainWindow.StartPointRow = row;
                MainWindow.StartPointCol = col;
            }

        } else if (val == 2) {
            MainWindow.ClearVis();
            if (row == MainWindow.StartPointRow && col == MainWindow.StartPointCol) {
                JOptionPane.showMessageDialog(null, "Its Start Point Cannot Be The End Point");
            } else {
                Grid.cells[MainWindow.EndPointRow][MainWindow.EndPointCol].setBackground(Color.BLACK);
                Grid.cells[row][col].setBackground(Color.BLUE);
                if (MainWindow.Weight[row][col] > 1) {
                    MainWindow.Weight[row][col] = 1;
                    String buttonText = "<html><div style='text-align: center;'>" + "</div></html>";
                    Grid.cells[row][col].setText(buttonText);
                }
                MainWindow.EndPointRow = row;
                MainWindow.EndPointCol = col;
            }
        } else if (val == 3) {
            MainWindow.ClearVis();
            //wight
            MainWindow.Weight[row][col]++;
            if (MainWindow.Weight[row][col] > 1) {
                if ((row == MainWindow.StartPointRow && col == MainWindow.StartPointCol) || (row == MainWindow.EndPointRow && col == MainWindow.EndPointCol)) {
                    JOptionPane.showMessageDialog(null, "Its Start/End Point Cannot Have Weight");
                    MainWindow.Weight[row][col] = 1;
                } else {
                    Font font = new Font("Arial", Font.PLAIN, 16);
                    Color textColor = Color.WHITE;
                    String buttonText = "<html><div style='text-align: center;'>" + MainWindow.Weight[row][col] + "</div></html>";
                    Grid.cells[row][col].setFont(font);
                    Grid.cells[row][col].setForeground(textColor);
                    Grid.cells[row][col].setText(buttonText);
                }
            }
        } else if (val == 4) {
            MainWindow.ClearVis();
            MainWindow.Weight[row][col]--;
            if (MainWindow.Weight[row][col] > 1) {
                if ((row == MainWindow.StartPointRow && col == MainWindow.StartPointCol) || (row == MainWindow.EndPointRow && col == MainWindow.EndPointCol)) {
                    JOptionPane.showMessageDialog(null, "Its Start/End Point Cannot Have Weight");
                    MainWindow.Weight[row][col] = 1;
                } else {
                    Font font = new Font("Arial", Font.PLAIN, 16);
                    Color textColor = Color.WHITE;
                    String buttonText = "<html><div style='text-align: center;'>" + MainWindow.Weight[row][col] + "</div></html>";
                    Grid.cells[row][col].setFont(font);
                    Grid.cells[row][col].setForeground(textColor);
                    Grid.cells[row][col].setText(buttonText);
                }
            } else {
                MainWindow.Weight[row][col] = 1;
                Grid.cells[row][col].setText("");
            }
        } else if (val == 5) {
            MainWindow.ClearVis();
            if ((row == MainWindow.StartPointRow && col == MainWindow.StartPointCol) || (row == MainWindow.EndPointRow && col == MainWindow.EndPointCol)) {
                JOptionPane.showMessageDialog(null, "Its Start/End Point Cannot Be Obtical");
            } else {
                MainWindow.Vis[row][col] = 2;
                Grid.cells[row][col].setBackground(Color.RED);
            }
        } else if (val == 6) {
            MainWindow.ClearVis();
            if ((row == MainWindow.StartPointRow && col == MainWindow.StartPointCol) || (row == MainWindow.EndPointRow && col == MainWindow.EndPointCol)) {
                JOptionPane.showMessageDialog(null, "Its Start/End Point Cannot Remove");
            } else {
                MainWindow.Vis[row][col] = 0;
                Grid.cells[row][col].setBackground(Color.BLACK);
            }
        } else {
            MainWindow.ClearVis();
        }
    }
}
