class Solution {
    int gcd(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    public int findGCD(int[] nums) {
       int smallest=Integer.MAX_VALUE;
       int largest=Integer.MIN_VALUE;
       for(int x:nums){
            smallest=Math.min(smallest,x);
            largest=Math.max(largest,x);
       }
       return gcd(smallest,largest);
       // time complexity=O(n)+O(logM)=O(n)
    }
}
