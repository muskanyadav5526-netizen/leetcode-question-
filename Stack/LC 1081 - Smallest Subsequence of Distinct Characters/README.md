# LC 1081 - Smallest Subsequence of Distinct Characters

## Problem Summary

Given a string `s`, return the **lexicographically smallest subsequence** that contains every distinct character exactly once.

**Example**

```
Input : "cbacdcbc"

Output : "acdb"
```

---

# Thought Process (Idea + Java Syntax)

### Idea

Maintain the current answer using a stack.

For every character:

```
Process Current Character
        ↓
Decrease Frequency
        ↓
Already Visited ?
      ↓ Yes
      Skip
      ↓ No
Pop all larger characters
(which appear later)
        ↓
Push Current Character
```

### Java Syntax

```java
Stack<Character> st = new Stack<>();
boolean[] visited = new boolean[26];
int[] freq = new int[26];
```

---

# Mistakes (Wrong Code + Correct Code + Example)

## Mistake 1 : peek() after pop()

❌ Wrong

```java
st.pop();
visited[st.peek()-'a']=false;
```

✅ Correct

```java
visited[st.peek()-'a']=false;
st.pop();
```

Example

```
Stack

a

↓

pop()

↓

(empty)

↓

peek() ❌ Runtime Error
```

---

## Mistake 2 : Frequency Meaning

I thought frequency should decrease **only while pushing**.

Actually,

> Frequency = characters still remaining to the right.

So decrease it immediately after processing the current character.

Example

```
a b a c
    ↑

Second 'a'

↓

freq[a]--
```

---

## Mistake 3 : Wrong While Loop

❌ Wrong

```java
while(!st.isEmpty()){
    pop();
}
```

Example

```
Stack

d
c
a

Current = b
```

Wrong

```
↓

(empty)
```

Correct

```
↓

a
```

Reason

Check after every pop

```
peek > current ?
```

---

## Mistake 4 : Current Character Lost

❌ Wrong

```java
if(condition){

    while(...){
        pop();
    }

}
else{
    push(current);
}
```

If `if` executes,

```
Current Character
```

never enters the stack.

✅ Correct

```
Pop Finished

↓

Push Current
```

---

## Mistake 5 : Special Handling of First Character

❌ Wrong

```java
st.push(s.charAt(0));

for(int i=1;i<n;i++)
```

✅ Better

```java
for(int i=0;i<n;i++)
```

Every character follows the same flow.

---

# Questions I Asked While Solving

### ❓ Why should frequency decrease even when I skip a character?

Because frequency represents **remaining occurrences**.

Even if the current character is skipped, this occurrence is already processed.

Example

```
a b a c
    ↑

Current

↓

freq--
```

---

### ❓ Why can't I do this?

```java
st.pop();
visited[st.peek()-'a']=false;
```

Because after popping,

```
Stack

a

↓

pop()

↓

(empty)
```

`peek()` causes `EmptyStackException`.

---

### ❓ Why should the popping condition be inside `while` instead of `if`?

Example

```
Stack

d
c
a

Current=b
```

Process

```
d>b ✔

↓

Pop

↓

c>b ✔

↓

Pop

↓

a>b ✖

↓

Stop
```

Condition changes after every pop.

---

### ❓ Why should I push the current character after popping?

Example

```
Stack

a
c

Current=b

↓

Pop c

↓

Stack

a

↓

Push b
```

Otherwise current character disappears.

---

### ❓ Why is `for(i=0)` cleaner?

No special handling.

Every character follows exactly the same algorithm.

Less code.
Less bugs.

---

# Key Observations

### Observation 1

Pop only when the character appears later.

```
Stack

a
d

Current=b

freq[d]>0

↓

Pop d
```

---

### Observation 2

Never pop the last occurrence.

```
freq[d]=0

↓

Don't Pop
```

---

### Observation 3

One character can pop multiple characters.

```
Stack

a
f
e
d

Current=b

↓

Pop d

↓

Pop e

↓

Pop f

↓

Push b
```

---

# Algorithm

```
Count Frequency

↓

Traverse String

↓

Decrease Frequency

↓

Already Visited ?

↓

Yes → Continue

↓

No

↓

Pop while

Stack Not Empty
AND
Top > Current
AND
Top appears later

↓

Push Current

↓

Mark Visited

↓

Convert Stack to String
```

---

# Complexities

**Time Complexity**

```
O(n)
```

Each character is pushed and popped at most once.

**Space Complexity**

```
O(26)
```

Stack, frequency array and visited array store at most one entry per lowercase letter.

---

# What I Learned

### 1. Frequency means "remaining characters"

```
Process Character

↓

freq--

↓

Make Decision
```

---

### 2. While condition changes after every pop

```
Top

d
c
a

Current=b

↓

d>b ✔

↓

c>b ✔

↓

a>b ✖
```

---

### 3. Stack always stores the current answer

Example

```
[]

↓

[c]

↓

[b]

↓

[a]

↓

[a,c]

↓

[a,c,d]

↓

[a,c,d,b]
```

---

### 4. Visited prevents duplicates

```
Stack

a
b
c

↓

Current=b

↓

Skip
```

---

### 5. Uniform code is easier to debug

Instead of

```
Handle First Character Separately
```

Use

```
for(i=0)
```

Every character follows the same flow.
