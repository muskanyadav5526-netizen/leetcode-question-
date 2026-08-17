# LC 125 - Valid Palindrome

---

## Problem Summary

Given a string `s`, determine whether it is a palindrome after:

* Converting all uppercase letters to lowercase.
* Ignoring all non-alphanumeric characters such as spaces, commas, `:`, `-`, etc.

A palindrome reads the same from left to right and right to left.

For example:

```text
"A man, a plan, a canal: Panama"
```

becomes:

```text
"amanaplanacanalpanama"
```

which is a palindrome.

---

## Constraints

* `s` contains English letters, digits, and non-alphanumeric characters.
* An empty string is considered a palindrome.
* Only letters and digits should participate in the comparison.
* Letter comparison is case-insensitive.

---

# Approach Evolution

## Attempt 1

### Idea

First, create a new string containing only alphanumeric characters and convert all letters to lowercase.

Example:

```text
Original:
"A man, a plan: Panama!"

After cleaning:
"amanaplanpanama"
```

I used `StringBuilder` to construct this cleaned string.

Then I used two pointers:

```text
low →                      ← high
a m a n a p l a n a p a m a
```

and compared characters from both ends.

### Java Syntax Used

```java
StringBuilder str = new StringBuilder();

for (char ch : s.toCharArray()) {
    if (Character.isLetterOrDigit(ch)) {
        str.append(Character.toLowerCase(ch));
    }
}

String string = str.toString();
```

### Time Complexity

```text
O(n)
```

The `for` loop takes `O(n)` and the two-pointer traversal also takes `O(n)`.

Therefore:

```text
O(n) + O(n) = O(n)
```

### Space Complexity

```text
O(n)
```

because the cleaned string is stored in `StringBuilder`.

### Why I improved it

I realized that I was already using two pointers, but I was first creating another string unnecessarily.

The question became:

> Can I skip the unwanted characters directly in the original string instead of creating a cleaned string?

That leads to the O(1) space solution.

---

## Attempt 2

### Idea

Use two pointers directly on the original string:

```text
low →                         ← high
"A man, a plan, a canal: Panama"
```

Instead of removing non-alphanumeric characters, simply move the pointer over them.

For example:

```text
s = "a,b"
```

The comma should not be compared.

So:

```text
low → a
high → b
```

Compare `a` and `b`.

For a non-alphanumeric character:

```java
while (low <= high && !Character.isLetterOrDigit(s.charAt(low))) {
    low++;
}
```

and similarly from the right:

```java
while (low <= high && !Character.isLetterOrDigit(s.charAt(high))) {
    high--;
}
```

---

## Mistake During Attempt 2 - Runtime Error

### Wrong Code

Initially I changed the `if` conditions to `while`:

```java
while (!Character.isLetterOrDigit(s.charAt(low))) {
    low++;
}

while (!Character.isLetterOrDigit(s.charAt(high))) {
    high--;
}
```

### Why it fails

I forgot that the pointer itself can move outside the valid string range.

Example:

```text
s = "!!!"
```

Initially:

```text
low = 0
```

Since every character is non-alphanumeric:

```text
low = 0
low = 1
low = 2
low = 3
```

Now `low == s.length()`.

Trying:

```java
s.charAt(low)
```

means:

```java
s.charAt(3)
```

for a string of length `3`.

That causes:

```text
StringIndexOutOfBoundsException
```

The same problem can happen with `high`.

For example, if `high` keeps decrementing:

```text
high = 2
high = 1
high = 0
high = -1
```

then:

```java
s.charAt(high)
```

would try to access:

```java
s.charAt(-1)
```

which is invalid.

---

## Fix

Add the boundary condition **before** accessing `charAt()`:

```java
while (low <= high && !Character.isLetterOrDigit(s.charAt(low))) {
    low++;
}
```

and:

```java
while (low <= high && !Character.isLetterOrDigit(s.charAt(high))) {
    high--;
}
```

The order is important because Java evaluates `&&` from left to right.

If:

```text
low > high
```

then Java does not evaluate:

```java
s.charAt(low)
```

This prevents the out-of-bounds access.

---

## Mistake - Pointer Crossing After Skipping

After skipping non-alphanumeric characters, the two pointers can cross.

Example:

```text
s = ".,!"
```

Eventually:

```text
low > high
```

There is nothing left to compare.

So I added:

```java
if (low > high) {
    return true;
}
```

This correctly handles strings containing no alphanumeric characters.

---

# Final Insight

The important improvement was:

> **I don't need to remove unwanted characters. I can simply make the two pointers skip them.**

Instead of:

```text
Original String
      ↓
Create cleaned String
      ↓
Two pointers
```

I can do:

```text
Original String
      ↓
Two pointers
      ↓
Skip irrelevant characters
      ↓
Compare relevant characters
```

This removes the `O(n)` extra space.

---

# Key Observations

## 1. `Character` is a class

These:

```java
Character.isLetterOrDigit(ch)
Character.toLowerCase(ch)
```

are methods belonging to the `Character` class.

The structure is:

```text
Class.method()
```

It is **not** a method inside another method.

For example:

```java
Character.isLetterOrDigit(ch)
```

means:

> Call the `isLetterOrDigit()` method of the `Character` class.

---

## 2. `isLetterOrDigit()` filters both letters and numbers

```java
Character.isLetterOrDigit(ch)
```

returns `true` for:

```text
'a' → true
'Z' → true
'5' → true
```

and `false` for:

```text
',' → false
'-' → false
' ' → false
':' → false
```

Therefore it is exactly what this problem requires.

---

## 3. `toLowerCase()` normalizes the comparison

Instead of worrying about:

```text
'A' vs 'a'
'B' vs 'b'
```

we convert both characters:

```java
Character.toLowerCase(s.charAt(low))
```

and:

```java
Character.toLowerCase(s.charAt(high))
```

Example:

```text
'A' → 'a'
'a' → 'a'
```

so they compare equal.

---

## 4. `low <= high` protects the pointers

The condition:

```java
low <= high
```

has two purposes.

It controls the main loop:

```java
while (low <= high)
```

and also protects the inner skipping loops:

```java
while (low <= high && ...)
```

The second condition is especially important because `low` and `high` are actively moving inside those loops.

---

## 5. Two pointers themselves require O(1) space

These variables:

```java
int low = 0;
int high = n - 1;
```

only store two integers.

Therefore:

```text
Space = O(1)
```

The original `StringBuilder` solution used `O(n)` because it created another string.

---

# Algorithm

1. Set `low = 0` and `high = s.length() - 1`.
2. Move `low` forward while its character is non-alphanumeric.
3. Move `high` backward while its character is non-alphanumeric.
4. If `low > high`, all remaining characters have been skipped, so return `true`.
5. Convert both characters to lowercase.
6. If they are different, return `false`.
7. Otherwise move both pointers inward.
8. Continue until the pointers meet/cross.
9. If no mismatch is found, return `true`.

---

# Dry Run

### Input

```text
"A man, a plan, a canal: Panama"
```

Relevant characters are:

```text
a m a n a p l a n a c a n a l p a n a m a
```

The pointers compare:

```text
a ↔ a
m ↔ m
a ↔ a
n ↔ n
a ↔ a
p ↔ p
l ↔ l
...
```

All corresponding characters match.

Eventually:

```text
low >= high
```

Therefore:

```text
Output:
true
```

---

### Edge Case

Input:

```text
".,!?"
```

Every character is non-alphanumeric.

The pointers keep skipping:

```text
low → → → 
          ← ← ← high
```

Eventually:

```text
low > high
```

There is nothing to compare.

Therefore:

```text
Output:
true
```

---

# Time Complexity

```text
O(n)
```

Although there are nested `while` loops, each pointer only moves forward/backward through the string.

`low` never moves backward and `high` never moves forward.

Therefore the total number of pointer movements is at most proportional to `n`.

---

# Space Complexity

```text
O(1)
```

No additional string or array is created.

Only a few variables such as:

```java
int low
int high
int n
```

are used.

---

# Mistakes I Made

* Initially created a cleaned string using `StringBuilder`, which made the solution `O(n)` in extra space.
* Initially thought I needed to remove non-alphanumeric characters before using two pointers.
* Changed the skipping logic from `if` to `while` but initially forgot pointer boundary checks.
* Got a runtime error because `low` could become equal to `s.length()` and `high` could become `-1`.
* Learned that `charAt(low/high)` must only be called after confirming the pointer is still valid.
* Initially didn't account for the case where all characters are non-alphanumeric and the pointers cross.
* Added `low <= high` to the inner loops and `low > high` after skipping.

---

# What I Learned

## 1. Don't modify data if you can simply ignore irrelevant elements

Initially:

```text
Original → Cleaned String → Compare
```

Better:

```text
Original → Skip irrelevant characters → Compare
```

Example:

```text
"a,b,c"
```

Instead of creating:

```text
"abc"
```

the pointers can simply skip `,`.

This reduced the extra space from:

```text
O(n) → O(1)
```

---

## 2. A while loop that moves a pointer needs a boundary condition

Consider:

```text
"!!!"
```

If we write:

```java
while (!Character.isLetterOrDigit(s.charAt(low))) {
    low++;
}
```

then `low` eventually becomes `3`.

But valid indices are only:

```text
0, 1, 2
```

Therefore:

```java
while (low <= high && ...)
```

protects the `charAt()` access.

---

## 3. `&&` short-circuiting can protect an array/string access

This:

```java
low <= high && !Character.isLetterOrDigit(s.charAt(low))
```

is evaluated from left to right.

If:

```text
low > high
```

the second condition is never evaluated.

Therefore:

```java
s.charAt(low)
```

is not executed.

This is an important Java safety pattern when accessing arrays or strings inside conditions.

---

## 4. Two pointers can work directly on a string

The two-pointer pattern isn't limited to arrays.

For this problem:

```text
low →                       ← high
A man, a plan, a canal...
```

Each pointer can move independently until it reaches a character relevant to the comparison.

The key is:

```text
Move → skip → compare → move inward
```

---

## 5. The real purpose of preprocessing is sometimes unnecessary

The first approach taught me that preprocessing can make a problem easier to visualize:

```text
"A man, a plan..."
        ↓
"amanaplan..."
```

But preprocessing is not always necessary.

Before creating another data structure, ask:

> **Can I handle the unwanted elements while traversing the original data?**

Here, the answer was yes.

---

## 6. Complexity is about total work, not simply the number of loops

There is a `for` loop in the first solution and a `while` loop for comparison.

That does **not** mean the complexity becomes `O(2n)` in the final notation.

```text
O(n) + O(n)
= O(2n)
= O(n)
```

Constants are ignored in Big-O notation.

In the final two-pointer solution, even though there are inner `while` loops, the pointers collectively traverse the string only a constant number of times.

Therefore:

```text
Time = O(n)
Space = O(1)
```

---

# Similar Problems

* LC 344 - Reverse String
* LC 167 - Two Sum II - Input Array Is Sorted
* LC 11 - Container With Most Water
* LC 392 - Is Subsequence

---

# Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length();
        int low = 0;
        int high = n - 1;
        boolean result = true;

        if (n == 0) {
            return true;
        }

        while (low <= high) {

            while (low <= high && !Character.isLetterOrDigit(s.charAt(low))) {
                low++;
            }

            while (low <= high && !Character.isLetterOrDigit(s.charAt(high))) {
                high--;
            }

            if (low > high) {
                return true;
            }

            if (Character.toLowerCase(s.charAt(low)) !=
                Character.toLowerCase(s.charAt(high))) {
                result = false;
                break;
            } else {
                low++;
                high--;
            }
        }

        return result;
    }
}
```

### Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```
