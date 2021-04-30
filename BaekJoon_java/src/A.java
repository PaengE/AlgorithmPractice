import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A {
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
    }

}
