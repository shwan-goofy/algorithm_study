class Solution {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length]; 
        int count = 0;
        for(int i = 0; i < M.length; i++) {
            if(!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] M, boolean[] visited, int person) {
        for(int other = 0; other < M.length; other++) {
            if(M[person][other] == 1 && !visited[other]) {
                visited[other] = true;
                dfs(M, visited, other); 
            }
        }
    }
}
