import java.util.ArrayList;
import java.util.HashMap;

public class Compression {
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        String msg = "ABABABABABABABAB";
        Compression c = new Compression();
        int[] ans = c.solution(msg);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();

    }

    public int[] solution(String msg) {
        mapInit();

        int index = 27;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            String s = String.valueOf(msg.charAt(i));
            int j = i;
            while (map.containsKey(s)) {
                if (++j >= msg.length()) {
                    break;
                }
                s += String.valueOf(msg.charAt(j));
            }
            i = j - 1;

            if (j != msg.length()) {
                map.put(s, index++);
                list.add(map.get(s.substring(0, s.length() - 1)));
            } else {
                list.add(map.get(s));
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    static void mapInit() {
        for (int i = 0; i < 27; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }
    }
}
