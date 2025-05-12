import java.util.Scanner;
import java.util.List;

public class Main {
    static Scanner input = new Scanner(System.in);
    static void PrintBoard(boolean[][] b){
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if (b[i][j])
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }

            System.out.println();
        }
    }

    static boolean[][] initialize(boolean[][] b){
        System.out.println("Enter some starting coordinates, enter -1 for stopping, rows are first and columns are second:");
        int x = input.nextInt();
        int y = input.nextInt();
        if(x!=-1 && y!=-1)
            b[x][y] = true;
        while(x!=-1 && y!=-1){
            System.out.println("Player inserted!");
            x = input.nextInt();
            y = input.nextInt();
            if(x!=-1 && y!=-1)
                b[x][y] = true;
        }
        return b;
    }
    static int ncount(int r, int c, boolean[][] b) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};  // Row offsets
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};  // Column offsets

        for (int i = 0; i < 8; i++) {
            int newRow = r + dx[i];
            int newCol = c + dy[i];

            // Check that the neighbor is within bounds
            if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
                if (b[newRow][newCol]) {
                    count++;
                }
            }
        }

        return count;
    }

    static boolean GameOver (boolean[][] b, boolean[][] newb){
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if( b[i][j] != newb[i][j] )
                    return false;
            }
        }
        return true;
    }
    static boolean[][] copy(boolean[][] b){
        boolean[][] cpy = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cpy[i][j] = b[i][j];
            }
        }
        return cpy;
    }
    static void Game(boolean[][] b) {
        int number_of_n;
        boolean[][] newBoard = new boolean[10][10];
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++)
                newBoard[i][j] = false;
        }

        while (true) {
            System.out.println();



            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    number_of_n = ncount(i, j, b);
                    if (b[i][j]) {
                        newBoard[i][j] = (number_of_n == 2 || number_of_n == 3);
                    } else {
                        newBoard[i][j] = (number_of_n == 3);
                    }
                }
            }
            if (GameOver(b, newBoard)) break;  // check AFTER update

            b = copy(newBoard);
            PrintBoard(newBoard);

            try {
                Thread.sleep(400); // Add delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Game's over!");
    }
        public static void main(String[] args) {
        boolean[][] board = new boolean[10][10];
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++)
                board[i][j] = false;
        }

//        board[1][2] = true;
//        board[2][3] = true;
//        board[3][1] = true;
//        board[3][2] = true;
//        board[3][3] = true;
        PrintBoard(board);

        initialize(board);
        PrintBoard(board);
        Game(board);








    }
}
