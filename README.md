# Recursive Methods in Java to solve Towers Of Hanoi Puzzle

## Overview

Recursion is a process in which a function calls itself as a subroutine. This allows the function to be repeated several times, since it calls itself during its execution. Functions that incorporate recursion are called recursive functions.

Recursion is often seen as an efficient method of programming since it requires the least amount of code to perform the necessary functions. However, recursion must be incorporated carefully, since it can lead to an infinite loop if no condition is met that will terminate the function.

A classic example of recursion is the **Towers Of Hanoi** Puzzle invented by the French mathematician Édouard Lucas in 1883. In the Towers of Hanoi puzzle, we have three rods labeled  Source, Dest, Aux and n numbered discs that fit onto the poles. In the default state, the discs are placed initially stacked on the Source rod, in order from largest (disc n) at the bottom to smallest (disc 1) at the top. The task is to move all n discs to the Dest rod.

Movement  / placement of discs must obey the following rules:
- Move only one disc at a time.
- Never place a larger disc on a smaller one.

### Recursion provides us with the perfect solution: 

The code outlined in this workshop implements this solution using a this recursive algorithm.
- Step 1 − Move n-1 disks from source to aux
- Step 2 − Move nth disk from source to dest
- Step 3 − Move n-1 disks from aux to dest

As the number of discs increase, the number of moves required to complete the puzzle grows exponentially. In general,  this can be written as **M = 2^n − 1** where n is the number of discs 

### Let's get started
In this workshop, we will build an animated representation of the **Towers of Hanoi**.  First, create a new blank project in your IDE of choice. Once you have your project, create a new java class file and name it **TOHA**

Now that you have your class file created let's start by importing in the libraries we will need for this solution.

Add the following lines of code to the top of your class file.
~~~~
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
~~~~
Next let's create the class header.  Notice that we will be extending the JPanel Java class

Add the following code to your class file
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

The next method we will add if used to display a single frame of our animation of disk movement.  here we are using the Java 
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

Next we add the method used to setup the current displayframe.

Add the following code to your file.

~~~~
void displayFrame(Graphics g,int x,int y)
    {
        try{
            displaySingleAnimationFrame(g);
            g.setColor(getColor(currentDisk));
            g.fillRect(x-15-currentDisk*5,y-10,35+currentDisk*10,10);
            Thread.sleep(numOfDisks*5); //Sudo Framerate

        }catch(InterruptedException ex){}
    }
~~~~
The last animation method we will add is the visualizer  method.  This method is responsible for the actual movement  of the discs
on screen by orchestrating the calls to the previous graphics methods displaySingleAnimationFrame and displayFrame.  This is also where we use out static variable Animate to control if we show the disc movement  or not.  1= yes 0 = no

Add the following code to your file.
~~~~
void visualizer(Graphics g) //graphics function to show the movement of the disk from peg to peg
    {
        currentMoveNum++;
        int x,y,delta,NegPos;
        currentDisk=pop(sourceRod);
        x=sourceRod*screenW;
        y=screenHeight-(visableDisks[sourceRod-1]+1)*10; //Moving up
        for(;y>rodHight-20;y-=8)
            if(Animate ==1) //controls animation
                displayFrame(g, x, y);

        y=rodHight-20;
        delta=destinationRod*screenW-x;
        NegPos=delta/Math.abs(delta);
        for(;Math.abs(x-destinationRod*screenW)>=24;x+=NegPos*12) //Moving Left to Right or Right to Left
            if(Animate ==1)
                displayFrame(g, x, y);
        x=destinationRod*screenW;
        for(;y<screenHeight-(visableDisks[destinationRod-1]+1)*10;y+=8) //Moving down over Peg
            if(Animate == 1)
                displayFrame(g, x, y);
        push(destinationRod,currentDisk);
        displaySingleAnimationFrame(g);


    }
~~~~

ok after all that, we have finally gotten to our recursive method solve.  solve takes in the graphics object that represents the visual state of the move, along with the current disc number, and rods to work with.  Notice that in the definition of this method, solve is calling itself twice.  This correlates  to the algorithm  we defined above for solving the puzzle.
- Step 1 − Move n-1 disks from source to aux
- Step 2 − Move nth disk from source to dest
- Step 3 − Move n-1 disks from aux to dest

Add the following code to your file.

~~~~
void solve(Graphics g, int diskNum, int rodA, int rodB, int rodC) throws InterruptedException //recursive call to solve puzzle
    {
        if(diskNum ==0)
        {}
        else
        {
            solve(g,diskNum-1,rodA,rodC,rodB); //Step 1

            if(Animate == 0) {
                displaySingleAnimationFrame(g);
                Thread.sleep(200);
            }
            sourceRod=rodA; //Step 2
            destinationRod=rodC; //Step 2
            visualizer(g); //Step 2
            if(Animate ==1)
                Thread.sleep(80);
            solve(g,diskNum-1,rodB,rodA,rodC); //Step3

        }

    }
~~~~
ok now all that is left is to add our Main application entry point.  This allows us to run the applet as an application.  Alternately  we could use a separate runner class to hold Main and import our TOHA class.  This way is fine for our workshop.  
Add the following code to your file.  After this final code, you should have fully functional **Towers Of Hanoi** Puzzle.
~~~~
public static void main(String[] args)
    {

        String s = "0";
        numOfDisks = 0;
        while(numOfDisks<1 | numOfDisks > 20) { //max disk number is 20
            s = JOptionPane.showInputDialog(null,"Enter number of disks(1-20)","Towers Of Hanoi",1); //Get disc number & validate input
            if(!s.isEmpty())
                numOfDisks = Integer.parseInt(s);
        }
        estimatedNumOfMoves = Math.pow(2.0,(double)numOfDisks)-1; 
        String[] choices = { "Yes", "No" };
        String Choice = (String) JOptionPane.showInputDialog(null, "Show Animation?", 
                "Towers Of Hanoi", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[1]);//Gets input for controlling animation
        if(Choice == "Yes")
            Animate = 1;
        else
            Animate = 0;
        TOHA puzzle=new TOHA(); //crate a new object of our class
        for(int i=0;i<3;i++) //Clearing all Pegs
            visableDisks[i]=-1;
        for(int i=numOfDisks;i>0;i--) //setup all defined disks on Peg 1
        {
            push(1,i);
        }

        JFrame fr=new JFrame("Towers Of Hanoi"); //Create JFrame to hold our Animation
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tells the runtime to exit if we close the Frame
        fr.setLayout(new BorderLayout());
        if(numOfDisks >= 10)
            fr.setSize(numOfDisks*70,numOfDisks*40); //Dynamic frame size based on number of discs
        else
            fr.setSize(700,400); //< 10 discs create static size otherwise the Frame is to small
        fr.add(puzzle);
        puzzle.setSize(fr.getSize());
        fr.setLocation(300,200);
        fr.setVisible(true);
        screenW=puzzle.getWidth()/4;
        screenHeight=puzzle.getHeight()-50;
        rodHight=screenHeight-numOfDisks*13;
        try{
            puzzle.solve(puzzle.getGraphics(),numOfDisks,1,2,3); //Start the solve 

        }catch(Exception ex){}
    }
~~~~


