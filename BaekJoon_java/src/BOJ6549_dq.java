import java.io.*;
import java.util.StringTokenizer;

/**
 * no.6549 : 히스토그램에서 가장 큰 직사각형
 * title : 히스토그램에서 가장 큰 직사각형을 찾는 문제.
 * hint : segment tree + divide & conquer
 */

public class BOJ6549_dq {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = null;
        SegmentTree segmentTree;

        while(!s.equals("0")){
            st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            segmentTree = new SegmentTree(arr);
            bw.write(segmentTree.getMaxWidth(0, n - 1) + "\n");

            s = br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class SegmentTree {
    private int n;
    private int[] arr, rangeMinIndex;
    private final int INF = 0x7fffffff;

    // segment tree 생성 및 초기화
    public SegmentTree(int[] arr) {
        n = arr.length;
        this.arr = arr.clone();
        rangeMinIndex = new int[n << 2];

        init(0, n - 1, 1);
    }

    // segment tree 초기 구성 -> 부모노드는 두개의 자식노드 중 작은 값으로 설정됨
    private int init(int left, int right, int node) {
        if (left == right)
            return rangeMinIndex[node] = left;

        int mid = (left + right) / 2;
        int leftMinIndex = init(left, mid, node * 2);
        int rightMinIndex = init(mid + 1, right, (node * 2) + 1);

        return rangeMinIndex[node]
                = arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    // segment tree 를 이용하여 최소 높이를 가지는 막대의 인덱스 조회
    private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
        // tree index 를 벗어났을 때의 예외 처리
        if (nodeRight < left || nodeLeft > right)
            return INF;

        if (left <= nodeLeft && nodeRight <= right) {
            return rangeMinIndex[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        int leftMinIndex = query(left, right, node * 2, nodeLeft, mid);
        int rightMinIndex = query(left, right, (node * 2) + 1, mid + 1, nodeRight);

        if (leftMinIndex == INF)
            return rightMinIndex;
        else if (rightMinIndex == INF)
            return leftMinIndex;
        else
            return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }
    
    // 가장 큰 직사각형의 넓이를 구하는 메소드
    public long getMaxWidth(int left, int right) {
        long maxWidth, tmpWidth
                ;
        // 높이가 최소인 인덱스
        int minIndex = query(left, right, 1, 0, n - 1);

        // 직사각형의 넓이 구하기
        maxWidth = (long) (right - left + 1) * (long) arr[minIndex];

        // minIndex 기준 왼쪽 막대가 남아있다면
        // 그 중 높이가 최소인 막대를 찾아 직사각형의 넓이를 구함
        if (left <= minIndex - 1) {
            tmpWidth = getMaxWidth(left, minIndex - 1);
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        // minIndex 기준 오른쪽에 막대가 남아있다면
        // 그 중 높이가 최소인 막대를 찾아 직사각형의 넓이를 구함
        if (minIndex + 1 <= right) {
            tmpWidth = getMaxWidth(minIndex + 1, right);
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        return maxWidth;
    }

}