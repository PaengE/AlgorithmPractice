import java.util.ArrayList;
import java.util.HashSet;

public class CandidateKey {
    static HashSet<ArrayList<Integer>> candidates;
    static int answer;
    public static void main(String[] args) {

//        String[][] relation = {{"100", "ryan", "music", "2"}
//                , {"200", "apeach", "math", "2"}
//                , {"300", "tube", "computer", "3"}
//                , {"400", "con", "computer", "4"}
//                , {"500", "muzi", "music", "3"}
//                , {"600", "apeach", "music", "2"}};
//        String[][] relation = {{"a", "b", "c"}, {"1", "b", "c"}, {"a", "b", "4"}, {"a", "5", "c"}};
        String[][] relation = {{"a", "1", "4"}, {"2", "1", "5"}, {"a", "2", "4"}};

        CandidateKey ck = new CandidateKey();
        System.out.println("answer : " + ck.solution(relation));

        candidates.forEach(list -> System.out.println(list));
    }

    public int solution(String[][] relation) {
        candidates = new HashSet<>();
        answer = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int n = relation[0].length;

        for (int i = 0; i < n; i++) {
            arr.add(i);
        }

        for (int i = 1; i <= n; i++) {
            makeKey(arr, res, 0, n, i, relation);
        }

        return answer;
    }

    static void makeKey(ArrayList<Integer> arr, ArrayList<Integer> res, int idx, int n, int r, String[][] relation) {
        if (r == 0) {
            // 최소성을 검사
            for (ArrayList tmpList : candidates) {
                boolean check = true;
                // 주어진 컬럼 조합이 후보키를 포함하고 있으면 최소성을 충족시키지 못함
                for (int i = 0; i < tmpList.size(); i++) {
                    if (res.indexOf(tmpList.get(i)) == -1) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return;
                }
            }

            // 유일성을 검사(구성된 컬럼으로)
            HashSet<String> tmpSet = new HashSet<>();
            for (int i = 0; i < relation.length; i++) {
                String t = "";
                for (int x : res) {
                    t += relation[i][x] + " ";
                }
                tmpSet.add(t);
            }
            // 구성된 컬럼으로 구성된 set 이 relation 의 행 개수와 같으면 유일성이 충족됨
            if (tmpSet.size() == relation.length) {
                candidates.add(new ArrayList<>(res));
                answer += 1;
            }
        }

        // 컬럼의 조합을 생성
        for (int i = idx; i < n; i++) {
            res.add(arr.get(i));
            makeKey(arr, res, i + 1, n, r - 1, relation);
            res.remove(res.size() - 1);
        }
    }
}
