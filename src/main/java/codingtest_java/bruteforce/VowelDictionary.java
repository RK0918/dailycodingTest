package codingtest_java.bruteforce;

public class VowelDictionary {
    public int solution(String word) {
        int[] w= {781, 156, 31, 6, 1};
        String alp= "AEIOU";

        int answer = 0;

        for (int i=0; i < word.length(); i++){
            // word.charAt(i) == word의 단어(char) 반환
            // A=0, E=1, I=2, O=3, U=4
            // String.indexOf : 해당 문자열의 인덱슥밧 반환
            int idx = alp.indexOf(word.charAt(i));
            // A = 1, AA =2 이미 접두어로 붙어있는 상태
            // 자신을 포함하니깐 +1을 해줘야 함. 따라서 자릿수마다 +1

            // 테스트케이스 "I" 의 경우,
            // A(+1) 를 사용한 단어를 전부 사용함. (781)
            // E(+1) 를 사용한 단어를 전부 사용함. (781)
            // 따라서 (1+781) + (1+781) 이 성립하게 되고 "I"가 됨.
            // 이걸 각 자릿수(i)마다 반복한다. ("I"는 자릿수 하나이므로 한 번만)
            answer += idx*w[i] +1;
        }
        return answer;
    }

    public static void main(String[] args) {
        VowelDictionary sb = new VowelDictionary();
        System.out.println(sb.solution("AAAE"));
        System.out.println(sb.solution("AAAAA"));
        System.out.println(sb.solution("I"));
    }
}
