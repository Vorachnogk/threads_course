import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThingCanvas extends JPanel {
    protected ArrayList<Thing> things = new ArrayList<>();
    private int born = 0;
    private int kill = 0;
    private int dead = 0;

    public void add(Thing b) {
        this.things.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawString("Live:" + Integer.toString(things.size()), 20, 20);
        g.drawString("Born:" + Integer.toString(born), 20, 40);
        g.drawString("Killed:" + Integer.toString(kill), 20, 60);
        g.drawString("Died:" + Integer.toString(dead), 20, 80);
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
                        Thing t = new Thing(this, firstThing.getType(), firstThing.getSpeedX());
                        this.add(t);

                        ThingThread thread = new ThingThread(t);
                        thread.start();
                        born++;
                        System.out.println("Thread name = " + thread.getName());
                    } else if (firstThing.getType() > secondThing.getType() && firstThing.getSubType() > 0) {
                        things.remove(secondThing);
                        kill++;
                    } else if (firstThing.getType() < secondThing.getType() && secondThing.getSubType() > 0) {
                        things.remove(firstThing);
                        kill++;
                    }
                }
            }
        }
        for (Thing thing : things) {
            if (thing.getLife() >= thing.getDeath()) {
                things.remove(thing);
                dead++;
            }
        }
    }
}
