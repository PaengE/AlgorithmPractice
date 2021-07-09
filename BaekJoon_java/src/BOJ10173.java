import java.io.*;

public class BOJ10173 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;

        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine()).equals("EOI")) {
            if (s.toLowerCase().matches(".*(nemo).*")) {
                sb.append("Found\n");
            } else {
                sb.append("Missing\n");
            }
        }
        bw.write(sb.substring(0, sb.length() - 1));
        bw.close();
        br.close();
    }
}
