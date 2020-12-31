/**
 *
 *
 *
 *
 *
 *
 *  불완전한 풀이(테스트케이스 부족으로 통과)
 *
 *
 *
 *
 *
 */

public class Joystick {
    public static void main(String[] args) {
        String name = "BABAAAAABAB";

        Joystick q = new Joystick();

        System.out.println(q.solution(name));

        // 테스트케이스 부족함. (정답이 아닌 것이 정답처리됨)
    }

    public int solution(String name) {
        int size = name.length();

        // 비교할 문자열인 "A...A"를 만듦
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("A");
        }

        int count = 0;
        int cur = 0;

        while (true) {
            char c = name.charAt(cur);

            // 문자를 'A' 에서 바꿀 때의 비용
            if (c != 'A') {
                count += Math.min((int) 'Z' - (int) c + 1, c - (int) 'A');
            }

            // 문자를 바꾼 후, 두 문자열이 같아지면 loop 종료
            sb.setCharAt(cur, c);
            if (sb.toString().equals(name)) {
                break;
            }

            // 현재위치 기준 어느쪽으로 가는게 이득인지를 계산(거리가 같다면 오른쪽 우선)
            for (int move = 1; move < size; move++) {
                // 오른쪽으로 가는 걸 먼저 계산
                if (sb.charAt((cur + move) % size) != name.charAt((cur + move) % size)) {
                    cur = (cur + move) % size;
                    count += move;
                    break;
                }
                // 그다음 왼쪽 계산
                else if (sb.charAt((cur + size - move) % size) != name.charAt((cur + size - move) % size)) {
                    cur = (cur + size - move) % size;
                    count += move;
                    break;
                }
            }
        }

        return count;
    }
}
