import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ22232 {
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] files = new String[n][2];

        for (int i = 0; i < n; i++) {
            files[i] = br.readLine().split("\\.");
        }

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            map.put(s, 0);
        }

        sort(files);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(files[i][0] + "." + files[i][1] + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    static void sort(String[][] files) {
        Arrays.sort(files, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].equals(o2[0])) {
                    if (map.containsKey(o1[1]) && map.containsKey(o2[1])) {
                        return o1[1].compareTo(o2[1]);
                    }

                    if (!map.containsKey(o1[1]) && !map.containsKey(o2[1])) {
                        return o1[1].compareTo(o2[1]);
                    }

                    return map.getOrDefault(o1[1], 1) - map.getOrDefault(o2[1], 1);
                }

                return o1[0].compareTo(o2[0]);
            }
        });
    }
}
