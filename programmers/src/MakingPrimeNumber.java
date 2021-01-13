import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakingPrimeNumber {
//    static int count = 0;
    static boolean[] prime = new boolean[3000]; // false 인 것이 소수
    public static void main(String[] args) {
        MakingPrimeNumber mpn = new MakingPrimeNumber();

        int[] nums = {1,2,7,6,4};

//        System.out.println(mpn.mysolution(nums));
        System.out.println(mpn.solution(nums));
    }

    public int solution(int[] nums) {
        int count = 0;

        prime[0] = true;
        prime[1] = true;
        for(int i = 0; i < prime.length; i++) {
            if (prime[i]) {
                continue;
            }
            for (int j = i + i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (!prime[sum]) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }
}


// 시간 초과 (접근법은 맞는거같음...)
//    public int mysolution(int[] nums) {
//        List<Integer> t = Arrays.asList(Arrays.stream(nums).boxed().toArray(Integer[]::new));
//        ArrayList<Integer> arr = new ArrayList<>(t);
//        ArrayList<Integer> res = new ArrayList<>();
//
//        prime[0] = true;
//        prime[1] = true;
//        for(int i = 0; i < prime.length; i++) {
//            if (prime[i]) {
//                continue;
//            }
//            for (int j = i + i; j < prime.length; j += i) {
//                prime[j] = true;
//            }
//        }
//
//        makeNums(arr, res, 0, nums.length, 3);
//
//        return count;
//    }
//
//    static void makeNums(ArrayList<Integer> arr, ArrayList<Integer> res, int index, int n, int r) {
//        if (r == 0) {
//            int sum = 0;
//            for (int a : res) {
//                sum += a;
//                System.out.print(a +" ");
//            }
//            System.out.println("sum = " + sum);
//
//            if (!prime[sum]) {
//                count += 1;
//            }
//        }
//
//        for (int i = index; i < n; i++) {
//            res.add(arr.get(i));
//            makeNums(arr, res, i + 1, n, r - 1);
//            res.remove(res.size() - 1);
//        }
//    }