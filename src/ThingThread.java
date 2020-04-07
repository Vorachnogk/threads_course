public class ThingThread extends Thread {
    private Thing t;

    public ThingThread(Thing thing) {
        t = thing;
    }

    @Override
    public void run() {
        try {
            while (!(t.getX() <= 180 && t.getX() >= 120 && t.getY() <= 180 && t.getY() >= 120)) {
                t.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
            t.move();
        } catch (
                InterruptedException ex) {
            System.out.println("Ups,i did it again...");
        }
    }
}
