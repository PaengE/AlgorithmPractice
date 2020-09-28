import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * no.6549 : 히스토그램에서 가장 큰 직사각형
 * title : 히스토그램에서 가장 큰 직사각형을 찾는 문제.
 * hint : Stack
 */

public class BOJ6549_stack {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = null;
        StackHistogram stackHistogram;

        while(!s.equals("0")){
            st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            stackHistogram = new StackHistogram(arr);

            bw.write(stackHistogram.run() + "\n");

            s = br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class StackHistogram {
    private int n;
    private int[] arr;
    private Stack<Integer> stack;
    private long max;

    // stack 생성 및 초기화
    public StackHistogram(int[] arr) {
        n = arr.length;
        stack = new Stack<>();
        this.arr = arr.clone();
    }

    public long run(){
        // 배열의 0번쨰 인덱스를 스택에 넣음
        stack.push(0);

        // 배열의 1번째 인덱스부터 n-1번째 인덱스까지 스택에 넣음
        for (int i = 1; i < n; i++) {

            // 스택이 비지 않았고, arr[top] 이 arr[i]보다 크면
            if (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int top = 0;
                long left = 0, right = 0, x = 0, sum = 0;

                // 스택이 비거나, arr[top] 이 arr[i]보다 크지 않을 때까지 스택에서 pop 하면서
                // 막대의 left, right, height 를 구해 최대 넓이를 구함
                // right 는 i - 1, left 는 top 바로 밑 스택요소 + 1
                while (arr[top = stack.peek()] > arr[i]) {
                    stack.pop();
                    x = arr[top];

                    right = i - 1;
                    if (!stack.isEmpty())
                        left = stack.peek() + 1;
                    else
                        left = 0;

                    sum = Math.abs(right - left + 1) * x;
                    max = Math.max(sum, max);

                    if (stack.isEmpty())
                        break;
                }

            }
            // i를 push
            stack.push(i);
        }


        // 배열의 인덱스를 모두 넣은 뒤 스택이 비어 있지 않으면
        // 막대의 right 는 n-1, left 는 top 바로 밑 스택요소 + 1로 하여 막대의 최대 넓이를 구함
        while (!stack.isEmpty()) {
            long x = arr[stack.pop()];
            long right = n - 1;
            long left = 0;
            
            if (!stack.isEmpty())
                left = stack.peek() + 1;
            else
                left = 0;

            long sum = (right - left + 1) * x;
            max = Math.max(sum, max);

        }

        return max;
    }
}
