public class QuadCompression {
    static int[] answer = {0, 0};
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1}};

        QuadCompression qc = new QuadCompression();
        int[] a = qc.solution(arr);

        for (int t : a) {
            System.out.println(t);
        }
    }

    public int[] solution(int[][] arr) {
        dq(0, 0, arr.length, arr);

        return answer;
    }

    static void dq(int sx, int sy, int size, int[][] arr) {
        int element = arr[sx][sy];

        if (size == 1) {
            answer[element] += 1;
        } else {
            boolean same = true;
            for (int i = sx; i < sx + size; i++) {
                for (int j = sy; j < sy + size; j++) {
                    if (arr[i][j] != element) {
                        same = false;
                        break;
                    }
                }

                if (!same) {
                    break;
                }
            }

            if (same) {
                answer[element] += 1;
            } else {
                int half = size / 2;
                dq(sx, sy, half, arr);
                dq(sx, sy + half, half, arr);
                dq(sx + half, sy, half, arr);
                dq(sx + half, sy + half, half, arr);
            }
        }
    }
}
