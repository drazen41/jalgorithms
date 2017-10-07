package algorithms1;

public class WeightedUnionUF {
	private int[] id;
	private int[] sz;
	public WeightedUnionUF(int n) {
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	
	}
	private int root(int p) {
		while (p != id[p]) {
			p = id[p];
			
		}
		return p;
	}
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	public void	union(int p, int q) {
		int j = root(p);
		int i = root(q);
		if (i == j) {
			return;
		}
		if (sz[i]<sz[j]) {
			id[i]=j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
	public int rootPathCompression(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
			
		}
		return p;
	}
}
