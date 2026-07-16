# LC 3658 - GCD of Odd and Even Sums

## Problem Summary

Given an integer `n`, find the GCD of:

* Sum of first `n` odd numbers.
* Sum of first `n` even numbers.

---

# Initial Thought Process

### Step 1 : Use the standard mathematical formulas.

```
Sum of first n odd numbers  = n²

Sum of first n even numbers = n(n+1)
```

---

### Step 2 : Substitute the formulas.

```
gcd(n², n(n+1))
```

---

### Step 3 : Factor out the common term.

```
gcd(n², n(n+1))

= n × gcd(n, n+1)
```

---

### Step 4 : Apply the property.

```
gcd(n, n+1) = 1
```

Therefore,

```
Answer = n
```

---

# Algorithm

1. Use the formulas for the sums.
2. Simplify the GCD expression.
3. Return `n`.

---

# Time Complexity

```
O(1)
```

---

# Space Complexity

```
O(1)
```

---

# What I Learned

* Before writing loops in a Math problem, check if a standard mathematical formula already exists.
* Simplifying an expression is often easier than computing large values first.
* Factoring common terms from a GCD expression can reveal hidden properties.

