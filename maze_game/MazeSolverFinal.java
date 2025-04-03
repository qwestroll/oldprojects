/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazesolverproject;

/**
 *
 * @author qwest
 */
public class MazeSolverFinal {

    static int direction = 1;  //0,north;1,east;2,south;3,west
    static int x, y;

    //satır,sütun veya y,x 
    public static void main(String[] args) {
        int grid = 5;
        int[][] maze = getMaze(grid);
        int f = 2 * (grid - 1) + 1;//formula
        x = 1;//belki x ve y nin yerine stack kullanılabilir(Location)
        y = 1;
        Stack path = new Stack<Location>();
        maze[y][x] = 2;
        path.push(new Location(1, 1, 1));
        while (maze[f][f] != 2) {
            MazeUtility.plotMaze(maze);
            Location tmp = move(maze);
            path.push(tmp);
            if (!path.isEmpty()) {
                maze[y][x] = 2;
            }
        }
        MazeUtility.plotMaze(maze);
        // To do: starting from the coordinates [1,1], use the path stack to navigate in the maze and 
        // find a way to [2*grid-1, 2*grid-1] coordinates
        // use the following code to print the maze at each step
        //MazeUtility.plotMaze(maze);  
        // DO NOT change any of the given code
    }

    public static int[][] getMaze(int grid) {
        MazeGenerator maze = new MazeGenerator(grid);
        String str = maze.toString();

        int[][] maze2D = MazeUtility.Convert2D(str);
        return maze2D;
    } /////////////////////////////////  

    public static Location move(int[][] a) {

        if (isWallChecker(a, direction + 1)) {///elimi sağ duvara koydum gidiyorum
            if (isWallChecker(a, direction)) {/////önümde duvar var mı?
                if (isWallChecker(a, direction - 1)) {///solumda da varsa geri döneyim?
                    turnRight();
                    turnRight();

                } else {
                    turnRight();
                    turnRight();
                    turnRight();
                }
            }

        } else {
            turnRight();
        }

        switch (direction) {
            case 0:
                a[y][x] = 0;
                y--;

                break;
            case 1:
                a[y][x] = 0;
                x++;
                break;
            case 2:
                a[y][x] = 0;
                y++;
                break;
            case 3:
                a[y][x] = 0;
                x--;
                break;

        }
        Location l = new Location(x, y, direction);
        return l;
    }

    public static boolean isWallChecker(int[][] a, int tmpDir) {
        boolean isWall = false;
        if (tmpDir < 0) {
            tmpDir = 3;
        }
        if (tmpDir > 3) {
            tmpDir = 0;
        }
        switch (tmpDir) {
            case 0:
                if (a[y - 1][x] == 1) {
                    isWall = true;
                }
                break;
            case 1:
                if (a[y][x + 1] == 1) {
                    isWall = true;
                }
                break;
            case 2:
                if (a[y + 1][x] == 1) {
                    isWall = true;
                }
                break;
            case 3:
                if (a[y][x - 1] == 1) {
                    isWall = true;
                }
                break;

        }
        return isWall;
    }

    public static void turnRight() {
        if (direction == 3) {
            direction = 0;
        } else {
            direction++;
        }
    }
}