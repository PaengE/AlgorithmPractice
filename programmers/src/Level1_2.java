public class Level1_2 {
    public static void main(String[] args) {
        int n = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n / 2; i++) {
            sb.append("수박");
        }

        if (n % 2 == 1) {
            sb.append("수");
        }

        String s = sb.toString();

        System.out.println("s = " + s);
    }
}
