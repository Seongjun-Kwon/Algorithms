import java.io.*;
import java.util.*;

class A implements Comparable<A>
{
	int from;
	int to;
	int weight;

	public A(int from, int to, int weight)
	{
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int compareTo(A args)
	{
		return this.weight <= args.weight ? -1 : 1;
	}
}

public class Kruskal
{
	static int v; // 정점의 수
	static int e; // 간선의 수
	static PriorityQueue<A> pq = new PriorityQueue<A>(); // 간선의 가중치를 최소 힙으로 하는 우선순위 큐
	static int[] parent; // union-find 에서 필요한 부모 노드를 담는 배열
	static boolean[] visit;
	static int result = 0;
	static int cnt = 0;

	public static int find(int x) // 정점 x의 부모를 찾는 메소드
	{
		if (x == parent[x])
			return x;
		parent[x] = find(parent[x]);
		return parent[x];
	}

	public static void union(int x, int y)
	{
		int xroot = find(x);
		int yroot = find(y);
		if (xroot != yroot)
			parent[xroot] = y;
		else
			return;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		parent = new int[v + 1];
		visit = new boolean[v + 1];
		
		for (int i = 1; i <= v; i++)
			parent[i] = i;

		for (int i = 0; i < e; i++)
		{
			A node = pq.poll();
			int start = node.from;
			int end = node.to;
			int a = find(start);
			int b = find(end);
			if (a == b)
				continue;
			union(a, b);
			result += node.weight;
			cnt++;
			if (cnt == e - 1)
				break;
		}

		bw.write(Integer.toString(result));
		br.close();
		bw.flush();
		bw.close();
	}
}
