# LC 1260 - Shift 2D Grid

## Problem Summary

Given an `m × n` grid and an integer `k`, shift the grid `k` times.

One shift means:

* Every element moves **one position forward** in row-major order.
* The last element moves to the first position.

Return the shifted grid as a `List<List<Integer>>`.

---

# Thought Process (Idea + Java Syntax)

## Initial Understanding

Initially, I thought the problem was asking me to shift rows or columns independently.

After tracing the examples, I realized that neither rows nor columns are shifted individually. Instead, the entire grid should be viewed as a **single 1D sequence**.

### Example

```
Grid

1 2 3
4 5 6
```

Think of it as

```
1 → 2 → 3 → 4 → 5 → 6
```

After one shift,

```
6 → 1 → 2 → 3 → 4 → 5
```

The matrix is simply another way of displaying this linear sequence.

---

## Core Idea

For every element,

```
(row, col)
      ↓
Convert to 1D Index
      ↓
Shift by k
      ↓
Convert back to 2D
      ↓
Store in new matrix
```

---

## Four Important Formulas

### Formula 1 : Convert 2D → 1D

```java
int index = row * n + col;
```

Example

```
1 2 3
4 5 6

row = 1
col = 2

index = 1 * 3 + 2 = 5
```

---

### Formula 2 : Shift the Index

```java
int newIndex = (index + k) % totalElements;
```

Example

```
Current Index = 5
k = 2
Total Elements = 6

newIndex = (5 + 2) % 6
         = 1
```

Modulo automatically wraps the index back to the beginning.

---

### Formula 3 : Convert 1D → Row

```java
int newRow = newIndex / n;
```

Example

```
newIndex = 1

newRow = 1 / 3 = 0
```

---

### Formula 4 : Convert 1D → Column

```java
int newCol = newIndex % n;
```

Example

```
newIndex = 1

newCol = 1 % 3 = 1
```

---

## Java Syntax Used

### Create Temporary Matrix

```java
int[][] temp = new int[m][n];
```

### Place Every Element

```java
temp[newRow][newCol] = grid[row][col];
```

### Convert `temp[][]` into `List<List<Integer>>`

```java
List<List<Integer>> result = new ArrayList<>();

for (int i = 0; i < m; i++) {
    List<Integer> rowList = new ArrayList<>();

    for (int j = 0; j < n; j++) {
        rowList.add(temp[i][j]);
    }

    result.add(rowList);
}
```

---

# Mistakes (Wrong Code + Correct Code + Example)

## Mistake 1 : Thinking rows or columns are shifted

### Wrong Thinking

```
Shift every row

OR

Shift every column
```

### Correct Thinking

Treat the entire matrix as one linear sequence.

```
1 → 2 → 3 → 4 → 5 → 6
```

Every element moves `k` positions in this sequence.

---

## Mistake 2 : Confusing movement with destination

Initially I thought,

```
Current Index = 5

Answer = 1

Shouldn't I add 1?
```

Wrong.

`k` represents the **distance travelled**, not the destination.

Correct trace:

```
Current Index = 5

Move 2 positions

5 → 0 → 1
```

```
newIndex = (5 + 2) % 6 = 1
```

---

## Mistake 3 : Writing modulo incorrectly

Wrong

```java
(index + k) % m * n
```

Java evaluates it as

```java
((index + k) % m) * n
```

Correct

```java
(index + k) % (m * n)
```

or

```java
(index + k) % totalElements
```

---

## Mistake 4 : Overwriting the original grid

Wrong

```java
grid[newRow][newCol] = grid[row][col];
```

This destroys values that are still needed.

Correct

```java
temp[newRow][newCol] = grid[row][col];
```

Store everything in `temp`, then convert it into the required `List<List<Integer>>`.

---

# Key Observations (with Examples)

## Observation 1

The matrix is actually a **1D circular array**.

```
Grid

1 2 3
4 5 6

↓

1 → 2 → 3 → 4 → 5 → 6
```

---

## Observation 2

Every element has exactly one destination.

```
(row, col)

↓

index

↓

newIndex

↓

(newRow, newCol)
```

---

## Observation 3

Modulo handles wrapping automatically.

```
Indices

0 1 2 3 4 5

Current = 5

Shift = 2

5 → 0 → 1

(5 + 2) % 6 = 1
```

---

## Observation 4

A temporary matrix prevents overwriting.

```
Original Grid
      │
      ▼
Compute New Position
      │
      ▼
Store in temp[][]
      │
      ▼
Convert temp[][] → List<List<Integer>>
```

---

# Algorithm

1. Find `m`, `n`, and `totalElements`.
2. Create a temporary matrix `temp`.
3. Traverse every cell of the original grid.
4. Convert `(row, col)` to a 1D index.
5. Compute the shifted index using modulo.
6. Convert the new index back to `(newRow, newCol)`.
7. Store the value in `temp[newRow][newCol]`.
8. Convert `temp[][]` into `List<List<Integer>>`.
9. Return the result.

---

# Complexities

### Time Complexity

```
O(m × n)
```

Every element is processed exactly once.

### Space Complexity

```
O(m × n)
```

An extra matrix is used to store the shifted grid.

---

# What I Learned

## Learning 1 : Many matrix problems become easier after converting them to 1D

Instead of solving directly in two dimensions,

```
(row, col)

↓

index

↓

Solve

↓

(row, col)
```

This technique appears in many matrix problems.

---

## Learning 2 : Movement and destination are different

```
Current Index = 5

k = 2

Move

5 → 0 → 1

Destination = 1
```

`k` tells us **how far to move**, not **where to end**.

---

## Learning 3 : Remember these four formulas

```java
index = row * n + col;

newIndex = (index + k) % totalElements;

newRow = newIndex / n;

newCol = newIndex % n;
```

These four formulas together solve the entire index mapping process.

---

## Learning 4 : Standard Java template to convert `int[][]` into `List<List<Integer>>`

```java
List<List<Integer>> result = new ArrayList<>();

for (int i = 0; i < m; i++) {
    List<Integer> rowList = new ArrayList<>();

    for (int j = 0; j < n; j++) {
        rowList.add(temp[i][j]);
    }

    result.add(rowList);
}
```

This is a reusable Java template whenever a problem asks for a `List<List<Integer>>` instead of a 2D array.

This version is ready to paste directly into your GitHub `README.md`.
