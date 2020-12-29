import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Hotter {
    public static void main(String[] args) {
        int[] s = {0,0,0,0};
        int k = 7;

        Hotter h = new Hotter();
        System.out.println(h.solution(s, k));
    }

    public int solution(int[] scoville, int K) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(Arrays.stream(scoville).boxed().toArray(Integer[]::new)));
        PriorityQueue<Integer> pq = new PriorityQueue<>(list);

//        pq.forEach(item -> System.out.println(item));
        int answer = 0;

        while (pq.peek() < K) {
            // 우선순위 큐 앞 두개를 조합하여 다시 큐에 삽입
            if (pq.size() >= 2) {
                pq.offer(pq.poll() + pq.poll() * 2);
                answer += 1;
            }
            // 큐 안의 요소가 2개 미만이면 더이상 조합할 수 없으므로 -1
            else {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
