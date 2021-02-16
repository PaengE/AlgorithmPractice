import java.io.*;

public class BOJ3344 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        boolean isOdd = false;
        StringBuilder sb = new StringBuilder();

        if (n % 2 != 0) {
            isOdd = true;
        }

        if ((!isOdd && n % 6 != 2) || (isOdd && (n - 1) % 6 != 2)) {
            if (isOdd) {
                n--;
            }
            for (int i = 1; i <= n / 2; i++) {
                sb.append(2 * i).append("\n");
            }
            for (int i = 1; i <= n / 2; i++) {
                sb.append(2 * i - 1).append("\n");
            }
            if (isOdd) {
                sb.append(n + 1).append("\n");
            }

        } else if ((!isOdd && n / 6 != 0) || (isOdd && (n - 1) / 6 != 2)) {
            if (isOdd) {
                n--;
            }
            for (int i = 1; i <= n / 2; i++) {
                sb.append(1 + (2 * i + n / 2 - 3) % n).append("\n");
            }
            for (int i = n / 2; i > 0; i--) {
                sb.append(n - (2 * i + n / 2 - 3) % n).append("\n");
            }
            if (isOdd) {
                sb.append(n + 1).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
