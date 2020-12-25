import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.11505: 구간 곱 구하기
 *  Hint: SegmentTree 를 활용한 구간 곱 구하기
 *
 *      하향식으로 update를 하면 안되는 이유
 *      : 오버플로우된 값을 Mod하여 부모에 저장하면, 원래 값을 나누더라도
 *        원래 값과 달라지기 때문에, 바뀐 단말노드 부터 올라오면서 구간 곱을
 *        다시 계산해야 한다.
 */

public class BOJ11505 {
    static long[] tree, arr;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        tree = new long[4 * n];

        init(1, n, 1);

        int t = m + k;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, n, 1,  b, c);
                arr[b] = c;

            } else if (a == 2) {
                bw.write(String.valueOf(mul(1, n, 1, b, c)));
                bw.newLine();
            }
        }

        bw.close();
        br.close();
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @return  재귀적으로 두 부분으로 나눈 뒤 그 곱(나눌수 없으면 자기 자신)
     */
    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param left  구하려는 구간의 왼쪽 끝
     * @param right 구하려는 구간의 오른쪽 끝
     * @return  left ~ right 구간의 곱
     */
    static long mul(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return 1;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 그렇지 않다면 두 부분으로 나누어 곱 구하기
        int mid = (start + end) / 2;
        return mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right) % MOD;
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param index 값이 바뀐 배열의 인덱스
     * @param value 새로이 바뀐 값
     */
    static long update(int start, int end, int node, int index, long value) {
        // 범위 밖에 있는 경우
        if (index < start || index > end) {
            return tree[node];
        }
        // 터미널 노드에 도착 했으면(자식이 없으면)
        else if (start == end) {
            return tree[node] = value;
        }

        // 범위 안에 있으면 밑에서부터 올라오면서 연결된 모든 부모노드는 다시 계산
        int mid = (start + end) / 2;

        return tree[node] = (update(start, mid, node * 2, index, value) * update(mid + 1, end, node * 2 + 1, index, value)) % MOD;
    }
}
