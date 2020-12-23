import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class StockPrice {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};

        int[] answer = solution(prices);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }

    static int[] solution(int[] prices) {
        int size = prices.length;
        int[] answer = new int[size];

        Queue<Integer> q = new LinkedList<>();
        for (int num : prices) {
            q.offer(num);
        }

        for (int i = 0; i < size; i++) {
            int price = q.poll();
            Iterator it = q.iterator();

            int count = 0;
            while (it.hasNext()) {
                if (price <= (int) it.next()) {
                    count += 1;
                } else {
                    count += 1;
                    break;
                }
            }

            answer[i] = count;
        }
        return answer;
    }
}
