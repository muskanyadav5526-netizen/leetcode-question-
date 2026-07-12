class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n=arr.length;
        if(n==0){
            return new int []{};
        }
        int rank=1;
        int [] result=new int [n];
        int [][] pairs=new int [n][2];
        for(int i=0;i<n;i++){
            pairs[i][0]=arr[i];
            pairs[i][1]=i;
        }
        Arrays.sort(pairs,(a,b)->Integer.compare(a[0],b[0]));
        result[pairs[0][1]]=1;
        for(int j=1;j<pairs.length;j++){
            if(pairs[j][0]!=pairs[j-1][0]){
                rank++;
                result[pairs[j][1]]=rank;
            }
            else{
                result[pairs[j][1]]=rank;
            }
            
        }
        return result;

    }
}
