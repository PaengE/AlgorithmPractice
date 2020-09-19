import java.io.*;
import java.util.Stack;
/*
    no.10828 : 스택
    hint : Stack<> 자료형 활용
 */

public class BOJ10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            switch (s[0]){
                case "push":
                    stack.push(Integer.parseInt(s[1]));
                    break;
                case "pop":
                    if (stack.empty()){
                        bw.write("-1\n");
                    } else {
                        bw.write(stack.pop() + "\n");
                    }
                    break;
                case "size":
                    bw.write(stack.size() + "\n");
                    break;
                case "empty":
                    if(stack.empty()){
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
                case "top":
                    if (stack.empty()){
                        bw.write("-1\n");
                    } else {
                        bw.write(stack.peek() + "\n");
                    }
                    break;

            }
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
