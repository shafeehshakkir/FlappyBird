import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight =640;

        JFrame frame = new JFrame("Flappy Bird");//This line creates a new JFrame object.
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jpanel section
        FlappyBird flappyBird= new FlappyBird();
        frame.add(flappyBird);
        frame.pack();//this line will size the jpanel to 360 and 640 without including the title bar
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}