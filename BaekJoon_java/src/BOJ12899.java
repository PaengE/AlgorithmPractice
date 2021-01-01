import java.io.*;
import java.util.*;

/**
 *  No.12899: 데이터 구조
 *  Description: 세그먼트 트리를 활용하여 K번째 원소를 효율적으로 찾는 문제
 *  유형 1: 그 인덱스가 포함된 모든 노드에 +1을 더한 값으로 업데이트
 *  유형 2: 이분탐색으로 X번째 원소를 찾은후, 그 원소가 포함된 모든 노드에 -1을 더한 값으로 업데이트
 */

public class BOJ12899 {
    static int[] tree;
    static int m = 2000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        tree = new int[4 * m];

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                int num = Integer.parseInt(st.nextToken());
                update(1, m, 1, num, 1);
            } else {
                int x = Integer.parseInt(st.nextToken());
                int val = query(1, m, 1, x);
                sb.append(val + "\n");
                update(1, m, 1, val, -1);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    // SegmentTree update
    static void update(int start, int end, int node, int index, int val) {
        if (index < start || end < index) {
            return ;
        }

        tree[node] += val;
        if (start == end) {
            return ;
        }

        int mid = (start + end) / 2;

        update(start, mid, node * 2, index, val);
        update(mid + 1, end, node * 2 + 1, index, val);
    }

    static int query(int start, int end, int node, int x) {
        // 단말노드에 도착하면
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        // 이분 탐색
        if (tree[node * 2] >= x) {
            return query(start, mid, node * 2, x);
        } else {
            return query(mid + 1, end, node * 2 + 1, x - tree[node * 2]);
        }
    }
}
