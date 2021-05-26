import java.io.*;
import java.util.*;

public class BOJ21775 {
    static int n, t;
    static int[] sequence;
    static Queue<OperationCard> queue;
    static OperationCard[] people;
    static HashMap<Integer, Integer> personalSpace = new HashMap<>();   // (k, v) = (num, owner)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        sequence = new int[t];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < t; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        queue = new ArrayDeque<>();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            if (!command.equals("next")) {
                int num = Integer.parseInt(st.nextToken());
                queue.offer(new OperationCard(idx, command, num));
            } else {
                queue.offer(new OperationCard(idx, command));
            }
        }

        people = new OperationCard[n + 1];

        bw.write(gameStart());
        bw.close();
        br.close();
    }

    static String gameStart() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int curPeople = sequence[i];

            if (people[curPeople] == null) {    // 해당 턴의 사람이 가지고 있는 연산카드가 없으면
                people[curPeople] = queue.poll();
                sb.append(people[curPeople].idx + "\n");
                if (operation(people[curPeople], curPeople)) {
                    people[curPeople] = null;
                }
            } else {    // 연산카드를 가지고 있으면(acquire 연산카드임)
                sb.append(people[curPeople].idx + "\n");
                if (!personalSpace.containsKey(people[curPeople].num)) {
                    personalSpace.put(people[curPeople].num, curPeople);
                    people[curPeople] = null;
                }
            }
        }

        return sb.toString();
    }

    // 리턴이 true 면 버림, fals 면 버리지 않음
    static boolean operation(OperationCard card, int personId) {
        if (card.command.equals("acquire")) {
            if (!personalSpace.containsKey(card.num)) { // 공용공간에 카드가 있으면
                personalSpace.put(card.num, personId);
                return true;
            } else {    // 공용공간에 카드가 없으면 카드를 버리지 않고 다음 턴으로
                return false;
            }
        } else if (card.command.equals("release")) {
            if (personalSpace.get(card.num) == personId) {  // personId 개인공간에 카드가 있으면
                personalSpace.remove(card.num); // 공용공간에 반환
            }
            return true;
        } else {    // 커맨드가 next일 경우
            return true;
        }
    }

    static class OperationCard{
        int idx, num;
        String command;

        OperationCard(int idx, String command, int num) {
            this.idx = idx;
            this.command = command;
            this.num = num;
        }

        OperationCard(int idx, String command) {
            this.idx = idx;
            this.command = command;
        }
    }
}
