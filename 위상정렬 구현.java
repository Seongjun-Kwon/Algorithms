import java.util.*;

public class Main
{
	static int n = 10;
	static int[] indeg = new int[n + 1];
	static List<List<Integer>> adj = new ArrayList<List<Integer>>();

	static void Topo_sort()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 1; i <= n; i++)
		{
			if (indeg[i] == 0)
				q.offer(i);
		}

		while (!q.isEmpty())
		{
			int cur = q.poll();
			result.add(cur);

			for (int i = 0; i < adj.get(i).size(); i++)
			{
				int nxt = adj.get(cur).get(i);
				indeg[nxt]--;
				if (indeg[nxt] == 0)
					q.offer(nxt);
			}

			if (result.size() != n)
			{
				System.out.println("Cycle exists!");
				return;
			}

			for (int i = 0; i < n; i++)
				System.out.println(result.get(i) + " ");
		}
	}
}
