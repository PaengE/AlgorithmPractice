import java.io.*;

public class BOJ2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();

        if (s.matches("^(100+1+|01)+$")) {
            bw.write("SUBMARINE");
        } else {
            bw.write("NOISE");
        }
        bw.close();
        br.close();
    }
}
