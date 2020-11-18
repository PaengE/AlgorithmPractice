import java.io.*;

/**
 * no.1629 : 곱셈
 * hint : (A * B) % C = ((A % C) * (B % C)) % C
 */

public class BOJ1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[1]);
        int c = Integer.parseInt(s[2]);

        long ans = 1;
        long t = a % c;
        while (b > 0) {
            if (b % 2 == 1){
                ans *= t;
                ans %= c;
            }
            t = ((t % c) * (t % c)) % c;
            b /= 2;
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
