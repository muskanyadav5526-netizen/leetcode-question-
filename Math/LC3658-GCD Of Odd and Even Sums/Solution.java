class Solution {
    public int gcdOfOddEvenSums(int n) {
        //sum of first n odd numbers=n*n
        //sum of first n even numbers=n*(n+1)
        //gcd(n*n,n*(n+1))-->ngcd(n,n+1)-->n*1=n
        // n and n+1 are coprime 
        return n;
    }
}
