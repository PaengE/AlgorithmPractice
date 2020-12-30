import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.16975: 수열과 쿼리 21
 *  Description: 구간에 수를 더하고, 한 칸의 값만 가져오는 문제(Lazy Propagation 사용하지 않아도 됨)
 *  Hint: SegmentTree 사용
 */

public class BOJ16975 {
    static int[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        tree = new long[4 * n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 초기화
        init(1, n, 1);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                update(1, n, 1, a, b, val);
            } else {
                int x = Integer.parseInt(st.nextToken());
                long res = find(1, n, 1, x);
                sb.append(String.valueOf(res) + "\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 세그먼트 트리 초기화(터미널 노드만 초기화)
    static void init(int start, int end, int node) {
        // 터미널노드
        if (start == end) {
            tree[node] = arr[start];
            return ;
        }
        int mid = (start + end) / 2;

        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
    }

    // 최소의 노드 개수만 update(범위를 감쌀 수 있는)
    // 1~4에 k를 더하려는데, 1~3범위를 가리키는 노드와 4~4범위를 가리키는 노드가 있다면
    // 그 두 노드에ㅐ만 k를 더해줌.
    static void update(int start, int end, int node, int left, int right, int val) {
        // 범위를 벗어날 경우
        if (end < left || right < start) {
            return;
        }

        // 범위안에 있다면 val 을 추가하고 더 이상 내려가지 않음
        if (left <= start && end <= right) {
            tree[node] += val;
            return;
        }

        int mid = (start + end) / 2;

        update(start, mid, node * 2, left, right, val);
        update(mid + 1, end, node * 2 + 1, left, right, val);
    }

    // index~index 를 가리키는 노드부터 루트노드까지 올라오면서 더하면서 찾음
    static long find(int start, int end, int node, int index) {
        // 범위를 벗어날 경우
        if (end < index || index < start) {
            return 0;
        }

        // 터미널노드일 경우 (index ~ index 범위를 가지는 노드인 경우)
        if (start == end) {
            return tree[node];

        }
        // 터미널노드가 아닐경우 현재노드의 값을 더한후 밑으로 내려감
        else {
            int mid = (start + end) / 2;

            return tree[node] + find(start, mid, node * 2, index) + find(mid + 1, end, node * 2 + 1, index);
        }

    }
}
