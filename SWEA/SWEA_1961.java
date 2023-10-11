package SWEA;

/**
 * N x N 행렬이 주어질 때,

시계 방향으로 90도, 180도, 270도 회전한 모양을 출력하라.


[제약 사항]

N은 3 이상 7 이하이다.

[입력]

가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.

각 테스트 케이스의 첫 번째 줄에 N이 주어지고,

다음 N 줄에는 N x N 행렬이 주어진다.

[출력]

출력의 첫 줄은 '#t'로 시작하고,

다음 N줄에 걸쳐서 90도, 180도, 270도 회전한 모양을 출력한다.

입력과는 달리 출력에서는 회전한 모양 사이에만 공백이 존재함에 유의하라.

(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.) 
*/

import java.util.Scanner;

class SWEA_1961
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int mat_size = sc.nextInt();
			int[][] arr = new int[mat_size][mat_size];
			
			// matrix input
			for(int i = 0; i < mat_size; i++) {
				for(int j = 0; j < mat_size; j++)
					arr[i][j] = sc.nextInt();
			}
			
			// answer
            System.out.println("#" + test_case);
			for(int i = 0; i < mat_size; i++) {
				for(int j = mat_size - 1; j >= 0; j--)
					System.out.print(arr[j][i]);
				System.out.print(" ");
				for(int j = mat_size - 1; j >= 0; j--)
					System.out.print(arr[mat_size - 1 - i][j]);
				System.out.print(" ");
				for(int j = 0; j < mat_size; j++)
					System.out.print(arr[j][mat_size - 1 - i]);
				System.out.print("\n");
			}
		}
		sc.close();
	}
}