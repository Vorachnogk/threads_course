public class ThingThread extends Thread {
    private Thing t;

    public ThingThread(Thing thing) {
        t = thing;
    }

    @Override
    public void run() {
        try {
            while (true) {
                t.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (
                InterruptedException ex) {
            System.out.println("Ups,i did it again...");
        }
    }
}
