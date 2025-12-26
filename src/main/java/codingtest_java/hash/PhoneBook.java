package codingtest_java.hash;

import java.util.Arrays;

public class PhoneBook {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        // phone_book[i].length()가 phone_book[i+1].length()보다 크면
        // StringIndexOutOfBoundsException 발생예외 처리를 해줘야 한다.
        // phone_book[i].length() <= phone_book[i+1].length() 조건을
        // 길이 체크하는 조건식을 추가하여 예외처리

        for (int i = 0; i < phone_book.length - 1; i++) {
            // phone_book[i]의 경우 String 이므로 length -> length() 이다.
            // length는 배열과 리스트만 해당한다.

            //if문을 통해 정렬된 배열에서 i인덱스, i+1 인덱스값과 비교하여
            // i인덱스값의 string length가 i+1의 인덱스값의 길이보다 적고( 그래야 접두어가 될 수 있음. 아닐 경우 절대 접두어가 될 수 없으므로 넘기면 됨 )
            // i+1의 인덱스값이 i인덱스값의 접두어일 경우 false return
            if (phone_book[i].length() <= phone_book[i+1].length() &&
                    phone_book[i].equals(phone_book[i+1].substring(0, phone_book[i].length()))) {
                return false;
            }
        }
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();

        String[][] testCases = {
                {"119", "97674223", "1195524421"}, // false, 119가 접두사
                {"123","456","789"},               // true, 접두사 없음
                {"12","123","1235","567","88"},    // false, 12가 접두사
                {"911","976","987","9112"},        // false, 911이 접두사
                {"321","432","543"}                 // true, 접두사 없음
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = pb.solution(testCases[i]);
            System.out.println("테스트 케이스 " + (i+1) + ": " + Arrays.toString(testCases[i]));
            System.out.println("결과: " + result);
            System.out.println("---------------------------");
        }
    }
}


/* Arrays1.startsWith(Arrays2)를 활용한 코드

import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            // phone_book[i+1]이 phone_book[i]로 시작하는지 확인
            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
 */