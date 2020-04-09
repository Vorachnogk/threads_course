import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Thing {
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private Component canvas;
    private int type;
    private int subType;
    private int life = 0;
    private int death;
    private int x = 0;
    private int y = 0;
    private int speedX;
    private int speedY;

    public Thing(Component c) {
        this.canvas = c;
        death = new Random().nextInt(70000);
        type = new Random().nextInt(4);
        subType = new Random().nextInt(2);
        speedX = type + 1;
        speedY = type + 1;
        x = new Random().nextInt(this.canvas.getWidth());
        y = new Random().nextInt(this.canvas.getHeight());
    }

    public Thing(Component c, int type, int speed) {
        this.canvas = c;
        this.type = type;
        subType = 1;
        death = new Random().nextInt(80000);
        speedX = speed + 1;
        speedY = speed + 1;
        x = new Random().nextInt(this.canvas.getWidth());
        y = new Random().nextInt(this.canvas.getHeight());
    }

    public int getSubType() {
        return subType;
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

    public int getSpeedX() {
        return speedX;
    }

    public int getLife() {
        return life;
    }

    public int getDeath() {
        return death;
    }

    public void draw(Graphics2D g2) {
        switch (this.getType()) {
            case (0):
                g2.setColor(Color.RED);
                break;
            case (1):
                g2.setColor(Color.GREEN);
                break;
            case (2):
                g2.setColor(Color.YELLOW);
                break;
            case (3):
                g2.setColor(Color.MAGENTA);
                break;
        }
        g2.fill(new Rectangle2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += speedX;
        y += speedY;
        if (x < 0) {
            x = 0;
            speedX = -speedX;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            speedX = -speedX;
        }
        if (y < 0) {
            y = 0;
            speedY = -speedY;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            speedY = -speedY;
        }
        this.life++;
        this.canvas.repaint();
    }
}

