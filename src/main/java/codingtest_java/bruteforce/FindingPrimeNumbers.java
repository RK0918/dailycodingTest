package codingtest_java.bruteforce;

import java.util.HashSet;
import java.util.Set;

public class FindingPrimeNumbers {
    // Set을 통해 중복되는 소수 숫자 제외

    // 1. 소수판별 함수 작성
    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n==2) return true;
        // 짝수는 소수가 될 수 없기 때문에 false 반환
        if (n%2==0) return false;

        // for문의 조건식 ( i*i < n ) -> (int)Math.sqrt(n)  대체 가능
        for (int i=3; i*i <= n; i+=2) { // i+=2를 통해 홀수만 체크(위에 이미 짝수 체크함)
            if (n%i==0) return false;
        }
        return true;
    }
    // 2. set을 통해 중복되는 소수 제외, buildNumber, isPrime 함수를 통해 순열, 소수판정
    // 하고 최종 answer 반환
    public int solution(String numbers){
        Set<Integer> set = new HashSet<>();

        boolean[] used = new boolean[numbers.length()];
        // 필요한 타입의 매개변수들을 받고 buildNumbers 함수로 이동
        buildNumbers(numbers, used, "", set);

        int answer = 0;

        // set에 담긴 중복을 제외한(set) number를 순회하여 isPriem(소수판별)
        for (int s : set ) {
            if (isPrime(s)) {
                answer++;
            }
        }
        // buildNumbers(순열), isPrime(소수판별) 함수를 통해
        // answer를 카운팅하고 최종 반환
        return answer;
    }

    // 1) String cur이 비어있지 않다면 set.add를 통해 매개변수로 받은 cur을 set에 저장
    // 2) String cur이 비어있다면 boolean[]의 used[i] 방문처리
    // -> buildNumbers 재귀함수를 통해 numbers의 순열을 만들어 소수 생성
    // 끝나면 다시 used[i] 백트래킹하고 다음 인덱스값의 numbers.charAt(i) 순회

    // 3. 재귀를 통해 매개변수 numbers의 순열을 구한다.
    private void buildNumbers(String numbers, boolean[] used, String cur, Set<Integer> set) {
        if(!cur.isEmpty()) {
            set.add(Integer.parseInt(cur));
        }
        for (int i=0; i < numbers.length(); i++) {
            if (used[i]) continue;

            used[i] = true;

            // set으로 중복을 제거하기 때문에 numbers.charAt(i) +cur 해도 정답
            buildNumbers(numbers, used, cur +numbers.charAt(i), set);
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        FindingPrimeNumbers sol = new FindingPrimeNumbers();

        System.out.println("sol.solution(\"17\") = " + sol.solution("17"));
        System.out.println("sol.solution(\"17\") = " + sol.solution("101"));
    }
}
