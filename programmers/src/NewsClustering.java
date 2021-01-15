import java.util.ArrayList;

public class NewsClustering {
    public static void main(String[] args) {
        String str1 = "aaaaa";
        String str2 = "aaa";

        NewsClustering nc = new NewsClustering();
        System.out.println(nc.solution(str1, str2));
    }

    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        var list1 = new ArrayList<String>();
        var list2 = new ArrayList<String>();

        for (int i = 1; i < str1.length(); i++) {
            Character c1 = str1.charAt(i - 1);
            Character c2 = str1.charAt(i);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String s = String.valueOf(c1) + String.valueOf(c2);
                list1.add(s);
            }
        }
        for (int i = 1; i < str2.length(); i++) {
            Character c1 = str2.charAt(i - 1);
            Character c2 = str2.charAt(i);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String s = String.valueOf(c1) + String.valueOf(c2);
                list2.add(s);
            }
        }

        int intersection = 0;
        int sizeA = list1.size();
        int sizeB = list2.size();

        if (sizeA == 0 && sizeB == 0) {
            return 65536;
        }

        for (int i = 0; i < sizeA; i++) {
            if (list2.indexOf(list1.get(i)) != -1) {
                intersection++;
                list2.remove(list1.get(i));
            }
        }
        int union = sizeA + sizeB - intersection;

        return (int)(((float)intersection / (float) union) * 65536);
    }
}
