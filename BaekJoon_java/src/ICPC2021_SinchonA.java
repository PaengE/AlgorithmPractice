import java.io.*;
import java.util.*;

public class ICPC2021_SinchonA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s.length() < m) {
                continue;
            }
            map.computeIfPresent(s, (k, v) -> v + 1);
            map.computeIfAbsent(s, k -> 1);
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, ((o1, o2) -> {
            if (map.get(o1) == map.get(o2)) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o2.length(), o1.length());
            }
            return Integer.compare(map.get(o2), map.get(o1));
        }));

        for (String s : list) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
