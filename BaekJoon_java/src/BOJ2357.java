import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2357: 최솟값과 최댓값
 *  Hint: SegmentTree 를 이용한 최솟값과 최댓값
 */

public class BOJ2357 {
    static int[] minTree, maxTree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        minTree = new int[4 * n];
        maxTree = new int[4 * n];

        init(1, n, 1, true);    // minTree
        init(1, n, 1, false);   // maxTree


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(min(1, n, 1, a, b) + " " + max(1, n, 1, a, b) + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param flag  true: minTree, false: maxTree
     * @return  재귀적으로 두 부분으로 나눈 뒤 그 최솟값 or 최댓값(나눌수 없으면 자기 자신)
     */
    static int init(int start, int end, int node, boolean flag) {
        if (start == end) {
            if (flag) {
                return minTree[node] = arr[start];
            } else {
                return maxTree[node] = arr[start];
            }
        }

        int mid = (start + end) / 2;

        if (flag) {
            return minTree[node] = Math.min(init(start, mid, node * 2, true), init(mid + 1, end, node * 2 + 1, true));
        } else {
            return maxTree[node] = Math.max(init(start, mid, node * 2, false), init(mid + 1, end, node * 2 + 1, false));
        }
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param left  구하려는 구간의 왼쪽 끝
     * @param right 구하려는 구간의 오른쪽 끝
     * @return  left ~ right 구간의 최댓값
     */
    static int min(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return minTree[node];
        }

        // 그렇지 않다면 두 부분으로 나누어 합 구하기
        int mid = (start + end) / 2;
        return Math.min(min(start, mid, node * 2, left, right), min(mid + 1, end, node * 2 + 1, left, right));
    }

    /**
     * @param start 시작 인덱스
     * @param end   끝 인덱스
     * @param node  노드 번호
     * @param left  구하려는 구간의 왼쪽 끝
     * @param right 구하려는 구간의 오른쪽 끝
     * @return  left ~ right 구간의 최댓값
     */
    static int max(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return Integer.MIN_VALUE;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return maxTree[node];
        }

        // 그렇지 않다면 두 부분으로 나누어 합 구하기
        int mid = (start + end) / 2;
        return Math.max(max(start, mid, node * 2, left, right), max(mid + 1, end, node * 2 + 1, left, right));
    }
}
