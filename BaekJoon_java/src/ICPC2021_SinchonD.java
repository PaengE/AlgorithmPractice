import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 틀림
public class ICPC2021_SinchonD {
    static Deque<Integer>[] cards = new Deque[2];
    static Deque<Integer>[] grounds = new Deque[2];
    static BufferedWriter bw;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        // 입력, 0: do, 1: su
        for (int i = 0; i < 2; i++) {
            cards[i] = new ArrayDeque<>();
            grounds[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cards[0].offerFirst(Integer.parseInt(st.nextToken()));
            cards[1].offerFirst(Integer.parseInt(st.nextToken()));
        }

        int t = openCard(1);

        if (t != -1) {
            if (cards[0].size() > cards[1].size()) {
                bw.write("do");
            } else if (cards[0].size() < cards[1].size()) {
                bw.write("su");
            } else {
                bw.write("dosu");
            }
        }

        bw.close();
        br.close();
    }

    // 다음 게임 처음 순서를 리턴, 0: do, 1: su, -1: 게임 종료 플래그. 2: m번 모두 놓고 정상종료
    static int openCard(int first) throws IOException {
        int next = (first + 1) % 2; // 다음순서

        // 카드를 m번 놓음
        while (m-- > 0) {
            // 하나를 깜
            grounds[next].offerFirst(cards[next].pollFirst());
            // 깐 후 자신의 카드가 없으면
            if (cards[next].isEmpty()) {
                if (next == 0) {
                    bw.write("su");
                } else if (next == 1) {
                    bw.write("do");
                }
                return -1;
            }

            // 그라운드의 두 카드 중 하나라도 5이면 su 승리
            if (cards[0].peekFirst() == 5 || cards[1].peekFirst() == 5) {
                AfterWin(0);
                return openCard(next);
            }

            // 두 그라운드 모두 카드가 있고 두개의 합이 5면 do 승리
            if (!grounds[0].isEmpty() && !grounds[1].isEmpty()
                    && (grounds[0].peekFirst() + grounds[1].peekFirst()) == 5) {
                AfterWin(1);
                return openCard(next);
            }

            // 다음 순서 지정
            next = (next + 1) % 2;
        }

        return 2;
    }

    // 이긴 사람의 카드 밑에 추가, 0: do, 1: su
    static void AfterWin(int winner) {
        int opponent = (winner + 1) % 2;

        while (!grounds[opponent].isEmpty()) {
            cards[winner].offerLast(grounds[opponent].pollLast());
        }
        while (!grounds[winner].isEmpty()) {
            cards[winner].offerLast(grounds[winner].pollLast());
        }
    }
}
