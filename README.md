# Overview

Recursion is a process in which a function calls itself as a subroutine. This allows the function to be repeated several times, since it calls itself during its execution. Functions that incorporate recursion are called recursive functions.

Recursion is often seen as an efficient method of programming since it requires the least amount of code to perform the necessary functions. However, recursion must be incorporated carefully, since it can lead to an infinite loop if no condition is met that will terminate the function.

A classic example of recursion is the **Towers Of Hanoi** Puzzle. In the Towers of Hanoi puzzle, we have three rods labled Source, Dest, Aux and n numbered discs that fit onto the poles. In the default state, the discs are placed initially stacked on the Source rod, in order from largest (disc n) at the bottom to smallest (disc 1) at the top. The task is to move all n discs to the Dest pole.

Movment / placement of discs must obey the following rules:
- Move only one disc at a time.
- Never place a larger disc on a smaller one.

### Recursion provides us with the perfect solution: 

The code outlined in this workshop implements this solution using a this recursive algorythm.
- Step 1 − Move n-1 disks from source to aux
- Step 2 − Move nth disk from source to dest
- Step 3 − Move n-1 disks from aux to dest

As the number of discs increase, the number of moves required to complete the puzzle grows exponentialy. In general,  this can be written as **M = 2^n − 1** where n is the number of discs 

### Let's get started
In this workshop, we will build an animated representation of the Towers of Hanoi.  First, create a new blank project in your IDE of choice. Once you have your project, create a new java class file and name it **TOHA**

Now that you have your class file created let's start by importing in the libraries we will need for this solution.
add the following lines of code to the top of your class file.
~~~~
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
~~~~
Next lets create the class header.  Notice that we will be extending the JPanel Java class
addd the following code to your class file
~~~~
public class TOHA extends JPanel{
}
~~~~
Next we need to add in some static variables that our methods will need.
add the following **inside the Class Definition {}**
~~~~
static int diskStacks[][]; //we need three stacks of disks Multidimensional Array will have 3 columns ans N rows
static int visableDisks[];   //This will keep track of the top of each stack
static int sourceRod,destinationRod; //Tracks From and To Stack number 1-3
static int currentDisk;//number of disk being moved (1 to n)
static int numOfDisks; //Number of Disks on Stack
static int screenW,screenHeight; //Dimension values based upon number of disks chosen
static int rodHight; //how tall should the rod be
static int Animate; //controls if the animation will run or not
static double estimatedNumOfMoves; //calculated move count 
static int currentMoveNum;//holds current move count
~~~~ 
 Now let's addin the default constructor for your Class TOHA. as you can see the default is setting up the structures for discs and towers.
 ~~~~
  public TOHA()
    {
        diskStacks=new int[3][numOfDisks];
        visableDisks=new int[3];
    }
~~~~

Next we need to ass in two methods, push and pop.  these will be used to add dics to a rod and pop a disc off a rod.

~~~~
    static void push(int to, int diskNum) //Push operation to add a disc to a rod(tower)
    {
        diskStacks[to-1][++visableDisks[to-1]]=diskNum;
    }

    static int pop(int from) //pop operation to remove the top disc from the rod(Tower)
    {
        return(diskStacks[from-1][visableDisks[from-1]--]);
    }
~~~~



