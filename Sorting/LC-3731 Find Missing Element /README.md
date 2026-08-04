# LC 3731 - Find Missing Elements

## Problem Summary

Given an integer array, return all the missing integers that lie between the minimum and maximum elements of the array.

## Constraints

* The range of element values can be much larger than the number of elements.
* The output may contain multiple missing numbers.

## Initial Thought

I initially thought of sorting the array and iterating from the smallest element to the largest element. For every integer in that range, I wanted to check whether it existed in the array and, if not, add it to the result.

## Why That Doesn't Work

Although sorting helps, iterating from the minimum value to the maximum value makes the time complexity depend on the value range rather than the input size.

For example:

```text
nums = [1, 1000000000]
```

The algorithm would perform nearly one billion iterations even though the array contains only two elements.

## Key Insight

After sorting, all missing numbers must lie **between adjacent elements**.

For every adjacent pair:

* If the difference is `1`, no numbers are missing.
* If the difference is greater than `1`, generate all integers between those two elements.

This avoids checking numbers that are already known to exist.

## Algorithm

1. Sort the array.
2. Traverse the sorted array.
3. For every adjacent pair:

   * Compute the difference.
   * If the difference is greater than `1`, generate all missing numbers between them.
4. Return the result.

## Time Complexity

* Sorting: **O(n log n)**
* Traversing the array: **O(n)**
* Generating missing elements: **O(k)**

Overall:

```text
O(n log n + k)
```

where `k` is the number of missing elements returned.

## Space Complexity

* Auxiliary Space: **O(1)**
* Output Space: **O(k)**

## What I Learned

* Sorting can transform a global search problem into a local comparison problem.
* Comparing only adjacent elements is sufficient because every missing number must lie between two consecutive sorted values.
* When a problem asks for all missing elements, the running time naturally depends on the size of the output (`k`). This is an example of **output-sensitive complexity**.
* Distinguish between **auxiliary space** (extra memory used by the algorithm) and **output space** (memory required to store the returned answer).


