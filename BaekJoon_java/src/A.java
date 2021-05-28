import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A {
    static int[] op,eq, sel;
    public static void main(String[] args) {
//        String str = "abc=123&abc[]=234&abc=345&qwe=000&abc[]=135";
//        String[] s = new String[2];
//
//        String regex = "[a-z]+(\\[\\])*=[0-9]*";
//        Pattern p = Pattern.compile(regex);
//
//        Matcher m = p.matcher(str);
//
//
//        while (m.find()) {
////            if (m.find()) {
//                System.out.println("start = " + m.start() + " end = " + m.end());
//                System.out.println(m.group());

//            } else {
//                System.out.println("못찾았다");
//            }

//        }


        String str = "qwerty";
        for (int i = 0; i < 5; i++) {
            str = str + "05";
        }
        System.out.println(str);

         op = new int[]{1, 2, 3, 4};
         eq = new int[]{2, 1, 1, 1};
         sel = new int[]{-1, -1, -1, -1, -1};

         sameP(0,0);

    }

    static void sameP(int idx, int st) {
        if (idx == 4) {
            System.out.println("res");
            for (int i = 0; i < 5; i++) {
                System.out.print(sel[i] + " ");
            }
            System.out.println();
            return;
        }

        if (eq[idx] == 0) {
            sameP(idx + 1, 0);
        } else {
            eq[idx]--;
            for (int i = st; i < 5; i++) {
                if (sel[i] < 0) {
                    sel[i] = idx;
                    sameP(idx, i + 1);
                    sel[i] = -1;
                }

            }
            eq[idx]++;
        }
    }

}
