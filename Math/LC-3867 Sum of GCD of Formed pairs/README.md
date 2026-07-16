# LC 3867 - Sum of GCD of Formed Pairs

## Problem Summary

Given an integer array `nums`:

1. Construct a `prefixGcd` array where

   ```
   prefixGcd[i] = gcd(nums[i], maximum element from index 0 to i)
   ```

2. Sort the `prefixGcd` array.

3. Pair the smallest element with the largest element, the second smallest with the second largest, and so on.

4. Compute the GCD of every formed pair and return their sum.

---

# Initial Thought Process

## Step 1 : Maintain the maximum element seen so far.

### Idea

Instead of finding the maximum element repeatedly for every prefix, maintain a running maximum.

### Java Syntax

```java
int currentMax = nums[0];

currentMax = Math.max(currentMax, nums[i]);
```

---

## Step 2 : Construct the `prefixGcd` array.

### Idea

For every index, compute the GCD of the current element and the maximum element seen so far.

### Java Syntax

```java
prefixGcd[i] = gcd(nums[i], currentMax);
```

---

## Step 3 : Sort the `prefixGcd` array.

### Idea

Sorting makes pairing the smallest and largest elements straightforward.

### Java Syntax

```java
Arrays.sort(prefixGcd);
```

---

## Step 4 : Use Two Pointers.

### Idea

Pair the smallest and largest unpaired elements.

### Java Syntax

```java
int low = 0;
int high = prefixGcd.length - 1;
```

---

## Step 5 : Compute the answer.

### Java Syntax

```java
while (low < high) {
    sum += gcd(prefixGcd[low], prefixGcd[high]);
    low++;
    high--;
}
```

---

# Mistakes I Made

## 1. Added the pair values instead of their GCD.

### Wrong Code

```java
sum += prefixGcd[low] + prefixGcd[high];
```

### Example

Sorted array

```
[2, 3, 6, 8]
```

Pair formed

```
(2, 8)
```

What I computed

```
2 + 8 = 10
```

What the problem expected

```
gcd(2, 8) = 2
```

---

## 2. Used `low <= high`.

### Wrong Code

```java
while (low <= high)
```

### Example

Sorted array

```
[2, 2, 6]
```

First iteration

```
Pair -> (2,6)
```

Pointers become

```
low = 1
high = 1
```

Since

```
1 <= 1
```

the loop executes again and incorrectly processes the middle element.

The problem statement clearly says:

> If the array length is odd, the middle element remains unpaired.

### Correct Code

```java
while (low < high)
```

---

## 3. Initially thought of recomputing the maximum.

### Example

```
nums = [5,1,8,6]
```

Instead of searching

```
5
1
8
6
```

for every index, maintain

```java
currentMax = Math.max(currentMax, nums[i]);
```

Traversal

```
5 → 5

1 → 5

8 → 8

6 → 8
```

---

# Key Observations

### Observation 1 : Running maximum is enough.

A single variable stores the maximum element seen so far.

Example

```
nums = [5,1,8,6]

currentMax

5 → 5
1 → 5
8 → 8
6 → 8
```

No need to scan the prefix repeatedly.

---

### Observation 2 : Sorting naturally suggests Two Pointers.

Before sorting

```
[5,1,8,2]
```

After sorting

```
[1,2,5,8]
```

Required pairs

```
(1,8)

(2,5)
```

The smallest and largest elements are always available using two pointers.

---

### Observation 3 : Euclidean GCD is a reusable helper.

Instead of finding factors manually,

```
gcd(24,18)

↓

gcd(18,6)

↓

gcd(6,0)

↓

Answer = 6
```

using

```
gcd(a,b) = gcd(b,a%b)
```

---

# Algorithm

1. Maintain a running maximum (`currentMax`).
2. Construct the `prefixGcd` array.
3. Sort the array.
4. Initialize two pointers.
5. Pair the smallest and largest elements.
6. Add the GCD of every pair to the answer.
7. Return the final sum.

---

# Time Complexity

```
Construct prefixGcd : O(n)

Sorting            : O(n log n)

Two Pointers       : O(n)

Overall            : O(n log n)
```

---

# Space Complexity

```
O(n)
```

for the `prefixGcd` array.

---

# What I Learned

## 1. When a problem asks for information about every prefix, first check if it can be maintained using a running variable.

**Example**

```
nums = [5,1,8,6]

currentMax

5 → 5
1 → 5
8 → 8
6 → 8
```

Instead of searching the entire prefix every time, updating one variable gives the answer in O(1) per index.

---

## 2. Whenever I see "pair the smallest with the largest", sorting + two pointers should immediately come to mind.

**Example**

Before sorting

```
[5,1,8,2]
```

After sorting

```
[1,2,5,8]
```

Pairs formed

```
(1,8)

(2,5)
```

---

## 3. Read every sentence of the problem carefully because small statements directly affect the implementation.

Problem statement

> "The middle element remains unpaired."

Effect on code

```java
while (low < high)
```

instead of

```java
while (low <= high)
```

Example

```
[2,2,6]

Pair

(2,6)

Middle element

2

Ignored
```

---

## 4. The Euclidean GCD algorithm is a standard helper that I can reuse in future problems.

Example

```
gcd(24,18)

↓

gcd(18,6)

↓

gcd(6,0)

↓

Answer = 6
```

Instead of computing factors manually, this runs much faster and should become part of my coding toolkit.
