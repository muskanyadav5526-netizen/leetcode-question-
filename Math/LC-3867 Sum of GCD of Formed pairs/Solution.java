class Solution {
    //gcd(a,b)-->gcd(b,a%b)
    int gcd(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    public long gcdSum(int[] nums) {
        int n=nums.length;
        int [] prefixGcd=new int [n];
        int currentMax=nums[0];
        for(int i=0;i<n;i++){
            currentMax=Math.max(currentMax,nums[i]);
            int gcd=gcd(nums[i],currentMax);
            prefixGcd[i]=gcd;
        }
        Arrays.sort(prefixGcd);
        int low=0;
        int high=prefixGcd.length-1;
        long sum=0;
        while(low<high){
            sum+=gcd(prefixGcd[low],prefixGcd[high]);
            low++;
            high--;
        }
        return sum;
        
    }
}
