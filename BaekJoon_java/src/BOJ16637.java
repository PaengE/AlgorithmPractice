import java.io.*;

public class BOJ16637 {
    static int[] nums;
    static char[] ops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        nums = new int[(n / 2) + 1];
        ops = new char[n / 2];

        int numIdx = 0;
        int opIdx = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                ops[opIdx++] = c;
            } else {
                nums[numIdx++] = c - '0';
            }
        }



    }
}
