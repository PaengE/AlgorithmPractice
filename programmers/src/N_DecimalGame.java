public class N_DecimalGame {
    public static void main(String[] args) {
        N_DecimalGame ndg = new N_DecimalGame();
        System.out.println(ndg.solution(2, 4, 2, 1));
        System.out.println(ndg.solution(16, 16, 2, 1));
        System.out.println(ndg.solution(16, 16, 2, 2));
    }

    public String solution(int n, int t, int m, int p) {
        String s = "0";
        int count = 0;

        while (s.length() <= t * m + p) {
            String nthNum = "";
            int nth = count++;

            // 10진수를 n진수로 바꾸는 과정
            while (nth != 0) {
                // 10 이상은 A B C D E F
                if (nth % n >= 10) {
                    nthNum += String.valueOf((char)('A' + (nth % n) - 10));
                } else {
                    nthNum += String.valueOf(nth % n);
                }
                nth /= n;
            }
            s += new StringBuffer(nthNum).reverse().toString();
        }

        String answer = "";
        for (int i = 0; i < t; i++) {
            answer += String.valueOf(s.charAt(m * i + p - 1));
        }
        return answer;
    }
}
