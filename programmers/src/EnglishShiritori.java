import java.util.HashSet;

public class EnglishShiritori {
    public static void main(String[] args) {
        EnglishShiritori es = new EnglishShiritori();
        int n = 2;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};

        int[] t = es.solution(n, words);

        System.out.println(t[0] + " " + t[1]);

    }

    public int[] solution(int n, String[] words) {
        var set = new HashSet<String>();

        set.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                return new int[]{(i % n) + 1, i / n + 1};
            }

            if (!set.add(words[i])) {
                return new int[]{(i % n) + 1, i / n + 1};
            }
        }

        return new int[]{0, 0};
    }
}
