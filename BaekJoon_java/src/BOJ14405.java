import java.io.*;

public class BOJ14405 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();

        bw.write(str.matches("(pi|ka|chu)*") ? "YES" : "NO");
        bw.close();
        br.close();
    }
}