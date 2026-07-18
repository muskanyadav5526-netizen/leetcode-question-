# LC1979 - Find Greatest Common Divisor of Array

## Problem Summary
Find the GCD of the smallest and largest element in the array.

## Initial Thought Process (Idea + Java Syntax)
**Idea:**
- Find the smallest and largest element.
- Use Euclidean Algorithm to find their GCD.

**Java Syntax:**
```java
Arrays.sort(nums);
int smallest = nums[0];
int largest = nums[nums.length - 1];
```

## Mistakes I Made (with examples)
- Used `Arrays.sort()` even though only the minimum and maximum were required.
- Initially thought the time complexity was `O(n)`.

## Key Observations
- Only the minimum and maximum affect the answer.
- GCD can be found efficiently using the Euclidean Algorithm.
- Sorting is not required.

## Algorithm
1. Find the minimum and maximum element.
2. Compute `gcd(min, max)`.
3. Return the answer.

## Time & Space Complexity

**My Solution**
- Time: `O(n log n)`
- Space: `O(1)`

**Optimal Solution**
- Time: `O(n)`
- Space: `O(1)`

## What I Learned
- Use Euclidean Algorithm for GCD problems.
- Don't sort when only min/max is needed.
- Always calculate the complexity of every operation.
