import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ2870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Pattern p = Pattern.compile("\\d+");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            Matcher m = p.matcher(str);

            while (m.find()) {
                String s = m.group().replaceAll("^0+", "");
                if (s.length() == 0) {
                    list.add("0");
                } else {
                    list.add(s);
                }
            }
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
