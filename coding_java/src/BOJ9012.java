import java.io.*;
import java.util.Stack;
/*
    no.9012 : 괄호
    hint : Stack<> 활용
 */

public class BOJ9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split("");
            Stack<String> stk = new Stack<String>();
            boolean vps = true;

            for (int j = 0; j < s.length; j++) {
                switch (s[j]){
                    case "(":
                        stk.push("(");
                        break;
                    case ")":
                        if(stk.empty()){
                            vps = false;
                        } else {
                            stk.pop();
                        }
                        break;
                }
                if(!vps)
                    break;
            }
            if(!stk.empty())
                vps = false;

            if(vps)
                bw.write("YES\n");
            else
                bw.write("NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
