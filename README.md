# Overview

Recursion is a process in which a function calls itself as a subroutine. This allows the function to be repeated several times, since it calls itself during its execution. Functions that incorporate recursion are called recursive functions.

Recursion is often seen as an efficient method of programming since it requires the least amount of code to perform the necessary functions. However, recursion must be incorporated carefully, since it can lead to an infinite loop if no condition is met that will terminate the function.

a classic example of recursion is the **Towers Of Hanoi** Puzzle. In the Towers of Hanoi puzzle, we have three rods labled Source, Dest, Aux and n numbered discs that fit onto the poles. In the default state, the discs are placed initially stacked on the Source rod, in order from largest (disc n) at the bottom to smallest (disc 1) at the top. The task is to move all n discs to the Dest pole.

Movment / placement of discs must obey the following rules:
- Move only one disc at a time.
- Never place a larger disc on a smaller one.

### Recursion provides us with the perfect solution: 

The code outlined in this workshop implements this solution using a this recursive algorythm.
- Step 1 − Move n-1 disks from source to aux
- Step 2 − Move nth disk from source to dest
- Step 3 − Move n-1 disks from aux to dest

As the number of discs increase, the number of moves required to complete the puzzle grows exponentialy. In general,  this can be written as **M = 2^n − 1** 
