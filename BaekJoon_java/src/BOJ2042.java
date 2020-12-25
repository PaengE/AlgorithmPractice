import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2042: 구간 합 구하기
 *  Hint: SegmentTree 를 활용한 구간 합 구하기
 */

public class BOJ2042 {
    static long[] tree, arr;

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
                long diff = c - arr[b];
                arr[b] = c;
                update(1, n, 1,  b, diff);
            } else if (a == 2) {
                bw.write(String.valueOf(sum(1, n, 1, b, c)));
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
     * @return  재귀적으로 두 부분으로 나눈 뒤 그 합(나눌수 없으면 자기 자신)
     */
    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param left  구하려는 구간의 왼쪽 끝
     * @param right 구하려는 구간의 오른쪽 끝
     * @return  left ~ right 구간의 합
     */
    static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 그렇지 않다면 두 부분으로 나누어 합 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param index 값이 바뀐 배열의 인덱스
     * @param diff  원래있던 값과 새로운 값의 차이
     */
    static void update(int start, int end, int node, int index, long diff) {
        // 범위 밖에 있는 경우
        if (index < start || index > end) {
            return;
        }

        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += diff;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }
}