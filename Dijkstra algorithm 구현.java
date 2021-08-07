import java.io.*;
import java.util.*;

class D implements Comparable<D>
{
	int v, weight;

	public D(int v, int weight)
	{
		super();
		this.v = v;
		this.weight = weight;
	}

	@Override
	public int compareTo(D args)
	{
		return this.weight <= args.weight ? -1 : 1;
	}
}

public class Dijkstra
{
	static int v;
	static int e;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		List<D>[] adj = new ArrayList[v + 1];
		int[] dis = new int[v + 1];
		
		for (int i = 1; i <= v; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < e; i++)
		{
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[start].add(new D(end, weight));
		}

		PriorityQueue<D> pq = new PriorityQueue<>();
		int first = 1;
		dis[first] = 0;
		pq.add(new D(first, dis[first]));

		while (!pq.isEmpty())
		{
			D edge = pq.poll();
			int idx = edge.v;
			int dist = edge.weight;
			if (dis[idx] != dist)
				continue;

			for (int i = 0; i < adj[idx].size(); i++)
			{
				int nidx = adj[idx].get(i).v;
				int cost = adj[idx].get(i).weight;

				if (dis[nidx] > dist + cost)
				{
					dis[nidx] = dist + cost;
					pq.add(new D(nidx, dis[nidx]));
				}
			}
		}
		for (int i = 1; i <= v; i++)
			bw.write(Integer.toString(dis[i]));
		
		br.close();
		bw.flush();
		bw.close();
	}
}
