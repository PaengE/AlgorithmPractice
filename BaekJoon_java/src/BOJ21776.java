import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21776 {
    static int n, c;
    static ArrayList<Integer>[] order;
    static String[] cards;
    static int[] personalCards, res, commands;
    static TreeSet<String> ans = new TreeSet<>();   // 정답 문자열의 중복을 제거 + 아스키코드 기준 사전 순
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        order = new ArrayList[n + 1];   // i번째 사람의 카드 연산 순서
        cards = new String[c + 1];  // 카드에 적혀 있는 연산
        personalCards = new int[n + 1]; // i번째 사람이 가지고 있는 카드 개수
        res = new int[c + 1];   // 사용자 순열
        commands = new int[c];  // 사용자가 사용한 카드 순열
        Arrays.fill(res, -1);

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            personalCards[i] = cnt;

            order[i] = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                order[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 1; i <= c; i++) {
            cards[i] = br.readLine();   // i번째 카드
        }

        samePermutation(1,0);

        bw.write(printAnswer());
        bw.close();
        br.close();
        
    }

    static String printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (String s : ans) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    // 카드번호 순열로 문자열 만드는 메소드
    static void operation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            int cur = commands[i];

            String[] ops = cards[cur].split(",");
            for (int j = 0; j < ops.length; j++) {
                StringTokenizer token = new StringTokenizer(ops[j]);
                if (token.nextToken().equals("ADD")) {  // 문자열에 문자 추가
                    sb.append(token.nextToken());
                } else {    // Delete 가능하면 삭제, 불가능하면 Exception
                    try {
                        sb.deleteCharAt(Integer.parseInt(token.nextToken()));
                    } catch (Exception e) {
                        ans.add("ERROR");
                        return;
                    }
                }
            }
        }

        // 문자열이 비어있으면 EMPTY
        if (sb.length() == 0) {
            ans.add("EMPTY");
        } else {
            ans.add(sb.toString());
        }
    }

    static void samePermutation(int personIdx, int cardCnt) {
        if (personIdx == n + 1) {
            int[] cardIdx = new int[n + 1];

            // 사람 순열로 카드번호 순열 만들기
            for (int i = 0; i < c; i++) {
                int person = res[i];
                commands[i] = order[person].get(cardIdx[person]++);
            }

            operation();
            return;
        }

        // 현재 사람의 카드를 다 썼다면 다음 사람으로 넘어감
        if (personalCards[personIdx] == 0) {
            samePermutation(personIdx + 1, 0);
        } else {    // 현재 사람의 카드로 순열 만들기
            personalCards[personIdx]--;
            for (int i = cardCnt; i < c; i++) {
                if (res[i] < 0) {
                    res[i] = personIdx;
                    samePermutation(personIdx, i + 1);
                    res[i] = -1;
                }
            }
            personalCards[personIdx]++;
        }
    }
}
