import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *  no.17298: 오큰수
 *  url: https://www.acmicpc.net/problem/17298
 *  description: 스택으로 풀 수 있는 꽤 어려운 문제
 *  hint: 스택의 top 과 입력배열의 top 의 오른쪽 부분을 비교하여 풀어야 함.
 */

public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];     // 입력 배열
        int[] answer = new int[n];  // 정답 배열
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();   // 스택엔 인덱스를 삽입함

        // 초기 인덱스 삽입
        stack.push(0);
        for (int i = 1; i < n; i++) {
            // 스택의 top 에 있는 인덱스의 값이 i번째 인덱스의 값보다 작으면 정답배열에 저장
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                answer[stack.pop()] = arr[i];
            }

            // 다음번에 비교할 숫자 삽입
            stack.push(i);
        }

        // 스택에 남아있는 인덱스들은 오큰수가 없는 것
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        for (int nge : answer) {
            sb.append(nge + " ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}


/* 시간초과 코드(O^2)

for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (arr[i] < arr[j]) {
            sb.append(arr[j] + " ");
            break;
        }

        if (j == n - 1) {
            sb.append("-1 ");
        }
    }
}
sb.append("-1 ");
 */