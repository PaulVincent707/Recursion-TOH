# Overview

Recursion is a process in which a function calls itself as a subroutine. This allows the function to be repeated several times, since it calls itself during its execution. Functions that incorporate recursion are called recursive functions.

Recursion is often seen as an efficient method of programming since it requires the least amount of code to perform the necessary functions. However, recursion must be incorporated carefully, since it can lead to an infinite loop if no condition is met that will terminate the function.

a classic example of recursion is the **Towers Of Hanoi** Puzzle. In the Towers of Hanoi puzzle, we have three poles labled A, B, C and n numbered discs that fit onto the poles. In the default state, the discs are placed initially stacked on one of the poles, in order from largest (disc n) at the bottom to smallest (disc 1) at the top. The task is to move all n discs to another pole, while obeying the following rules:
Move only one disc at a time.
Never place a larger disc on a smaller one.
Recursion provides just the plan that we need: First we move the top n−1 discs to an empty pole, then we move the largest disc to the other empty pole, then complete the job by moving the n−1 discs onto the largest disc. TowersOfHanoi.java is a direct implementation of this strategy.
Exponential time. exponential growthLet T(n) be the number of move directives issued by TowersOfHanoi.java to move n discs from one peg to another. Then, T(n) must satisfy the following equation:
T(n)=2T(n−1)+1 for n>1, with T(1)=1
Such an equation is known in discrete mathematics as a recurrence relation. We can often use them to derive a closed-form expression for the quantity of interest. For example, T(1) = 1, T(2) = 3, T(3) = 7, and T(4) = 15. In general, T(n) = 2n − 1. Assuming the monks move discs at the rate of one per second, it would take them more 5.8 billion centuries to solve the 64-disc problem.
