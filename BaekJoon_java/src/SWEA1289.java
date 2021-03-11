import java.io.*;

public class SWEA1289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            String num = br.readLine();
            int length = num.length();

            char pre = '0';
            int cnt = 0;
            for (int j = 0; j < length; j++) {
                if (pre != num.charAt(j)) {
                    cnt++;
                    pre = num.charAt(j);
                }
            }

            sb.append("#" + i + " " + cnt + " \n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
