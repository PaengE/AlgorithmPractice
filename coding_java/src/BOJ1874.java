import java.io.*;
import java.util.Stack;

/**
 * no.1874 : 스택 수열
 * hint : BufferedWriter를 쓸 경우 버퍼가 어느정도 차면 자동으로 비우기 때문에 출력초과가 남.
 *        StringBuilder를 사용할 것.
 */

public class BOJ1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        Stack<Integer> stk = new Stack<>();

        int j = 1;
        stk.push(0);

        for (int i = 0; i < n; i++) {
            if (arr[i] > stk.peek()){
                for ( ; arr[i] != stk.peek(); j++) {
                    stk.push(j);
                    sb.append("+\n");
                }
                stk.pop();
                sb.append("-\n");
            } else {
                if (arr[i] != stk.pop()){
                    System.out.println("NO");
                    return;
                } else {
                    sb.append("-\n");
                }
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
