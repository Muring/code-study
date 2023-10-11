package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class SWEA_1225 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int totalCase = 10;		// �� �׽�Ʈ ���̽� ��
	static final int arrSize = 8;			// �� �迭 ũ��
	
	
	public static void main(String[] args) throws IOException {
		// ��ü ����
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// �� �׽�Ʈ ���̽���ŭ �ݺ�
		for(int tc = 1; tc <= totalCase; tc++) {

			// ������ �� ������
			br.readLine().trim();
			
			// ť ���� �� ������ ����
			Queue<Integer> que = new LinkedList<Integer>();
			st = new StringTokenizer(br.readLine());
			int temp;	// �ӽ� ����
			for(int idx = 0; idx < arrSize; idx++) {
				temp = Integer.parseInt(st.nextToken());
				que.add(temp);	// ť�� ����
			}
			
			// ��ȣȭ
			int value = 1;	// 0�̸� ����⿡ 1�� ����
			while(value != 0) {
				
				for(int idx = 1; idx < 6; idx++) {	// 5���� ������ 1�� ���ư��� ������ �ݺ���
					value = que.poll();				// ť �� �� �� ������
					value -= idx;					// �� 1�� �� ū ������ ����
					
					if(value <= 0) value = 0;
					que.offer(value);				// ť �� �ڿ� ����
					if(value == 0)					// �߰��� ���� 0�� �Ǹ� �ݺ��� Ż��
						break;
				}
			} // while end
			
			// ��¹� ����
			sb.append("#").append(tc).append(" ");
			for(int idx = 0; idx < arrSize; idx++)
				sb.append(que.poll()).append(" ");
			sb.append("\n");
		}	// tc end
		// ��� ���
		System.out.println(sb);
	}

}
