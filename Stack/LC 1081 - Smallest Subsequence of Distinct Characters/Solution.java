class Solution {
    public String smallestSubsequence(String s) {
        int [] freq=new int [26];
        for(char ch:s.toCharArray()){
            freq[ch-'a']++;
        }
        // decrement the frequency
        //1 skip the element 
        // pushed the element
        // pop the elemnt when peek> current
        boolean [] visited=new boolean [26];

        Stack<Character> st=new Stack<>();
        // first element ko alag se push karne ki koi jarurat nhi hai q ki tab if aur while execute hee nhi hoga jab starting mein stack empty rahega phele koi element push hoga 
        // st.push(s.charAt(0));
        // visited[s.charAt(0)-'a']=true;
        // freq[s.charAt(0)-'a']--;
        
            for(int i=0;i<s.length();i++){
                freq[s.charAt(i)-'a']--;
                // jab current wala phele se stack mein ho 
                if(visited[s.charAt(i)-'a']==true){
                    //freq[s.charAt(i)-'a']--;
                    continue;//i++;
                }
                // jab current wala character chota hai stack se aur vo(stack peek) baad mein bhi present hai
                while(!st.isEmpty()&&st.peek()>s.charAt(i)&& freq[st.peek()-'a']>0){
                        
                        visited[st.peek()-'a']=false;
                        st.pop();
                    
                }

                // while se bhahar aaye matlab push karna hai 
                    st.push(s.charAt(i));
                    visited[s.charAt(i)-'a']=true;
                   // freq[s.charAt(i)-'a']--;--> for loop handles freq decrement of skip,push,pop
            }

            
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();

       
    }
}
