import java.util.stream.IntStream;

public class TriangleSnail {
    public static void main(String[] args) {
        int n = 4;

        int[] a = solution(n);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static int[] solution(int n) {
        int[][] tri = new int[n][n];
        int i = 0;
        int j = 0;
        int idx = 1;
        tri[0][0] = 1;

        // 1~n 합(삼각형 외곽부터 n...1번까지 순회하므로)
        int max = IntStream.rangeClosed(1, n).sum();

        while (idx < max) {
            while (i + 1 < n && idx < max && tri[i + 1][j] == 0) {
                tri[++i][j] = ++idx;
            }

            while (j + 1 < n && idx < max && tri[i][j + 1] == 0) {
                tri[i][++j] = ++idx;
            }

            while (i - 1 >= 0 && j - 1 >= 0 && idx < max && tri[i - 1][j - 1] == 0) {
                tri[--i][--j] = ++idx;
            }
        }

        idx = 0;
        int[] answer = new int[max];
        for (i = 0; i < n; i++) {
            for (j = 0; j <= i; j++) {
                answer[idx++] = tri[i][j];
            }
        }

        return answer;
    }
}
