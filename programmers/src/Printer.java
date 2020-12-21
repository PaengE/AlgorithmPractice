import java.util.*;

public class Printer {
    public static void main(String[] args) {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;

        System.out.println(solution(priorities, location));
    }

    static int solution(int[] priorities, int location) {
        Queue<Docs> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new Docs(i, priorities[i]));
        }

        int count = 1;
        while (!q.isEmpty()) {
            Docs front = q.poll();
            boolean check = true;

            Iterator<Docs> iter = q.iterator();
            while (iter.hasNext()) {
                if (front.priority < iter.next().priority) {
                    check = false;
                    break;
                }
            }
            if (!check) {
                q.offer(front);
            } else {
                if (front.docsNum == location) {
                    break;
                } else {
                    count += 1;
                }
            }
        }
        return count;
    }
    static class Docs{
        int docsNum;
        int priority;
        public Docs(int docsNum, int priority){
            this.docsNum = docsNum;
            this.priority = priority;
        }
    }
}
