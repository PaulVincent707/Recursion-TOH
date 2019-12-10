# Overview

Recursion is a process in which a function calls itself as a subroutine. This allows the function to be repeated several times, since it calls itself during its execution. Functions that incorporate recursion are called recursive functions.

Recursion is often seen as an efficient method of programming since it requires the least amount of code to perform the necessary functions. However, recursion must be incorporated carefully, since it can lead to an infinite loop if no condition is met that will terminate the function.

a classic example of recursion is the **Towers Of Hanoi** Puzzle. In the Towers of Hanoi puzzle, we have three poles labled A, B, C and n numbered discs that fit onto the poles. In the default state, the discs are placed initially stacked on one of the poles, in order from largest (disc n) at the bottom to smallest (disc 1) at the top. The task is to move all n discs to another pole.All

Movment / placement of discs must obey the following rules:
- Move only one disc at a time.
- Never place a larger disc on a smaller one.

### Recursion provides us with the perfect solution: 

First we move the top n−1 discs to an empty pole, then we move the largest disc to the other empty pole, then complete the job by moving the n−1 discs onto the largest disc folowing the rules stated above. The code outlined in this workshop implements this solution using a this recursive algorythm.

as the number of discks increase, the number of moves required to completed the puzzle grows exponentialy. If M(n) represents the number of moves required to Move n discs from one peg to another. Then, M(n) must satisfy the following equations:
- **M(n) = 2M(n−1)+1 when n>1**
- **M(1)=1 when there is 1 disc**

Thie equation is known as a recurrence relation. Given this, we can derive the mumber of moves required to solve the puzzle for any number of discs. For example, M(1) = 1, M(2) = 3, M(3) = 7, and M(4) = 15. In general,  this can be written as M(n) = 2n − 1. Assuming the monks move discs at the rate of one per second, it would take them more 5.8 billion centuries to solve the 64-disc problem.
