import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class ThingCanvas extends JPanel {
    private ArrayList<Thing> things = new ArrayList<>();
    private static final int XSIZE = 30;
    private static final int YSIZE = 30;
    private final int x = 150;
    private final int y = 150;
    private int inc = 0;

    public void add(Thing b) {
        this.things.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        g.drawString(Integer.toString(inc), 100, 100);
        for (int i = 0; i < things.size(); i++) {
            Thing b = things.get(i);

            if (!(b.getX() <= 180 && b.getX() >= 120 && b.getY() <= 180 && b.getY() >= 120)) {
                b.draw(g2);
            } else {
                things.remove(i);
                ++inc;
            }
        }
    }
}
