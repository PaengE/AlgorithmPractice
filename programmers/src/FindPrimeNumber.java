import java.util.ArrayList;
import java.util.TreeSet;

public class FindPrimeNumber {
    private TreeSet<String> set = new TreeSet<>();
    private int count = 0;

    public static void main(String[] args) {

        String numbers = "21";

        FindPrimeNumber s = new FindPrimeNumber();
        System.out.println(s.solution(numbers));
    }

    public int solution(String numbers) {
        int size = numbers.length();
        ArrayList<Character> arr = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            arr.add(numbers.charAt(i));
        }

        ArrayList<Character> res = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            makeNum(arr, res, size, i + 1);
        }

        return count;
    }

    /**
     * 소수판별
     *
     * @param number    판별될 숫자
     * @return          소수인지(ture) 아닌지(false)
     */
    private boolean isPrime(int number) {
        // 1이거나 2로 나누어 떨어지면 소수가 아님
        if (number % 2 == 0 || number == 1) {
            return false;
        }

        // 소수 판별은 제곱근까지만 따져보면 됨
        int sqrt = (int) Math.sqrt(number);
        for (int i = 3; i <= sqrt; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }


    /**
     * 순열을 이용하여 숫자 생성
     *
     * @param arr   원본 리스트
     * @param res   만들어질 숫자가 담길 리스트(결과 리스트)
     * @param n     nPr 에서 n
     * @param r     nPr 에서 r
     */
    private void makeNum(ArrayList<Character> arr, ArrayList<Character> res, int n, int r) {

        // r이 0이면 문자열이 완성됐다는 뜻(탈출)
        if (r == 0) {
            if (res.get(0) != '0') {
                // res 는 [1, 2] 형식이므로 str 전처리
                String str = res.toString()
                        .replaceAll("\\[|\\]", "")
                        .replaceAll(", ", "");

                // set 에 없다면(중복된 수가 아니라면)
                if (!set.contains(str)) {
                    int num = Integer.parseInt(str);
                    set.add(str);

                    // 소수 판별
                    if (isPrime(num)) {
                        System.out.println("num = " + num);
                        count += 1;
                    }
                }
            }
            
            return;
        }

        // 순열이므로 기준을 계속 옮겨야 함(뭐를 먼저 고를지)
        for (int i = 0; i < n; i++) {
            // 원본리스트에서 선택한 요소를 결과리스트에 담음
            res.add(arr.remove(i));
            // 결과리스트에 담은 수(1개)를 빼고, 그중에서 (r-1)개를 골라야 하므로 재귀
            makeNum(arr, res, n - 1, r - 1);
            // 재귀가 끝나면 결과리스트에 마지막으로 담았던 걸 다시 원본리스트의 제자리에 넣음
            arr.add(i, res.remove(res.size() - 1));
        }
    }
}
