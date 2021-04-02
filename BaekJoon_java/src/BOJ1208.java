import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *  No.1208: 부분 수열의 합 2
 *  URL: https://www.acmicpc.net/problem/1208
 *  Hint: meet in the middle + 이분탐색 or 투포인터
 */

public class BOJ1208 {
    static int n, s;
    static long ans;
    static int[] arr;
    static ArrayList<Integer> firstList, secondList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeSum(0, n / 2, 0, firstList);
        makeSum(n / 2, n, 0, secondList);
        Collections.sort(firstList);
        Collections.sort(secondList);

        twoPointer(0, secondList.size() - 1);
        bw.write(String.valueOf(ans));
        br.close();
        bw.close();
    }

    // 투포인터를 사용하여 합이 s인 것의 개수를 구함
    static void twoPointer(int left, int right) {
        while (left < firstList.size() && right >= 0) {
            int leftValue = firstList.get(left);
            int rightValue = secondList.get(right);

            if (leftValue + rightValue == s) {
                long leftValueCount = 0;
                long rightValueCount = 0;

                while (left < firstList.size() && leftValue == firstList.get(left)) {
                    leftValueCount++;
                    left++;
                }
                while (right >= 0 && rightValue == secondList.get(right)) {
                    rightValueCount++;
                    right--;
                }

                ans += leftValueCount * rightValueCount;
            } else if (leftValue + rightValue < s) {
                left++;
            } else if (leftValue + rightValue > s) {
                right--;
            }
        }

        if (s == 0) {   // s 가 0인 경우, 공집합인 경우도 ans 에 포함되기 때문에 제외시켜야함.
            ans--;
        }
    }

    // 부분 수열을 모두 구함
    static void makeSum(int idx, int end, int sum, ArrayList<Integer> res) {
        if (idx == end) {
            res.add(sum);
            return;
        }

        makeSum(idx + 1, end, sum + arr[idx], res);
        makeSum(idx + 1, end, sum, res);
    }
}

/* 왜 시간초과가 아닌가..

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());

    int maxSize = 100000 * 40 * 2;
    long[] arr = new long[maxSize + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        int a = Integer.parseInt(st.nextToken());

        if (a < 0) {
            for (int j = -a; j <= maxSize; j++) {
                arr[a + j] += arr[j];
            }
        } else {
            for (int j = maxSize - a; j >= 0 ; j--) {
                arr[a + j] += arr[j];
            }
        }
        arr[a + 4000000]++;
    }

    bw.write(String.valueOf(arr[s + 4000000]));
    bw.close();
    br.close();
}

 */
