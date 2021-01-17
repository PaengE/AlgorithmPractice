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
            // 현재 s가 hashmap에 존재한다면 계속하여 문자열을 붙여서 구성해나감
            while (map.containsKey(s)) {
                if (++j >= msg.length()) {
                    break;
                }
                s += String.valueOf(msg.charAt(j));
            }
            // 다음 시작은 hashmap에 없는 문자부터 시작해야하므로
            i = j - 1;

            // 마지막 문자까지 않았을 경우
            if (j != msg.length()) {
                // hashmap에 put한 후, 출력 리스트에 저장함
                map.put(s, index++);
                list.add(map.get(s.substring(0, s.length() - 1)));
            }
            // 마지막 문자까지 갔을 경우
            else {
                // 출력리스트에 저장함
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
