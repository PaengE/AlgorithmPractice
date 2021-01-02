import java.util.Arrays;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"12","1234","1235","567","88"};


        PhoneNumberList p = new PhoneNumberList();
        boolean flag = p.solution(phone_book);

        System.out.println("flag = " + flag);
    }

    // O(nlogn)
    public boolean solution(String[] phone_book) {
        int size = phone_book.length - 1;

        Arrays.sort(phone_book);

        for (int i = 0; i < size; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;

        /*  naive 한 방식 O(n^2)

        TreeSet<String> set = new TreeSet<>(Arrays.asList(phone_book));

        int size = phone_book.length;
        for (int i = 0; i < size && flag; i++) {
            Iterator it = set.iterator();
            int count = 0;

            while (it.hasNext()) {
                if (String.valueOf(it.next()).indexOf(phone_book[i]) == 0) {
                    count += 1;
                }

                if (count == 2) {
                    flag = false;
                    break;
                }
            }
        }

        */
    }
}
