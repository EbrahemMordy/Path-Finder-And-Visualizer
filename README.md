# Path Finder

A GUI Path Finder and Visualizing written in Java Swing.


# Features:

* **Interactive Node Management:** Set start and end points for algorithms with ease.
* **Algorithm Animation:** Visualize step-by-step execution of popular graph algorithms:
    * Depth-First Search (DFS)
    * Breadth-First Search (BFS)
    * Dijkstra's Algorithm (Shortest Path)
    * **More Coming Soon!**


***
# Choosing Start/End Point

- The user can select any start/end point from the grid and handle it if it is invalid, in which case the required cell is the other cell.

![Screen](https://github.com/EbrahemMordy/Path-Finder-And-Visualizer/blob/35636ccbbedd0c4d2d246d545f0a9cb6234304fc/Gifs/1-%20Start%20%26%20End%20Points.gif)

***
# Add/Remove Weights

- Every cell has a weight equal to 1 initially and starts printing a value if it is more than 1.

![Screen](https://github.com/EbrahemMordy/Path-Finder-And-Visualizer/blob/35636ccbbedd0c4d2d246d545f0a9cb6234304fc/Gifs/2-%20Add%20%26%20Remove%20Weights.gif)

***
# Add/Remove Obstacles

- Initially, every cell is a free cell, but the user can select any cell and make it a blocked "Obstacle" or free it again except Start/Endpoints.

![Screen](https://github.com/EbrahemMordy/Path-Finder-And-Visualizer/blob/35636ccbbedd0c4d2d246d545f0a9cb6234304fc/Gifs/3-%20Add%20%26%20Remove%20Obstacles.gif)

***
# Dijkstra (Shortest Path Algorithm)

![Screen](https://github.com/EbrahemMordy/Path-Finder-And-Visualizer/blob/35636ccbbedd0c4d2d246d545f0a9cb6234304fc/Gifs/4-%20Path%20With%20Dijkstra.gif)

***

# Tools and Technologies

**Programming Language:** Java
**GUI Framework:** Swing
* Components:
    * JFrame (main window)
    * JButtons (user interaction)
    * JPanel (container for UI elements)
    * etc.
**Concurrency:** SwingWorker
* Used for background execution of algorithm visualization and printing, ensuring a responsive UI.
**Data Structures:** Java Collections
* Utilized for storing and manipulating graph data efficiently.

***
# Installing JDK and Setting the System Path

To run the project, you'll need to have Java JDK 8 or higher installed on your system.

## 1. Download JDK

Visit the [Oracle website](https://www.oracle.com/java/technologies/downloads/) and download the appropriate JDK version.

## 2. Install JDK

Run the downloaded installer and follow the on-screen instructions to install the JDK.

## 3. Set System Path

- Right-click on `This PC` or `Computer` on the desktop or in File Explorer.
- Choose `Properties`.
- Click on `Advanced system settings` on the left.
- Click on the `Environment Variables` button.
- Under `System Variables`, find

and select the `Path` variable, then click on `Edit`.
- Click on `New` and add the path to the `bin` directory of your JDK installation. For example: `C:\Program Files\Java\jdk-21\bin`.
- Click `OK` to close each window.

***
# Running the Project

1. **Clone the Repository:** Clone this Git repository to your local machine using the following command:
   ```
   git clone https://github.com/EbrahemMordy/Path-Finder-And-Visualizer
   ```
   
2. **Navigate to the Project Directory:** Change your current directory to the root directory of the cloned project:
   ```
   cd Path-Finder-And-Visualizer
   ```

3. **Compile the Java Files:** Compile the Java source files using the `javac` command.
   ```
   javac Main.java
   ```

4. **Run the Application:** Once the Java files are compiled successfully, you can run the application using the `java` command followed by the name of the main class.
   ```
   java Main
   ```
