public class MaxValueAndMinValue {
    public static void main(String[] args) {
        String s = "55 55 5 558";
        MaxValueAndMinValue mm = new MaxValueAndMinValue();
        System.out.println("mm.solution(s) = " + mm.solution(s));
    }

    public String solution(String s) {
        String[] str = s.split(" ");
        int min, max;
        min = max = Integer.parseInt(str[0]);
        for (int i = 1; i < str.length; i++) {
            int num = Integer.parseInt(str[i]);

            if (num < min) {
                min = num;
            }
            if (max < num) {
                max = num;
            }
        }

        return min + " " + max;
    }
}
