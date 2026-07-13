class Solution {
    int numberOfDigits(int n){
        int count=0;
        while(n>0){
            n=n/10;
            count++;
            
        }
        return count;
    }
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result=new ArrayList<>();
        int startLength=numberOfDigits(low);
        int endLength=numberOfDigits(high);
        for(int k=startLength;k<=endLength;k++){
            int firstStartDigit=1;
            int lastStartDigit=10-k;
            // to generate all number of lenght k
            for(int i=firstStartDigit;i<=lastStartDigit;i++){
                int number=i;
                int start=i;
                //to generate one number of length k
                for(int j=1;j<k;j++){
                    number=number*10+(start+j);
                }
                if(low<=number && number<=high){
                    result.add(number);
                }
            }
            
        }
        return result;
        
    }
}
