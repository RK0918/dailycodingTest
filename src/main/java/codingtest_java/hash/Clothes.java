package codingtest_java.hash;

// import java.util.ArrayList;
import java.util.HashMap;
// import java.util.List;
import java.util.Map;

public class Clothes {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        // 1. 의상 종류별로 나눠서 카운트해서 저장
        for (String[] clothe : clothes) {
            String type = clothe[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        // 2. 각 의상 조합 계산 ( 안입는 경우가 있기 때문에 각 종류별로 +1)
        int answer = 1;
        for (int count : map.values()) {
            answer *= (count + 1);
        }
        return answer - 1; // 아무것도 안입은경우 -1
    }
    public static void main(String[] args) {
        Clothes s = new Clothes();

        // ---------------------------
        // ① 기본 테스트 (예제 테스트)
        // ---------------------------
        String[][] tc1 = {
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        };
        System.out.println("TC1 결과 = " + s.solution(tc1)); // 기대값: 5


        // ---------------------------
        // ② 종류 1개, 아이템 여러 개
        // ---------------------------
        String[][] tc2 = {
                {"hat1", "headgear"},
                {"hat2", "headgear"},
                {"hat3", "headgear"}
        };
        // headgear: 3개 → (3+1) -1 = 3
        System.out.println("TC2 결과 = " + s.solution(tc2)); // 기대값: 3


        // ---------------------------
        // ③ 모든 종류가 각각 1개씩
        // ---------------------------
        String[][] tc3 = {
                {"a", "typeA"},
                {"b", "typeB"},
                {"c", "typeC"}
        };
        // (1+1)*(1+1)*(1+1) -1 = 7
        System.out.println("TC3 결과 = " + s.solution(tc3)); // 기대값: 7


        // ---------------------------
        // ④ 종류 2개, 각 1개
        // ---------------------------
        String[][] tc4 = {
                {"aa", "top"},
                {"bb", "bottom"}
        };
        // (1+1)*(1+1)-1 = 3
        System.out.println("TC4 결과 = " + s.solution(tc4)); // 기대값: 3


        // ---------------------------
        // ⑤ 같은 이름, 같은 종류(중복은 문제에서 없지만 테스트용)
        // ---------------------------
        String[][] tc5 = {
                {"mask", "face"},
                {"mask", "face"}
        };
        // face: 2개 → (2+1)-1 = 2
        System.out.println("TC5 결과 = " + s.solution(tc5)); // 기대값: 2


        // ---------------------------
        // ⑥ 종류 1개, 아이템 1개
        // ---------------------------
        String[][] tc6 = {
                {"hat", "headgear"}
        };
        // (1+1)-1 = 1
        System.out.println("TC6 결과 = " + s.solution(tc6)); // 기대값: 1
    }
}

/*
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 1. 의상 종류별로 List 형태로 저장
        Map<String, List<String>> map = new HashMap<>();

        for (String[] item : clothes) {
            String name = item[0];
            String type = item[1];

            map.putIfAbsent(type, new ArrayList<>());
            map.get(type).add(name);
        }

        // 2. 조합 계산: 각 종류마다 (의상 수 + 1) 곱해주기
        int answer = 1;

        for (List<String> list : map.values()) {
            answer *= (list.size() + 1);
        }

        // 3. 아무것도 안 입는 경우 제거
        return answer - 1;
    }
}
*/
