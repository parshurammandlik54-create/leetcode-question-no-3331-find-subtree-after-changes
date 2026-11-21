
import java.util.*;

class Solution {
    int[] newParent;
    int[] ans;
    List<Integer>[] children;
    Map<Character, Integer> last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        newParent = new int[n];
        ans = new int[n];
        children = new ArrayList[n];
        last = new HashMap<>();

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        
        dfs(0, parent, s);

    
        for (int i = 0; i < n; i++) {
            children[i].clear();
        }

        for (int i = 1; i < n; i++) {
            children[newParent[i]].add(i);
        }

        
        calculate(0);

        return ans;
    }

    void dfs(int node, int[] parent, String s) {
        char ch = s.charAt(node);

        Integer old = last.get(ch);

        newParent[node] = (old == null) ? parent[node] : old;

        last.put(ch, node);

        for (int child : children[node]) {
            dfs(child, parent, s);
        }

        if (old == null) {
            last.remove(ch);
        } else {
            last.put(ch, old);
        }
    }

    int calculate(int node) {
        int size = 1;

        for (int child : children[node]) {
            size += calculate(child);
        }

        ans[node] = size;
        return size;
    }
}

