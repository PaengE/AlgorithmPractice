import java.util.Arrays;
import java.util.Comparator;

public class FilenameSort {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        FilenameSort fs = new FilenameSort();

        String[] ans = fs.solution(files);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i] + " ");
        }
    }

    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1.split("[0-9]")[0];
                String s2 = o2.split("[0-9]")[0];

                int result = s1.toLowerCase().compareTo(s2.toLowerCase());

                if (result == 0) {
                    result = getNum(o1, s1) - getNum(o2, s2);
                }

                return result;
            }
        });
        return files;
    }

    static int getNum(String s, String h) {
        s = s.replace(h, "");
        String result ="";
        for( char c : s.toCharArray()) {
            if( Character.isDigit(c) && result.length() < 5 ) {
                result+=c;
            }else
                break;
        }
        return Integer.valueOf(result);
    }
}
