/*
    Shortest path in Weighted DAG from a given source node
*/
import java.util.*;

class WDAG{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();

        for(int i = 0 ;i < v; i++){
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for(int i = 0 ; i < e; i++){
            int u = sc.nextInt();
            int vv = sc.nextInt();
            int w = sc.nextInt();

            ArrayList<Integer> temp = new ArrayList<Integer>();

            temp.add(vv);temp.add(w);

            adj.get(u).add(temp);
        }

        //Code to print Adj list
        System.out.println("");
        System.out.println("Adjacency List is ");
        int i = 0;
        while(i < v ){
            for(ArrayList<Integer> ar : adj.get(i)){
                Integer n = ar.get(0), w = ar.get(1);
                System.out.print(i + " -->  ("+ n +" , "+ w +") || "); 
            }
            System.out.println("");
            i++;
        }
        System.out.println("");

        int src = 0;
        int[] dis = shorttestPath(v,adj,src);
        
        for(int it : dis)
            if(it != Integer.MAX_VALUE)
                System.out.print(it+" ");
            else
                System.out.print("INF ");
        System.out.println("");

        sc.close();
    }

    public static int[] shorttestPath(int v,ArrayList<ArrayList<ArrayList<Integer>>> adj,int src){
        boolean[] vis = new boolean[v]; 
        int[] d = new int[v];
        Stack<Integer> st = new Stack();

        Arrays.fill(d,Integer.MAX_VALUE);
        d[src] = 0;

        for(int i = 0 ; i < v;i++){
            if(!vis[i])
                dfs(i,vis,st,adj);
        }

        while(!st.isEmpty()){
            int cur = st.pop();

            if(d[cur] != Integer.MAX_VALUE){
                for(ArrayList<Integer> it : adj.get(cur)){
                    int n = it.get(0), w = it.get(1);

                    if(d[cur] + w < d[n])
                        d[n] = d[cur] + w;
                }
            }
        }
        
        return d;
    }

    public static void dfs(int v,boolean[] vis,Stack<Integer> st, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        vis[v] = true;

        for(ArrayList<Integer> it : adj.get(v)){
            int node = it.get(0);
            if(!vis[node])
                dfs(node,vis,st,adj);
        }

        st.add(v);
    }
}