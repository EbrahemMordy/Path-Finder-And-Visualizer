import javax.swing.*;
import java.awt.*;
import java.util.Queue;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class MainWindow extends JFrame {
    public static SelectionMenu selectionMenu;
    public static int[][] Vis;
    public static ArrayList<ArrayList<pair>> Par;
    public static int[][] Weight;
    private static Grid grid;
    public static int StartPointRow = 0, StartPointCol = 0;
    public static int EndPointRow = Grid.rows - 1, EndPointCol = Grid.cols - 1;
    public static ArrayList<pair> Parent;
    public static ArrayList<pair> Child;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public MainWindow() {
        super("Path Finder");
        selectionMenu = new SelectionMenu();
        grid = new Grid();
        Weight = new int[Grid.rows][Grid.cols];
        Vis = new int[Grid.rows][Grid.cols];
        Par = new ArrayList<ArrayList<pair>>();
        for (int i = 0; i < Grid.rows; i++) {
            ArrayList<pair> temp = new ArrayList<>();
            for (int j = 0; j < Grid.cols; j++) {
                temp.add(new pair(-1, -1));
            }
            Par.add(temp);
        }
        Parent = new ArrayList<pair>();
        Child = new ArrayList<pair>();
        for (int i = 0; i < Grid.rows; i++) {
            Arrays.fill(Weight[i], 1);
            Arrays.fill(Vis[i], 0);
        }
        add(grid, BorderLayout.CENTER);
        add(selectionMenu, BorderLayout.EAST);
    }

    public static int ActiveChoice() {
        for (int i = 0; i < 6; i++) {
            if (SelectionMenu.Buttons[i].isSelected()) {
                return (i + 1);
            }
        }
        return 0;
    }

    public static int ActiveAlgoritm() {
        for (int i = 6; i < 9; i++) {
            if (SelectionMenu.Buttons[i].isSelected()) {
                return (i + 1);
            }
        }
        return 0;
    }

    private static boolean valid(int curRow, int curCol) {
        if (0 <= curRow && curRow < Grid.rows && 0 <= curCol && curCol < Grid.cols) return true;
        return false;
    }

    public static void runDFS(int curRow, int curCol) {
        DFSSwingWorker DFSSwingWorker = new DFSSwingWorker(curRow, curCol);
        DFSSwingWorker.execute();
    }

    public static void runBFS(int curRow, int curCol) {
        BFSSwingWorker BFSSwingWorker = new BFSSwingWorker(curRow, curCol);
        BFSSwingWorker.execute();
    }

    public static void runDIJ(int curRow, int curCol) {
        DijSwingWorker DijSwingWorker = new DijSwingWorker(curRow, curCol);
        DijSwingWorker.execute();
    }

    public static boolean DFS(int curRow, int curCol) {
        if (curRow == EndPointRow && curCol == EndPointCol) {
            return true;
        }
        if ((curRow == StartPointRow && curCol == StartPointCol) == false) {
            Grid.cells[curRow][curCol].setBackground(Color.YELLOW);
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Vis[curRow][curCol] = 1;
        for (int d = 0; d < 4; d++) {
            int nxtRow = curRow + dx[d];
            int nxtCol = curCol + dy[d];
            if (valid(nxtRow, nxtCol) && Vis[nxtRow][nxtCol] == 0) {
                if (DFS(nxtRow, nxtCol)) {
                    return true;
                }
            }
        }
        if (!(curRow == StartPointRow && curCol == StartPointCol)) {
            Grid.cells[curRow][curCol].setBackground(Color.BLACK);
        }
        return false;
    }

    public static boolean BFS(int curRow, int curCol) {
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(curRow, curCol));
        while (!q.isEmpty()) {
            pair cell = q.peek();
            int x = cell.first;
            int y = cell.second;
            q.remove();
            if (x == EndPointRow && y == EndPointCol) {
                return true;
            }
            if ((x == StartPointRow && y == StartPointCol) == false) {
                Grid.cells[x][y].setBackground(Color.YELLOW);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Vis[x][y] = 1;
            for (int i = 0; i < 4; i++) {
                int adjx = x + dx[i];
                int adjy = y + dy[i];
                if (valid(adjx, adjy) && Vis[adjx][adjy] == 0) {
                    Par.get(adjx).get(adjy).first = x;
                    Par.get(adjx).get(adjy).second = y;
                    q.add(new pair(adjx, adjy));
                    Vis[adjx][adjy] = 1;
                }
            }
        }
        return false;
    }

    public static boolean Dij(int curRow, int curCol) {
        PriorityQueue<pair2> q = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.cost, b.cost);
        });
        q.add(new pair2(0, curRow, curCol));
        while (!q.isEmpty()) {
            pair2 cell = q.peek();
            int cost = cell.cost;
            int x = cell.first;
            int y = cell.second;
            q.remove();
            if (x == EndPointRow && y == EndPointCol) {
                return true;
            }
            if ((x == StartPointRow && y == StartPointCol) == false) {
                Grid.cells[x][y].setBackground(Color.YELLOW);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Vis[x][y] = 1;
            for (int i = 0; i < 4; i++) {
                int adjx = x + dx[i];
                int adjy = y + dy[i];
                if (valid(adjx, adjy) && Vis[adjx][adjy] == 0) {
                    Par.get(adjx).get(adjy).first = x;
                    Par.get(adjx).get(adjy).second = y;
                    q.add(new pair2(cost + Weight[adjx][adjy], adjx, adjy));
                    Vis[adjx][adjy] = 1;
                }
            }
        }
        return false;
    }

    public static void ClearVis() {
        for (int i = 0; i < Grid.rows; i++) {
            for (int j = 0; j < Grid.cols; j++) {
                if (Vis[i][j] == 1) {
                    Grid.cells[i][j].setBackground(Color.BLACK);
                    Vis[i][j] = 0;
                }
                if (i == StartPointRow && j == StartPointCol) {
                    Grid.cells[i][j].setBackground(Color.GREEN);
                }
                if (i == EndPointRow && j == EndPointCol) {
                    Grid.cells[i][j].setBackground(Color.BLUE);
                }
            }
        }
    }

    public static void ClearObstacles() {
        for (int i = 0; i < Grid.rows; i++) {
            for (int j = 0; j < Grid.cols; j++) {
                if (Vis[i][j] == 2) {
                    Vis[i][j] = 0;
                    Grid.cells[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    public static void ClearWeights() {
        for (int i = 0; i < Grid.rows; i++) {
            for (int j = 0; j < Grid.cols; j++) {
                Weight[i][j] = 1;
            }
        }
    }

    public static class BFSSwingWorker extends SwingWorker<Boolean, Point> {

        private int curRow;
        private int curCol;

        public BFSSwingWorker(int curRow, int curCol) {
            this.curRow = curRow;
            this.curCol = curCol;
        }

        @Override
        protected Boolean doInBackground() {
            ClearVis();
            return BFS(curRow, curCol);
        }

        @Override
        protected void done() {
            try {
                boolean result = get();
                if (result) {
                    for (int i = 0; i < Grid.rows; i++) {
                        for (int j = 0; j < Grid.cols; j++) {
                            if (Vis[i][j] == 1) {
                                Vis[i][j] = 0;
                                if (i == StartPointRow && j == StartPointCol) {
                                    continue;
                                }
                                if (i == EndPointRow && j == EndPointCol) {
                                    continue;
                                }
                                Grid.cells[i][j].setBackground(Color.BLACK);
                            }
                        }
                    }
                    int PathRow = Par.get(EndPointRow).get(EndPointCol).first;
                    int PathCol = Par.get(EndPointRow).get(EndPointCol).second;
                    while (PathRow != StartPointRow || PathCol != StartPointCol) {
                        Grid.cells[PathRow][PathCol].setBackground(Color.YELLOW);
                        Vis[PathRow][PathCol] = 1;
                        int nxtRow = Par.get(PathRow).get(PathCol).first;
                        int nxtCol = Par.get(PathRow).get(PathCol).second;
                        PathRow = nxtRow;
                        PathCol = nxtCol;
                    }
                    JOptionPane.showMessageDialog(null, "BFS found a path!");
                } else {
                    JOptionPane.showMessageDialog(null, "BFS did not find a path.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DijSwingWorker extends SwingWorker<Boolean, Point> {

        private int curRow;
        private int curCol;

        public DijSwingWorker(int curRow, int curCol) {
            this.curRow = curRow;
            this.curCol = curCol;
        }

        @Override
        protected Boolean doInBackground() {
            ClearVis();
            return Dij(curRow, curCol);
        }

        @Override
        protected void done() {
            try {
                boolean result = get();
                if (result) {
                    for (int i = 0; i < Grid.rows; i++) {
                        for (int j = 0; j < Grid.cols; j++) {
                            if (Vis[i][j] == 1) {
                                Vis[i][j] = 0;
                                if (i == StartPointRow && j == StartPointCol) {
                                    continue;
                                }
                                if (i == EndPointRow && j == EndPointCol) {
                                    continue;
                                }
                                Grid.cells[i][j].setBackground(Color.BLACK);
                            }
                        }
                    }
                    int PathRow = Par.get(EndPointRow).get(EndPointCol).first;
                    int PathCol = Par.get(EndPointRow).get(EndPointCol).second;
                    while (PathRow != StartPointRow || PathCol != StartPointCol) {
                        Grid.cells[PathRow][PathCol].setBackground(Color.YELLOW);
                        Vis[PathRow][PathCol] = 1;
                        int nxtRow = Par.get(PathRow).get(PathCol).first;
                        int nxtCol = Par.get(PathRow).get(PathCol).second;
                        PathRow = nxtRow;
                        PathCol = nxtCol;
                    }
                    JOptionPane.showMessageDialog(null, "Dijkstra found a path!");
                } else {
                    JOptionPane.showMessageDialog(null, "Dijkstra did not find a path.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DFSSwingWorker extends SwingWorker<Boolean, Point> {

        private int curRow;
        private int curCol;

        public DFSSwingWorker(int curRow, int curCol) {
            this.curRow = curRow;
            this.curCol = curCol;
        }

        @Override
        protected Boolean doInBackground() {
            ClearVis();
            return DFS(curRow, curCol);
        }

        @Override
        protected void done() {
            try {
                boolean result = get();
                if (result) {
                    JOptionPane.showMessageDialog(null, "DFS found a path!");
                } else {
                    JOptionPane.showMessageDialog(null, "DFS did not find a path.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class pair {
    int first, second;

    public pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class pair2 {
    int cost;
    int first, second;

    public pair2(int cost, int first, int second) {
        this.cost = cost;
        this.first = first;
        this.second = second;
    }
}
