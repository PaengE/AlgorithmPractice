import java.io.*;
import java.util.*;

/**
 * no.1655 : 가운데를 말해요
 * title : 우선순위 큐를 응용하여 중앙값을 빠르게 찾는 문제
 * hint : PriorityQueue 를 두개 사용
 */

public class BOJ1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // MaxHeap, MinHeap 두 개의 우선순위 큐를 생성
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());

        int val = 0;
        for (int i = 0; i < n; i++) {
            val = Integer.parseInt(br.readLine());

            // maxHeap 의 크기가 minHeap 의 크기보다 작거나 같으면 maxHeap 에 삽입
            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.offer(val);
            } else {    // 그렇지 않으면 minHeap 에 삽입
                minHeap.offer(val);
            }

            // maxHeap 의 가장 큰 숫자가 minHeap 의 가장 작은 숫자 보다 크면 swap
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(maxHeap.poll());
            }

            bw.write(maxHeap.peek() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
