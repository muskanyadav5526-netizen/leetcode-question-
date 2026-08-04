class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result =new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        int low=0;
        int high=1;
        while(low!=n-1){
            int diff=nums[high]-nums[low];
            if(diff>1){
                int count=diff-1;
            
             for(int i=1;i<=count;i++){
                    result.add(nums[low]+i);
                }
            }
            low++;
            high++;
        }
        return result;
    }
}
