import java.io.*;
import java.util.Stack;
/*
    no.10773 : 제로
    hint : Stack<> 클래스 활용
 */

public class BOJ10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            switch(num){
                case 0:
                    stack.pop();
                    break;
                default:
                    stack.push(num);
                    break;
            }
        }
        int sum = 0;
        int stack_size = stack.size();
        for (int i = 0; i < stack_size; i++) {
            sum += stack.pop();
        }
        bw.write(sum + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
