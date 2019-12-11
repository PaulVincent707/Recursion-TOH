import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TOHA extends JPanel{

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

    public TOHA()
    {
        diskStacks=new int[3][numOfDisks];
        visableDisks=new int[3];
    }

    static void push(int to, int diskNum) //Push operation to add a disc to a rod(tower)
    {
        diskStacks[to-1][++visableDisks[to-1]]=diskNum;
    }

    static int pop(int from) //pop operation to remove the top disc from the rod(Tower)
    {
        return(diskStacks[from-1][visableDisks[from-1]--]);
    }

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



    void displayFrame(Graphics g,int x,int y)
    {
        try{
            displaySingleAnimationFrame(g);
            g.setColor(getColor(currentDisk));
            g.fillRect(x-15-currentDisk*5,y-10,35+currentDisk*10,10);
            Thread.sleep(numOfDisks*5); //Sudo Framerate

        }catch(InterruptedException ex){}
    }

    void visulizer(Graphics g) //graphics function to show the movement of the disk from peg to peg
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

    void solve(Graphics g, int diskNum, int rodA, int rodB, int rodC) throws InterruptedException //recursive call to solve puzzle
    {
        if(diskNum ==0)
        {}
        else
        {
            solve(g,diskNum-1,rodA,rodC,rodB);

            if(Animate == 0) {
                displaySingleAnimationFrame(g);
                Thread.sleep(200);
            }
            sourceRod=rodA;
            destinationRod=rodC;
            visulizer(g);
            if(Animate ==1)
                Thread.sleep(80);
            solve(g,diskNum-1,rodB,rodA,rodC);

        }

    }

    public static void main(String[] args)
    {

        String s = "0";
        numOfDisks = 0;
        while(numOfDisks<1 | numOfDisks > 20) {
            s = JOptionPane.showInputDialog("Enter number of disks(1-20)");
            if(!s.isEmpty())
                numOfDisks = Integer.parseInt(s);
        }
        estimatedNumOfMoves = Math.pow(2.0,(double)numOfDisks)-1;
        String[] choices = { "Yes", "No" };
        String Choice = (String) JOptionPane.showInputDialog(null, "Show Animation?",
                "Towers Of Hanoi", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[1]);
        if(Choice == "Yes")
            Animate = 1;
        else
            Animate = 0;
        TOHA puzzle=new TOHA();
        for(int i=0;i<3;i++) //Clearing all Pegs
            visableDisks[i]=-1;
        for(int i=numOfDisks;i>0;i--) //setup all defined disks on Peg 1
        {
            push(1,i);
        }

        JFrame fr=new JFrame("Towers Of Hanoi");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new BorderLayout());
        if(numOfDisks >= 10)
            fr.setSize(numOfDisks*70,numOfDisks*40);
        else
            fr.setSize(700,400);
        fr.add(puzzle);
        puzzle.setSize(fr.getSize());
        fr.setLocation(300,200);
        fr.setVisible(true);
        screenW=puzzle.getWidth()/4;
        screenHeight=puzzle.getHeight()-50;
        rodHight=screenHeight-numOfDisks*13;
//start solving
        try{
            puzzle.solve(puzzle.getGraphics(),numOfDisks,1,2,3);

        }catch(Exception ex){}
    }

}
