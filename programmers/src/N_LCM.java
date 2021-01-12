public class N_LCM {
    public static void main(String[] args) {
        N_LCM lcm = new N_LCM();
        int[] arr = {2, 6, 8, 14};

        System.out.println(lcm.solution(arr));
    }

    public int solution(int[] arr) {
        int size = arr.length;
        if (size == 1) {
            return arr[0];
        } else {
            int lcm = (arr[0] * arr[1]) / gcd(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                lcm = (lcm * arr[i]) / gcd(lcm, arr[i]);
            }
            return lcm;
        }
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
