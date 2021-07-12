import java.io.*;

public class BOJ14906 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder("SLURPYS OUTPUT\n");
        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            int lastIdxOfC = s.lastIndexOf('C');
            if (lastIdxOfC != -1) { // 스림프가 3글자 이상
                if (isSlimp(s.substring(0, lastIdxOfC + 1)) && isSlump(s.substring(lastIdxOfC + 1))) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }

            } else {    // 스림프가 2글자
                if (s.startsWith("AH") && isSlump(s.substring(2))) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        sb.append("END OF OUTPUT");
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    static boolean isSlump(String s) {
        return s.matches("^([DE]F+)+G$");
    }

    static boolean isSlimp(String s) {
        if (s.length() < 2) {
            return false;
        }

        if (s.length() == 2 && s.equals("AH")) {
            return true;
        }

        if (s.charAt(0) == 'A' && s.charAt(s.length() - 1) == 'C') {
            if (s.charAt(1) == 'B') {
                return isSlimp(s.substring(2, s.length() - 1));
            } else {
                return isSlump(s.substring(1, s.length() - 1));
            }
        }

        return false;
    }
}
