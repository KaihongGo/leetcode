import java.util.Arrays;

/**
 * 我也很疑惑，错误案例自己测试对了！提交上去不对😭
 */
public class Solution {

    public void solve(char[][] board) {
        if (board.length == 0)
            return;
        int m = board.length;
        int n = board[0].length;

        int dummy = m * n;
        UF uf = new UF(m * n + 1);
        int[][] d = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (board[i][j] == 'O') {
                        uf.union(index, dummy);
                    }
                } else {
                    if (board[i][j] == 'O') {
                        for (int k = 0; k < 4; k++) {
                            int x = i + d[k][0];
                            int y = j + d[k][1];
                            if (board[x][y] == 'O') {
                                uf.union(x * n + y, index);
                            }
                        }
                    }
                }
            }
        }
        changeBoard(board, uf);
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void changeBoard(char[][] board, UF uf) {
        int m = board.length;
        int n = board[0].length;
        int dummy = m * n;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][] { { 'O', 'X', 'X', 'O', 'X' }, { 'X', 'O', 'O', 'X', 'O' },
                { 'X', 'O', 'X', 'O', 'X' }, { 'O', 'X', 'O', 'O', 'O' }, { 'X', 'X', 'O', 'X', 'O' } };
        printBoard(board);
        System.out.println("----------------------------");

        Solution s = new Solution();
        s.solve(board);
        printBoard(board);
        System.out.println("----------------------------");

    }
}