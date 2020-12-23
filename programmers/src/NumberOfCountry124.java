public class NumberOfCountry124 {
    public static void main(String[] args) {
        int n = 13;

        String s = solution(n);

        System.out.println("s = " + s);
    }

    static String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % 3;
            n /= 3;

            if (remainder == 0) {
                n--;
            }

            if (remainder == 0) {
                sb.insert(0, 4);
            } else if (remainder == 1) {
                sb.insert(0, 1);
            } else {
                sb.insert(0, 2);
            }
        }

        String answer = sb.toString();
        return answer;
    }
}
