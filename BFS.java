import java.util.*;

public class Main {
    public static String SearchingChallenge(String[] strArr) {
        int rows = strArr.length;
        int cols = strArr[0].length();
        boolean[][] visited = new boolean[rows][cols];

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && strArr[i].charAt(j) == '0') {
                    bfs(i, j, strArr, visited, directions);
                    count++;
                }
            }
        }

        return String.valueOf(count);
    }

    private static void bfs(int i, int j, String[] strArr, boolean[][] visited, int[][] directions) {
        int rows = strArr.length;
        int cols = strArr[0].length();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : directions) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    !visited[newRow][newCol] && strArr[newRow].charAt(newCol) == '0') {
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(SearchingChallenge(new String[]{"01111", "01001", "00011", "11110"})); // 3
        System.out.println(SearchingChallenge(new String[]{"1011", "0010"})); // 2
    }
}
