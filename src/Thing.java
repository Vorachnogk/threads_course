import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Thing {
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private Component canvas;
    private int type;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    public Thing(Component c) {
        this.canvas = c;
        type = new Random().nextInt(4);
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public Thing(Component c, int type) {
        this.canvas = c;
        this.type = type;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void draw(Graphics2D g2) {
        switch (this.getType()) {
            case (1):
                g2.setColor(Color.RED);
                break;
            case (2):
                g2.setColor(Color.GREEN);
                break;
            case (3):
                g2.setColor(Color.YELLOW);
                break;
            case (4):
                g2.setColor(Color.MAGENTA);
                break;
        }
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }
}

