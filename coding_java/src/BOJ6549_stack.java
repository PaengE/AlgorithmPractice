import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

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
            stackHistogram.run();


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

    public StackHistogram(int[] arr) {
        n = arr.length;
        stack = new Stack<>();
        this.arr = arr.clone();
    }

    public void run(){
        for (int i = 0; i < n - 1; i++) {
//            if (stack.isEmpty() || )
        }
    }
}
