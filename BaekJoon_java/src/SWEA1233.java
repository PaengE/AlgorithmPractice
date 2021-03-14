import java.io.*;

/**
 *  Difficulty: D4
 *  URL: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD&categoryId=AV141176AIwCFAYD&categoryType=CODE&problemTitle=1233&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&
 */

public class SWEA1233 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());

            boolean flag = true;
            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");

                if (s[1].equals("+") || s[1].equals("-") || s[1].equals("*") || s[1].equals("/")) {
                    if (s.length != 4) {
                        flag = false;
                        continue;
                    }
                } else {
                    if (s.length != 2) {
                        flag = false;
                        continue;
                    }
                }
            }
            if (flag) {
                sb.append("#" + tc + " 1\n");
            } else {
                sb.append("#" + tc + " 0\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
