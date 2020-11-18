import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * No.14003: 가장 긴 증가하는 부분 수열 5
 * Description: O(NlogN) LIS 를 출력하는 문제
 * URL: https://www.acmicpc.net/problem/14003
 * Hint: 이분탐색을 이용하면 O(NlogN)
 *       DP 를 이용하면 O(N^2)
 */

public class BOJ14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int val = 0;
        int[] preIndex = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 이분 탐색을 이용한 LIS 구하기
        for (int i = 0; i < n; i++) {
            val = Integer.parseInt(st.nextToken());
            num[i] = val;

            if (list.get(list.size() - 1) < val) {
                list.add(val);
                preIndex[i] = list.size() - 1;
            } else {
                int left = 1;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) < val){
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                list.set(right, val);
                preIndex[i] = right;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size() - 1 + "\n");

        int index = list.size() - 1;
        Stack<Integer> stack = new Stack<>();

        for(int i = n - 1; i >= 0; i--){
            // 찾길 원하는 인덱스와 같은 경우
            if(preIndex[i] == index){
                // 찾길 원하는 인덱스를 하나 감소시킨다.
                // 다음 인덱스의 값을 찾기 위해서
                index--;
                // stack에 경로를 추가한다.
                stack.push(num[i]);
            }
        }

        // 스택에서 꺼내며 찾는다.
        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
