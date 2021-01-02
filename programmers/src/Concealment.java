import java.util.HashMap;

public class Concealment {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}
                , {"blue_sunglasses", "headgear"}
                , {"green_turban", "ey"}};

        Concealment c = new Concealment();
        System.out.println(c.solution(clothes));
    }

    // (모든 종류별 옷의 개수 + 1)을 곱한 값에 -1을 한 것이 답임.
    // n개 옷 중 하나를 고를 경우의 수: n
    // n개 옷 중 아무것도 안입는 경우의 수: 1
    // 모든 종류의 옷을 하나도 입지 않는 경우의 수: 1
    public int solution(String[][] clothes) {
        var map = new HashMap<String, Integer>();
        int[] cloth = new int[30];
        Integer idx = 0;

        for (String[] s : clothes) {
            Integer index = map.putIfAbsent(s[1], idx);
            if (index == null) {
                cloth[idx] = 1;
                idx += 1;
            } else {
                cloth[index] += 1;
            }
        }

        int answer = 1;
        for (int i = 0; i < idx; i++) {
            answer *= (cloth[i] + 1);
        }
        return answer - 1;
    }
}
