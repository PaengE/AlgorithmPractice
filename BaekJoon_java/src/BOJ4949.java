import java.io.*;
import java.util.Stack;

/**
 * no.4949 : 균형잡힌 세상
 * hint : Stack<E> 사용
 */

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        while(s.charAt(0) != '.'){
            String str = s.replaceAll("[^\\[\\]\\(\\)\\.]", "");
            Stack<Character> stk = new Stack<>();
            boolean check = true;

            for (int i = 0; i < str.length(); i++) {
                switch (str.charAt(i)){
                    case '(':
                    case '[':
                        stk.push(str.charAt(i));
                        break;
                    case ')':
                        if(stk.empty()){
                            check = false;
                        } else {
                            if (stk.pop() != '('){
                                check = false;
                            }
                        }
                        break;
                    case ']':
                        if(stk.empty()){
                            check = false;
                        } else {
                            if (stk.pop() != '['){
                                check = false;
                            }
                        }
                        break;
                }
                if (!check)
                    break;
            }
            if(check && stk.empty())
                bw.write("yes\n");
            else
                bw.write("no\n");
            s = br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
