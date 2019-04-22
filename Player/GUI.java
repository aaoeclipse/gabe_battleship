import javax.swing.*;
import java.awt.*;

public class GUI{
    private JFrame f;
    private JPanel p;
    private JButton b1;
    private JLabel lab;

    public GUI(){
        start();
    }

    public void start(){
        f = new JFrame("BattleShip");
        f.setVisible(true);
        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        p.setBackground(Color.YELLOW);

        b1 = new JButton("test");
        lab = new JLabel("This is the label");
        p.add(b1);
        p.add(lab);

        f.add(p, BorderLayout.CENTER);
    }

    public static void main(String... args){
        GUI test = new GUI();
    }
        
}