import java.math.BigInteger;

public class IntactSquare {
    public static void main(String[] args) {
        int w = 8;
        int h = 12;

        System.out.println(solution(w, h));
        System.out.println(mysolution(w, h));
    }

    // BigInteger 를 이용한 gcd method 사용
    static long solution(int w, int h) {
        int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();
        return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
    }

    // gcd를 직접 구현하여 사용
    static long mysolution(int w, int h) {
        long gcd = gcd(w, h);
        return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
    }

    static long gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
