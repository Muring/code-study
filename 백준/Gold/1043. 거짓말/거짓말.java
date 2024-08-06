import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int peopleCnt;   // 전체 사람 수
    static int partyCnt;    // 전체 파티 수
    static int[] parentIdx; // 부모 인덱스 저장 배열
    static int truthParentIdx;  // 진실을 알고 있는 집합 사람들의 부모 인덱스
    static int[] truthPersonIdx;   // 진실을 알고 있는 사람들의 번호를 담을 배열
    static int[][] party; // 전체 파티 정보

    private static void union(int a, int b) {
        a = find(a);    // a의 최상단 부모 찾기
        b = find(b);    // b의 최상단 부모 찾기

        if (a != b) {   // a와 b가 다르다는 것은 부모가 다르다는 것이기에 합칠 수 있다.
            if (truthParentIdx == a) {  // a가 진실을 알고있는 집합의 부모라면
                parentIdx[b] = a;   // b는 진실을 모르다가 알게 되었기 때문에 진실 그룹에 편입
            } else if (truthParentIdx == b) {   // b가 진실을 알고 있는 집합의 부모라면
                parentIdx[a] = b;   // a가 진실을 모르다가 알게 된 경우이기 때문에 a를 진실 그룹에 편입
            } else {    // 둘 다 진실을 모르는 경우에는
                parentIdx[b] = a;   // 어느쪽에 편입시켜도 상관 없다.
            }
        }
    }

    private static int find(int idx) {
        // 자기 자신이 부모라면 자신을 리턴
        if (parentIdx[idx] == idx)
            return idx;

        // 아니라면 더 상위의 자신의 부모를 찾아 부모 인덱스를 저장하고 리턴한다.
        return parentIdx[idx] = find(parentIdx[idx]);
    }

    private static void init(StringTokenizer st) {

    }

    private static void createTruthGroup(int truthCnt) {
        for (int idx = 0; idx < truthCnt - 1; idx++) {
            union(truthPersonIdx[idx], truthPersonIdx[idx + 1]);
        }
        // 진실을 알고있는 사람이 있다면
        if (truthPersonIdx.length != 0) {
            truthParentIdx = truthPersonIdx[0]; // 진실을 알고있는 집합의 최상단 부모 인덱스
        }
    }

    private static void startParty() throws IOException {
        party = new int[partyCnt][];
        for (int partyIdx = 0; partyIdx < partyCnt; partyIdx++) {
            st = new StringTokenizer(br.readLine());
            int customerCnt = Integer.parseInt(st.nextToken());

            // 해당 파티의 손님 정보 저장
            party[partyIdx] = new int[customerCnt];
            for (int customerIdx = 0; customerIdx < customerCnt; customerIdx++) {
                party[partyIdx][customerIdx] = Integer.parseInt(st.nextToken());
            }

            // 집합을 만들어 진실을 모르는 사람이 진실을 알게한다.
            for (int customerIdx = 0; customerIdx < customerCnt - 1; customerIdx++) {
                union(party[partyIdx][customerIdx], party[partyIdx][customerIdx + 1]);
            }
        }
    }

    private static int getLieCnt() {
        int lieCnt = 0; // 거짓말 할 수 있는 횟수

        for (int partyIdx = 0; partyIdx < partyCnt; partyIdx++) {
            boolean canLie = true;
            for (int customerIdx = 0; customerIdx < party[partyIdx].length; customerIdx++) {
                if (find(party[partyIdx][customerIdx]) == truthParentIdx) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) lieCnt++;
        }
        return lieCnt;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 사람 수와 파티 수 입력
        st = new StringTokenizer(br.readLine());
        peopleCnt = Integer.parseInt(st.nextToken());
        partyCnt = Integer.parseInt(st.nextToken());

        // 초기 부모 설정
        parentIdx = new int[peopleCnt + 1]; // 1번부터 peopleCnt번째 사람이기에 +1
        for (int idx = 0; idx <= peopleCnt; idx++)
            parentIdx[idx] = idx;   // 가장 처음 부모는 자기 자신이다.

        // 진실을 알고 있는 사람들의 정보 받기
        truthParentIdx = 0; // 가장 처음엔 0
        st = new StringTokenizer(br.readLine());

        int truthCnt = Integer.parseInt(st.nextToken());    // 진실을 알고 있는 사람의 수

        truthPersonIdx = new int[truthCnt];
        for (int idx = 0; idx < truthCnt; idx++) {
            truthPersonIdx[idx] = Integer.parseInt(st.nextToken());
        }

        // 진실을 알고 있는 사람의 집합 만들기
        createTruthGroup(truthCnt);

        // 파티 시작
        startParty();

        // 거짓말 할 수 있는 파티가 몇 개인지 확인한다.
        int lieCnt = getLieCnt();

        // 진실을 아는 사람들에 대한 확인이 끝났다.
        System.out.println(lieCnt);
    }
}