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
 Now let's add in the default constructor for your Class TOHA. as you can see the default is setting up the structures for discs and towers. Add the following code to you file.
 ~~~~
  public TOHA()
    {
        diskStacks=new int[3][numOfDisks];
        visableDisks=new int[3];
    }
~~~~

Next we need to add in two methods, push and pop.  These will be used to add discs to a rod and pop a disc off a rod.

Add the following code to you file.

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
The next method we will add is our getColor method.  This method gets a color for a given disk number randomly

Add the following code to you file.

~~~~
Color getColor(int diskNum)
    {
        Random randomColorValue = new Random(diskNum); //use DiskNum as seed
        int r = randomColorValue.nextInt(255);
        int g = randomColorValue.nextInt(255);
        int b = randomColorValue.nextInt(255);

        //using the new Red Green Blue data values, generate disk color
        Color currentDisk = new Color(r,g,b);
        return currentDisk;
    }
~~~~

The next method we will add if used to display a single frame of our animation of disk movment.  here we are using the Java 
Graphics class to create the disks and rods using fillroundRect and fillRect methods.  Each disc is numbered using drawString.
Add the following code to your file.

~~~~
 void displaySingleAnimationFrame(Graphics g)
    {
        int j,i,disk;
        g.clearRect(0,0,getWidth(),getHeight());
            for (j = 1; j <= 3; j++) //Displays Three Stacks
            {
                //Stack X
                g.setColor(Color.BLACK); //Peg Color
                g.fillRoundRect(j * screenW, rodHight, 5, screenHeight - rodHight, 1, 1);
                g.drawString(Integer.toString(j),j * screenW,rodHight-20);
                //Displays the Disks on the Stacks
                for (i = 0; i <= visableDisks[j - 1]; i++) {
                    disk = diskStacks[j - 1][i];
                    g.setColor(getColor(disk));
                    g.fillRect(j * screenW - 15 - disk * 5, screenHeight - (i + 1) * 10, 35 + disk * 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(disk),j * screenW - disk, screenHeight - (i) * 10);
                }
            }
        g.drawString("Estimated Number Of Moves : " + Double.toString(estimatedNumOfMoves) ,screenW-150,screenHeight-300);
        g.drawString("Current Move Number       : " + Integer.toString(currentMoveNum) ,screenW-150,screenHeight-280);

        g.drawString("Move Disk " + Integer.toString(currentDisk) + " from Peg " +Integer.toString(sourceRod) + " to Peg "+ Integer.toString(destinationRod) ,screenW-50,screenHeight+30);
    }
~~~~

Next we add another method used to setup the current displayframe



