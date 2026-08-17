class Solution {
    public boolean isPalindrome(String s) {
        int n=s.length();
        int low=0;
        int high=n-1;
        boolean result=true;
        if(n==0){
            return true;
        }
        while(low<=high){

            while(low<=high && !Character.isLetterOrDigit(s.charAt(low))){
                low++;
                // kya pta low increment karte karte itna bda ho gya ki string length se bhi jayada hai and then you try to accres charAt(low)--> outOfBoundException
            }
            while(low<=high && !Character.isLetterOrDigit(s.charAt(high))){
                high--;
                // kya pta high decrement karte karte itna chota ho gya ki negative me ho gya hai -1 ,-2  hai and then you try to access charAt(high)--> outOfBoundException

            }
            if(low>high){
                //s='.!.'--> emptyString so true 
                return true;
            }
            if(Character.toLowerCase(s.charAt(low))!=Character.toLowerCase(s.charAt(high))){
                return false;
                
            }
            else{
                low++;
                high--;
            }

        }
        return result;
        
    }
}
// time complexity -->O(n)
//space  complexity--> O(1)
