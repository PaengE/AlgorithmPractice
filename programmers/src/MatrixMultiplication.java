public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        MatrixMultiplication mm = new MatrixMultiplication();

        int[][] t = mm.solution(arr1, arr2);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int k = arr1[0].length;
        int m = arr2[0].length;
        int[][] ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    ans[i][j] += arr1[i][l] * arr2[l][j];
                }
            }
        }
        return ans;
    }
}
