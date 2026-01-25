package codingtest_java.bruteforce;

import java.util.Arrays;

public class Carpet {
    public int[] soltuion(int brown, int yellow){
        int total = brown + yellow ;
        // 카펫은 직사각형(가로길이 >= 세로길이) 이므로
        // h*h <= total  성립함
        // h < = total/h도 마찬가지
        // 또한 h(세로길이)는 최소 3부터 시작하게 됨 ( 위 아래 최소 테두리 1+(가운데 노란색+1)+1 이므로
        for (int h=3; h <= total/h; h++) {

            // if (total%h !=0) continue;

            // 가로 * 세로 = 격자의 총 갯수이므로 total%h가 성립해서 나머지가 없어야
            // 정수 * 정수 = 격자 가 성립하게됨
            // h(세로)는 정수값이 확실하지만 w(가로)의 개수가 소수점일 경우
            // 직사각형이 성립하지 않게됨)
            if (total%h==0) {
                int w = total/h; // 격자개수를 높이(세로)로 나누면 가로(w)

                if (w>=h && (w-2)*(h-2)==yellow) {
                    return new int[] {w, h};
                }
            }
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        Carpet sol = new Carpet();
        int[] result = sol.soltuion(10,  2);
        System.out.println(Arrays.toString(result));
        int[] result2 = sol.soltuion(8,  1);
        System.out.println(Arrays.toString(result2));
        int[] result3 = sol.soltuion(24,  24);
        System.out.println(Arrays.toString(result3));
    }


}
