public class Skill_Tree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(String.valueOf(solution(skill, skill_trees)));
    }

    static int solution(String skill, String[] skill_trees){
        int answer = 0;

        for (String s : skill_trees) {
            String skilltree = s;
            for (int i = 0; i < s.length(); i++) {
                String t = String.valueOf(s.charAt(i));
                if (!skill.contains(t)) {
                    skilltree = skilltree.replace(t, "");
                }
            }

            if (skill.indexOf(skilltree) == 0) {
                answer += 1;
            }

        }
        return answer;
    }
}
