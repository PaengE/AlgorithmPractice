public class Fibonacci {
    public static void main(String[] args) {
        int n = 5;

        Fibonacci f = new Fibonacci();

        System.out.println(f.solution(n));
    }

    public int solution(int n) {
        int[] fibo = new int[n + 1];
        fibo[0] = 0;
        fibo[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
        }

        return fibo[n];
    }
}
