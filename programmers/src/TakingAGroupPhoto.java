import java.util.ArrayList;
import java.util.Arrays;

public class TakingAGroupPhoto {
    static int count;
    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
//        String[] data = {"M~C<2", "C~M>1"};
        TakingAGroupPhoto tagp = new TakingAGroupPhoto();
        tagp.solution(n, data);

        System.out.println("count = " + count);
    }

    public int solution(int n, String[] data) {
        Character[] s = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        count = 0;

        ArrayList<Character> arr = new ArrayList<>(Arrays.asList(s));
        ArrayList<Character> res = new ArrayList<>();

        makeString(arr, res, 8, 8, data);

        return count;
    }

    static void makeString(ArrayList<Character> arr, ArrayList<Character> res, int n, int r, String[] data) {
        if (r == 0) {
            int size = data.length;

            for (int i = 0; i < size; i++) {
                char c1 = data[i].charAt(0);
                char c2 = data[i].charAt(2);
                char op = data[i].charAt(3);
                int num = data[i].charAt(4) - '0';

                int diff = Math.abs(res.indexOf(c1) - res.indexOf(c2)) - 1;
                if (op == '<') {
                    if (diff >= num) {
                        return;
                    }
                } else if (op == '>'){
                    if (diff <= num) {
                        return;
                    }
                } else if (op == '='){
                    if (diff != num) {
                        return;
                    }
                }
            }
            count += 1;
            return;
        }
        for (int i = 0; i < n; i++) {
            res.add(arr.remove(i));
            makeString(arr, res, n - 1, r - 1, data);
            arr.add(i, res.remove(res.size() - 1));
        }
    }
}
