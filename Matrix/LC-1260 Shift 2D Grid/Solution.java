class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result=new ArrayList<>();
        int m=grid.length;
        int n=grid[0].length;
        int totalElements=m*n;
        int [][] temp=new int [m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int index=(i*n)+j;
                int newIndex=(index+k)%totalElements;
                int newRow=newIndex/n;
                int newColumn=newIndex%n;
                temp[newRow][newColumn]=grid[i][j];
            }
        }
        // convert temp[][]-->List<List<Integer>> 
        for(int i=0;i<m;i++){
            List<Integer> rowList=new ArrayList<>();
            for(int j=0;j<n;j++){
                rowList.add(temp[i][j]);
            }
            result.add(rowList);
        }
        return result;

        
    }
}
