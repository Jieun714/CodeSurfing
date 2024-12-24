package Jieun714;
/**
 * 문제: 어렸을때부터 컴퓨터 프로그래밍에 엄청난 소질을 보인 상근이는 항상 소셜 네트워킹 웹사이트를 만들고 싶어 했다. 상근이는 페이스북을 벤치마킹하기 위해 지난 3년간 열심히 사용을 했고, 이제 페이스북의 단점을 보완한 새 소셜 네트워킹 웹 2.0 어플리케이션을 만들려고 한다.
 *      사람들은 소셜 네트워킹 어플리케이션에 가입을 한 다음, 현실에서 아는 사람을 친구로 추가하기 시작한다. 이러한 친구 관계 정보를 이용해 친구 관계 그래프를 그릴 수 있다.
 *      소셜 네트워킹 어플리케이션에서 가장 중요한 기능은 한 사람이 다른 사람의 페이지를 방문했을 때, 친구 관계 그래프에서 두 사람 사이의 경로를 보여주는 기능이다. 경로가 없는 경우에는 보여주지 않는다.
 *      상근이의 서비스는 매우 유명해졌고, 위의 기능은 사람들이 점점 많아질수록 경로를 구하는 시간이 매우 느려지게 되었다. 그 이유는 두 사람 사이의 경로가 없는 경우에 경로를 찾기 위해 너무 오랜시간 그래프를 탐색하기 때문이었다. 따라서, 상근이는 두 사람 사이의 경로가 존재하는지 안 하는지를 미리 구해보려고 한다.
 *      유저의 수와 각 유저의 친구 관계가 입력으로 주어진다. 이때, 주어지는 두 사람이 친구 관계 그래프상에서 경로가 존재하는지 안 하는지를 구하는 프로그램을 작성하시오.
 * 입력: 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 *      각 테스트 케이스의 첫째 줄에는 유저의 수 1 ≤ n ≤ 106이 주어진다. 둘째 줄에는 친구 관계의 수 1 ≤ k ≤ 105가 주어진다. 다음 k개 줄에는 두 정수 0 ≤ a, b < n이 주어진다. 두 수는 친구 관계를 나타내며, 유저 a와 b가 친구라는 소리이다. 다음 줄에는 미리 구할 쌍의 수 1 ≤ m ≤ 105가 주어진다. 다음 m개 줄에는 구해야하는 쌍을 나타내는 u, v가 주어진다.
 * 출력: 각 테스트 케이스마다 "Scenario i:"를 출력한다. i는 테스트 케이스 번호이며, 1부터 시작한다. 그 다음, 각각의 쌍마다 두 사람을 연결하는 경로가 있으면 1, 없으면 0을 출력한다.
 *      각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
 * 해결: 유니온-파인드(Union-Find)
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7511 {
    public static int [] nuf;

    // x의 부모를 찾는 함수
    public static int find(int x) {
        if(x == nuf[x]) return x;
        else return nuf[x] = find(nuf[x]);
    }

    // 조건이 맞는다면 a의 부모 혹은 b의 부모를 치환하는 함수
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (a < b) nuf[b] = a;
            else nuf[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트 케이스 수
        for(int i=1; i<=T; i++) {
            int n = Integer.parseInt(br.readLine()); //유저의 수
            nuf = new int[n];
            for(int j=0; j<n; j++) nuf[j] = j; //자기 자신으로 초기화

            int k = Integer.parseInt(br.readLine()); //친구 관계의 수
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            sb.append("Scenario " + i + ":\n");
            int m = Integer.parseInt(br.readLine()); //계산할 쌍의 개수
            for(int j=0; j<m; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                sb.append(find(u) == find(v) ? 1 : 0).append("\n"); //두 사람을 연결하는 경로가 있으면 1, 없으면 0
            }
            sb.append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}
