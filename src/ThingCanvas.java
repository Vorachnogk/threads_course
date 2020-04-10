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
        runHunt();
        createDestroy();
    }

    public void runHunt() {
        for (Thing firstThing : things) {
            for (Thing secondThing : things) {
                if (firstThing.getType() < secondThing.getType() && secondThing.getSubType() > 0 && firstThing != secondThing
                        && firstThing.getX() < (secondThing.getX() + 2 * secondThing.getSize())
                        && firstThing.getY() < (secondThing.getY() + 2 * secondThing.getSize())) {
                    firstThing.setSpeedX(-firstThing.getSpeedX());
                    firstThing.setSpeedY(-firstThing.getSpeedX());
                    if (secondThing.getX() > firstThing.getX()) {
                        if (secondThing.getY() > firstThing.getY()) {
                            secondThing.setSpeedX(-Math.abs(secondThing.getSpeedX()));
                            secondThing.setSpeedY(-Math.abs(secondThing.getSpeedX()));
                        } else {
                            secondThing.setSpeedX(-Math.abs(secondThing.getSpeedX()));
                            secondThing.setSpeedY(Math.abs(secondThing.getSpeedX()));
                        }
                    } else {
                        if (secondThing.getY() > firstThing.getY()) {
                            secondThing.setSpeedX(Math.abs(secondThing.getSpeedX()));
                            secondThing.setSpeedY(-Math.abs(secondThing.getSpeedX()));
                        } else {
                            secondThing.setSpeedX(Math.abs(secondThing.getSpeedX()));
                            secondThing.setSpeedY(Math.abs(secondThing.getSpeedX()));
                        }
                    }
                }
            }
        }
    }

    public void create(Thing first, Thing second) {
        Thing t = new Thing(this, first.getType(), second.getSpeedX());
        this.add(t);

        ThingThread thread = new ThingThread(t);
        thread.start();
        born++;
        System.out.println("Thread name = " + thread.getName());
    }

    public void createDestroy() {
        for (Thing firstThing : things) {
            for (Thing secondThing : things) {
                if (firstThing.getX() == secondThing.getX() && firstThing.getY() == secondThing.getY() && firstThing != secondThing
                        && secondThing.getX() <= (firstThing.getX() + firstThing.getSize())
                        && secondThing.getY() <= (firstThing.getY() + firstThing.getSize())
                        && firstThing.getX() <= (secondThing.getX() + secondThing.getSize())
                        && firstThing.getY() <= (secondThing.getY() + secondThing.getSize())) {
                    if (firstThing.getType() == secondThing.getType()) {
                        if (firstThing.getType() == 0) {
                            for (int i = 0; i < 5; i++) {
                                create(firstThing, secondThing);
                            }
                        } else {
                            create(firstThing, secondThing);
                        }
                    } else if (firstThing.getType() < secondThing.getType() && secondThing.getSubType() > 0) {
                        things.remove(firstThing);
                        kill++;
                    } else if (secondThing.getType() < firstThing.getType() && firstThing.getSubType() > 0) {
                        things.remove(secondThing);
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
