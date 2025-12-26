package codingtest_java.hash;

import java.util.HashMap;
import java.util.Map;

public class Ponketmon {
        public int solution(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            // Set<Integer> set = new HashSet<>(); 종류이므로 set으로도 가능


            // 폰켓몬을 종류별로 카운팅하기 위 HashMap에 gerOrDefault 함수를
            // 통해 카운팅하여 저장 => 종류별로 key(폰켓몬) :value(마리 수)
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0)+1);
                // set.add(num);
            }

            // 종류 수와 선택 가능한 수 중 작은 값
            // map.size() = 종류 수,
            // nums.length/2 = 포켓몬 수의 절반(조건에서 절반만 가져갈 수 있다고 규칙이 있음)
            return Math.min(map.size(), nums.length / 2);
            //return Math.min(set.size(), nums.length / 2);
        }
        public static void main(String[] args) {
            Ponketmon p = new Ponketmon();

            System.out.println(p.solution(new int[]{3, 1, 2, 3})); // 2
            System.out.println(p.solution(new int[]{3, 3, 3, 3})); // 1
            System.out.println(p.solution(new int[]{1, 2, 3, 4, 5, 6})); // 3
            System.out.println(p.solution(new int[]{1, 1, 2, 3, 3, 3})); // 3
            System.out.println(p.solution(new int[]{1, 2})); // 1
            System.out.println(p.solution(new int[]{})); // 0
            System.out.println(p.solution(new int[]{100})); // 0
        }
    }


// 총 N마리 중 N/2마리 가져가
// 근데 나는 최대한 많은 종류의 폰켓몬 가짐
