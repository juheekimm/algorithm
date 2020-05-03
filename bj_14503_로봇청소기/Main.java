package bj_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, map[][];
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		int x, y, d;
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(clean(x, y, d));
	}

	private static int clean(int x, int y, int d) {
		int dirCount = 0, clean = 0, nx, ny;
		boolean flag = true;
		
		while (flag) {
			if (map[x][y] == 0) {
				map[x][y] = 2;
				clean++;
			}
			
			while (true) {
				if (dirCount == 4) {
					nx = x - dx[d];
					ny = y - dy[d];
					
					if (map[nx][ny] == 1) {	//뒷칸도 벽 
						flag = false;
						break;
					} else {
						x = nx;
						y = ny;
						dirCount = 0;
					}
				}
				
				d = (d + 3) % 4;
				nx = x + dx[d];
				ny = y + dy[d];
				
				if (map[nx][ny] == 0) {
					dirCount = 0;
					x = nx; y = ny;
					break;
				} else {
					dirCount++;
					continue;
				} 
			}
		}
		return clean;
	}
}