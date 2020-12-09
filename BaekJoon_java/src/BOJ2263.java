import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2263: 트리의 순회
 *  URL: https://www.acmicpc.net/problem/2263
 *  Description: 중위순회와 후위순회가 주어졌을 때 전위순회를 구하는 문제
 *  Hint: 중위순회, 후위순회 와 전위순회가 어떠한 규칙이 있는지를 찾아야 함.(몰랐음)
 *
 * # 1) postOrder의 마지막 원소가 트리의 루트라는 특징을 이용하여 inOrder(좌우로 나뉜다는 특징)에서
 *      1. 첫번째 인덱스  ~  해당 인덱스-1  //// 2. 해당 인덱스+1  ~ 마지막 인덱스 로 분할 한다.
 *
 * # 2) postOrder 를 다음과 같이 분할
 *      1. 후위 배열의 첫번째 인덱스 ~ 찾은인덱스 - 중위순회에서의 첫 인덱스 - 1
 *      2. 후위 배열의 첫번째 인덱스 + 찾은인덱스 - 중위순회에서의 첫 인덱스 ~ 마지막 인덱스 -1 로 나누어준다.
 */

public class BOJ2263 {
    static int[] inOrder, postOrder, inOrderIdx;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        inOrder = new int[n + 1];
        postOrder = new int[n + 1];
        inOrderIdx = new int[n + 1];    // 중위 순회에 루트들의 인덱스 정보를 입력

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
        // 중위순회에 노드들이 루트일경우 인덱스 정보를 저장
        for (int i = 1; i <= n; i++) {
            inOrderIdx[inOrder[i]] = i;
        }

        getPreOrder(1, n, 1, n);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void getPreOrder(int inO_start, int inO_end, int poO_start, int poO_end) {
        // 중위, 후위 준회의 시작점은 종료점 보다 크면 안됨
        if(inO_start > inO_end || poO_start > poO_end) return;

        // 루트를 구함(후위 순회의 마지막 인덱스 poO_end 가 루트의 인덱스)
        int root = postOrder[poO_end];
        sb.append(root + " ");

        // 중위 순회에서 루트의 인덱스 얻기
        int rootIdx = inOrderIdx[root];
        // 중위 순회에서 루트 기준 왼쪽에 몇개가 있는지 계산
        int left = rootIdx - inO_start;

        // 좌측 자식 노드들을 구하기
        getPreOrder(inO_start, rootIdx-1, poO_start, poO_start+ left-1);

        // 우측 자식 노드들을 구하기
        getPreOrder(rootIdx+1, inO_end, poO_start + left, poO_end - 1);
    }
}
