/*
Lv. 3 #12987 - 숫자 게임

    문제 설명
        xx 회사의 2xN명의 사원들은 N명씩 두 팀으로 나눠 숫자 게임을 하려고 합니다. 두 개의 팀을 각각 A팀과 B팀이라고 하겠습니다. 숫자 게임의 규칙은 다음과 같습니다.
            · 먼저 모든 사원이 무작위로 자연수를 하나씩 부여받습니다.
            · 각 사원은 딱 한 번씩 경기를 합니다.
            · 각 경기당 A팀에서 한 사원이, B팀에서 한 사원이 나와 서로의 수를 공개합니다. 그때 숫자가 큰 쪽이 승리하게 되고, 승리한 사원이 속한 팀은 승점을 1점 얻게 됩니다.
            · 만약 숫자가 같다면 누구도 승점을 얻지 않습니다.

        전체 사원들은 우선 무작위로 자연수를 하나씩 부여받았습니다. 그다음 A팀은 빠르게 출전순서를 정했고 자신들의 출전 순서를 B팀에게 공개해버렸습니다. B팀은 그것을 보고 자신들의 최종 승점을 가장 높이는 방법으로 팀원들의 출전 순서를 정했습니다. 이때의 B팀이 얻는 승점을 구해주세요.
        A 팀원들이 부여받은 수가 출전 순서대로 나열되어있는 배열 A와 i번째 원소가 B팀의 i번 팀원이 부여받은 수를 의미하는 배열 B가 주어질 때, B 팀원들이 얻을 수 있는 최대 승점을 return 하도록 solution 함수를 완성해주세요.


    제한사항
        · A와 B의 길이는 같습니다.
        · A와 B의 길이는 1 이상 100,000 이하입니다.
        · A와 B의 각 원소는 1 이상 1,000,000,000 이하의 자연수입니다.


    입출력 예
        A	        B	        result
        [5,1,3,7]	[2,2,6,8]	3
        [2,2,2,2]	[1,1,1,1]	0


    입출력 예 설명
        입출력 예 #1
            A 팀     [첫 번째 팀원] 5   [두 번째 팀원] 1   [세 번째 팀원] 3   [네 번째 팀원] 7
            B 팀     [4 번 팀원] 8     [2 번 팀원] 2     [3 번 팀원] 6      [1 번 팀원] 2

            A 팀은 숫자 5를 부여받은 팀원이 첫번째로 출전하고, 이어서 1,3,7을 부여받은 팀원들이 차례대로 출전합니다.
            B 팀원들을 4번, 2번, 3번, 1번의 순서대로 출전시킬 경우 팀원들이 부여받은 숫자들은 차례대로 8,2,6,2가 됩니다. 그러면, 첫 번째, 두 번째, 세 번째 경기에서 승리하여 3점을 얻게 되고, 이때가 최대의 승점입니다.

        입출력 예 #2
            B 팀원들을 어떤 순서로 출전시켜도 B팀의 승점은 0점입니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.40ms, 85.7MB)
        테스트 2 〉	통과 (0.47ms, 77.2MB)
        테스트 3 〉	통과 (0.47ms, 86.2MB)
        테스트 4 〉	통과 (0.56ms, 74.3MB)
        테스트 5 〉	통과 (0.51ms, 76.2MB)
        테스트 6 〉	통과 (0.53ms, 62.9MB)
        테스트 7 〉	통과 (0.45ms, 76.6MB)
        테스트 8 〉	통과 (0.40ms, 77.5MB)
        테스트 9 〉	통과 (1.23ms, 80.2MB)
        테스트 10 〉	통과 (0.89ms, 72.9MB)
        테스트 11 〉	통과 (0.92ms, 71.3MB)
        테스트 12 〉	통과 (1.05ms, 79.6MB)
        테스트 13 〉	통과 (5.15ms, 71.7MB)
        테스트 14 〉	통과 (5.04ms, 82.1MB)
        테스트 15 〉	통과 (5.76ms, 81.9MB)
        테스트 16 〉	통과 (8.07ms, 75.7MB)
        테스트 17 〉	통과 (0.69ms, 72.9MB)
        테스트 18 〉	통과 (1.30ms, 78.6MB)

    효율성  테스트
        테스트 1 〉	통과 (63.75ms, 67MB)
        테스트 2 〉	통과 (57.02ms, 62.7MB)
        테스트 3 〉	통과 (59.37ms, 66.9MB)

    채점 결과
        정확성: 85.7
        효율성: 14.3
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO12987 {
    static int score; // B 팀의 팀원들이 얻을 수 있는 최대 승점

    public static void getScore(int[] A, int[] B) { // B 팀원들이 얻을 수 있는 최대 승점을 구하는 메서드
        Arrays.sort(A); // A 팀의 팀원들이 부여받은 수 오름차순 정렬
        Arrays.sort(B); // B 팀의 팀원들이 부여받은 수 오름차순 정렬

        int bIndex = 0; // B 팀의 인덱스
        for (int i = 0, size = A.length; i < size;) {
            if (A[i] < B[bIndex]) { // B 팀의 팀원이 부여받은 수가 A 팀의 팀원이 부여받은 수보다 클 때
                i += 1; // A 팀의 인덱스 증가
                score += 1; // B 팀의 팀원들이 얻을 수 있는 승점 추가
            }

            bIndex += 1;

            if (bIndex == size) { // B 팀의 팀원들이 부여받은 수를 모두 검사했을 경우
                break; // A 팀의 팀원들이 부여받은 수와 비교 종료
            }
        }
    }

    public int solution(int[] A, int[] B) {
        getScore(A, B);

        return score;
    }
}