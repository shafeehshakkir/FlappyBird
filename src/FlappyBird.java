import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //used for storing pipes
import java.util.Random; //used for placing pipes at random position
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardWidth=360;
    int boardHeight = 640;

    //images .. these 4 are variables which stores images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdx= boardWidth/8;
    int birdy= boardHeight/2;
    int birdWidth=34;
    int birdHeight=24;

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }



    class Bird {
        int x=birdx;
        int y=birdy;
        int width=birdWidth;
        int height=birdHeight;
        Image img;

        Bird(Image img) {
            this.img=img;
        }
    }
    //the pipes
    int pipeX=boardWidth;
    int pipeY=0;
    int pipeWidth=64;
    int pipeHeight=512;

    class Pipe{
        int x=pipeX;
        int y=pipeY;
        int width=pipeWidth;
        int height=pipeHeight;
        Image img;
        boolean passed=false;

        Pipe(Image img) {
            this.img=img;
        }
    }
    //game logic
    Bird bird;
    int velocityX=-4; //the pipes moves to the left
    int velocityY=0;
    int gravity=1;

    ArrayList<Pipe> pipes;
    Random random=new Random();

    Timer gameLoop;
    Timer placePipesTimer;



    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setFocusable(true);//checks if this is the class which gets the key events
        addKeyListener(this);

        //loading image...
        backgroundImg=new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg=new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg=new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        //bird
        bird =new Bird(birdImg);
        pipes= new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer=new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop=new Timer(1000/60,this);
        gameLoop.start();

    }

    public void placePipes() {
        int randomPipeY=(int) (pipeY-pipeHeight/4-Math.random()*(pipeHeight/2));
        int openingSpace=boardHeight/4;
        Pipe topPipe=new Pipe(topPipeImg);
        topPipe.y=randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe=new Pipe(bottomPipeImg);
        bottomPipe.y=topPipe.y + pipeHeight+openingSpace;
        pipes.add(bottomPipe)
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        //bird
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);
        //pipes
        for(int i=0;i<pipes.size();i++) {
            Pipe pipe=pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
    }
    public void move() {
        //bird
        /*adding gravity - concept
         * gravity refers to change in velocity overtime
         * simply add gravitational force to velocity y that is it becomes
         * -6+2,-4+2,-2+2...*/
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y=Math.max(bird.y,0);
        //pipes
        for (int i=0;i<pipes.size();i++) {
            Pipe pipe= pipes.get(i);
            pipe.x+=velocityX;

        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            velocityY=-9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}