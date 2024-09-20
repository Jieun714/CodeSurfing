package Jieun714;
/**
 * 문제: 계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.
 *      가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.
 *      격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.
 * 입력예시:
 *  m 	 n	  puddles	  return
 *  4	 3	  [[2, 2]]	  4
 * */
public class PRO42898 {
    public class Solution {
        public int solution(int m, int n, int[][] puddles) {
            int [][] dp = new int[n+1][m+1];
            for(int [] p : puddles) dp[p[1]][p[0]] = -1; //물에 잠긴 지역 표기

            dp[1][1] = 1; //시작 지점
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=m; j++) {
                    if(dp[i][j] != 0) continue; //물에 잠긴 지역이라면 넘어감
                    if(dp[i-1][j] > 0) dp[i][j] += dp[i-1][j];
                    if(dp[i][j-1] > 0) dp[i][j] += dp[i][j-1];
                    dp[i][j] %= 1_000_000_007;
                }
            }
            return dp[n][m];
        }
    }
}