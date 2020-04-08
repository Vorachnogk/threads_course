import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThingCanvas extends JPanel {
    protected ArrayList<Thing> things = new ArrayList<>();

    public void add(Thing b) {
        this.things.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawString(Integer.toString(things.size()), 100, 100);
        for (int i = 0; i < things.size(); i++) {
            Thing b = things.get(i);
            b.draw(g2);
        }
        createDestroy();
    }

    public void createDestroy() {
        for (Thing firstThing : things) {
            for (Thing secondThing : things) {
                if (firstThing.getX() == secondThing.getX() && firstThing.getY() == secondThing.getY() && firstThing != secondThing) {
                    if (firstThing.getType() == secondThing.getType()) {
                        Thing t = new Thing(this, firstThing.getType());
                        this.add(t);

                        ThingThread thread = new ThingThread(t);
                        thread.start();
                        System.out.println("Thread name = " + thread.getName());
                    } else if (firstThing.getType() > secondThing.getType()) {
                        things.remove(secondThing);
                    } else things.remove(firstThing);
                }
            }
        }
    }
}
