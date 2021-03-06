import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllFrame extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    private ThingCanvas canvas;

    public AllFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("LIFE GAME");

        this.canvas = new ThingCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Begin");
        JButton buttonStop = new JButton("End");

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {

                    Thing t = new Thing(canvas);
                    canvas.add(t);

                    ThingThread thread = new ThingThread(t);
                    thread.start();
                    System.out.println("Thread name = " + thread.getName());
                }
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
