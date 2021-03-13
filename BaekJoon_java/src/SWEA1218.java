import java.io.*;
import java.util.Stack;

/**
 *  Difficulty: D4
 *  URL: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD&categoryId=AV14eWb6AAkCFAYD&categoryType=CODE&problemTitle=1218&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

public class SWEA1218 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();

            int validation = 1;
            Stack<Character> stk = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '{' || c == '[' || c == '<' || c == '(') {
                    stk.push(c);
                } else if (c == '}' && stk.peek() == '{') {
                    stk.pop();
                } else if (c == ']' && stk.peek() == '[') {
                    stk.pop();
                } else if (c == '>' && stk.peek() == '<') {
                    stk.pop();
                } else if (c == ')' && stk.peek() == '(') {
                    stk.pop();
                } else {
                    validation = 0;
                    break;
                }
            }

            sb.append("#" + tc + " " + validation + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
