import java.io.*;
import java.util.*;

class P implements Comparable<P>
{
	int from, to, weight;

	public P(int from, int to, int weight)
	{
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(P args)
	{
		return this.weight <= args.weight ? -1 : 1;
	}
}

public class Prim
{
	static int v; // 정점의 수
	static int e; // 간선의 수
	static int sum = 0;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		List<P>[] nodelist = new ArrayList[v + 1];
		boolean[] visit = new boolean[v + 1];
		
		for (int i = 1; i <= v; i++)
			nodelist[i] = new ArrayList<>();

		for (int i = 0; i < e; i++)
		{
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodelist[start].add(new P(start, end, weight));
			nodelist[end].add(new P(end, start, weight));
		}

		int start = 1;
		bw.write(Integer.toString(result(start, visit, nodelist)));
		br.close();
		bw.flush();
		bw.close();
	}

	public static int result(int start, boolean[] visit, List<P>[] nodelist)
	{
		PriorityQueue<P> pq = new PriorityQueue<P>();
		Queue<Integer> q = new LinkedList<Integer>();
		int sum = 0;
		q.add(start);

		while (!q.isEmpty())
		{
			int cur = q.poll();
			visit[cur] = true;

			for (int i = 0; i < nodelist[cur].size(); i++)
			{
				P edge = nodelist[cur].get(i);
				if (!visit[edge.to])
					pq.add(edge);
			}

			while (!pq.isEmpty())
			{
				P edge = pq.poll();

				if (!visit[edge.to])
				{
					q.add(edge.to);
					visit[edge.to] = true;
					sum += edge.weight;
					break;
				}
			}
		}

		return sum;
	}
}
