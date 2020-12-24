import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingByBridge {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        System.out.println(solution(bridge_length, weight, truck_weights));
    }

    static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int answer = 0;
        int total_weight = 0;

        for (int cur_weight : truck_weights) {
            while (true) {
                if (q.isEmpty()) {
                    q.offer(cur_weight);
                    total_weight += cur_weight;
                    answer += 1;
                    break;
                } else if (q.size() == bridge_length) {
                    total_weight -= q.poll();
                } else {
                    if (total_weight + cur_weight > weight) {
                        answer += 1;
                        // 0을 넣는 이유는 시간 계산을 위해서임.
                        q.offer(0);
                    } else {
                        q.offer(cur_weight);
                        total_weight += cur_weight;
                        answer += 1;
                        break;
                    }
                }
            }
        }

        // 마지막 트럭은 큐에 넣은 후 바로 종료되기 때문에 + bridge_length 를 해줌
        return (answer + bridge_length);
    }
}
