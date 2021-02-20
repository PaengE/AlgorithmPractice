import java.io.*;

public class BOJ15829 {
    public static void main(String[] args) throws IOException {
        final int MOD = 1234567891;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        long answer = 0;
        long R = 1;
        for (int i = 0; i < s.length(); i++) {
            answer = (answer + ((int) (s.charAt(i) - 'a') + 1) * R) % MOD;
            R = (R * 31) % MOD;
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
