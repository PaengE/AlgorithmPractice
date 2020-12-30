import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.9345: 디지털 비디오 디스크(DVDs)
 *  Description: 관찰이 필요한 세그먼트 트리 응용문제
 *  Hint: 특정 범위 안에 최솟값과 최댓값이 변하지 않으면 그 범위의 합은 변하지 않음.
 */

public class BOJ9345 {
    static int[] maxTree, minTree, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = i;
            }

            // 트리 생성
            maxTree = new int[4 * n];
            minTree = new int[4 * n];

            // 트리 생성(최댓값: true, 최솟값: false)
            init(1, n, 1, true);
            init(1, n, 1, false);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken()) + 1;
                int b = Integer.parseInt(st.nextToken()) + 1;

                if (op == 0) {
                    // 최댓값 트리 갱신
                    update(1, n, 1, a, arr[b], true);
                    update(1, n, 1, b, arr[a], true);

                    // 최솟값 트리 갱신
                    update(1, n, 1, a, arr[b], false);
                    update(1, n, 1, b, arr[a], false);

                    // 실제 값 swap
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                } else {
                    if (a == find(1, n, 1, a, b, false) && b == find(1, n, 1, a, b, true)) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 최댓값 세그먼트 트리(flag = true), 최솟값 세그먼트 트리(flag = false) 구성
    static int init(int start, int end, int node, boolean flag) {
        int mid = (start + end) / 2;

        if (flag) { // 최댓값 트리

            if (start == end) { // 터미널 노드
                return maxTree[node] = arr[start];
            }

            // 논터미널 노드의 경우, 자기 밑의 모든수의 최댓 값을 저장
            return maxTree[node] = Math.max(init(start, mid, node * 2, true), init(mid + 1, end, node * 2 + 1, true));

        } else {    // 최솟값 트리

            if (start == end) { // 터미널 노드
                return minTree[node] = arr[start];
            }

            // 논터미널 노드의 경우, 자기 밑의 모든 수의 최솟값을 저장
            return minTree[node] = Math.min(init(start, mid, node * 2, false), init(mid + 1, end, node * 2 + 1, false));

        }
    }


    // 최댓값, 최솟값 찾기
    static int find(int start, int end, int node, int left, int right, boolean flag) {
        int mid = (start + end) / 2;

        if (flag) { // 최댓값 트리

            // 범위 밖에 있으면
            if (end < left || right < start) {
                return Integer.MIN_VALUE;
            }

            // 범위를 감싸면
            if (left <= start && end <= right) {
                return maxTree[node];
            }

            // 둘 다 아니라면 두 부분으로 나누어 최댓값을 취함
            return Math.max(find(start, mid, node * 2, left, right, true), find(mid + 1, end, node * 2 + 1, left, right, true));

        } else {    // 최솟값 트리

            // 범위 밖에 있으면
            if (end < left || right < start) {
                return Integer.MAX_VALUE;
            }

            // 범위를 감싸면
            if (left <= start && end <= right) {
                return minTree[node];
            }

            // 둘 다 아니라면 두 부분으로 나누어 최솟값을 취함
            return Math.min(find(start, mid, node * 2, left, right, false), find(mid + 1, end, node * 2 + 1, left, right, false));
        }
    }

    // 최댓값, 최솟값 트리를 갱신하는 메소드
    static int update(int start, int end, int node, int index, int value, boolean flag) {
        int mid = (start + end) / 2;

        if (flag) { // 최댓값 트리

            // 범위를 벗어나면
            if (end < index || index < start) {
                return maxTree[node];

            } else if (start == end) {
                // 범위를 벗어나지 않고 터미널노드에 도착하면, 값 갱신
                return maxTree[node] = value;
            }

            // 최댓값을 다시 계산
            return maxTree[node] = Math.max(update(start, mid, node * 2, index, value, true), update(mid + 1, end, node * 2 + 1, index, value, true));

        } else {    // 최솟값 트리

            // 범위를 벗어나면
            if (end < index || index < start) {
                return minTree[node];

            } else if (start == end) {
                // 범위를 벗어나지 않고 터미널노드에 도착하면, 값 갱신
                return minTree[node] = value;
            }

            // 최솟값을 다시 계산
            return minTree[node] = Math.min(update(start, mid, node * 2, index, value, false), update(mid + 1, end, node * 2 + 1, index, value, false));

        }
    }
}
