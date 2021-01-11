public class BinaryConversion {
    public static void main(String[] args) {
        String s = "1111111";
        BinaryConversion bc = new BinaryConversion();
        bc.solution(s);
    }

    public int[] solution(String s) {
        int count = 0;
        int deleted_zero = 0;

        while (!s.equals("1")) {
            count++;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    deleted_zero += 1;
                }
            }

            s = s.replaceAll("0", "");
            if (s.equals("1")) {
                break;
            }

            int length = s.length();
            s = Integer.toBinaryString(length);
        }

        System.out.println("count = " + count);
        System.out.println("deleted_zero = " + deleted_zero);
        return (new int[]{count, deleted_zero});
    }
}
