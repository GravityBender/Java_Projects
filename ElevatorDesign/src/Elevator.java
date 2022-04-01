import java.util.TreeSet;

public class Elevator {

    private static Elevator elevator = new Elevator();;
    private TreeSet<Integer> requestSet;
    private int currentFloor;
    private Direction direction;
    private boolean exit;
    private Thread requestProcessThread;

    //  Constructor to initialise the Elevator object
    Elevator() {
        this.requestSet = new TreeSet<>();
        this.currentFloor = 0;
        this.direction = Direction.UP;
        this.exit = false;
    }

    //  Returns a singleton instance of the Elevator Class that would be
    //  used throughout the execution of the program.
    static Elevator getInstance() {
        if (elevator == null) {
            elevator = new Elevator();
        }
        return elevator;
    }

    public synchronized void addFloor(int floor) {
        requestSet.add(floor);

        if(requestProcessThread.getState() == Thread.State.WAITING){
            notify();
        }else{
            requestProcessThread.interrupt();
        }
    }

    public synchronized int nextFloor() {
        Integer floor = null;

        if (direction == Direction.UP) {
            if (requestSet.ceiling(currentFloor) != null) {
                floor = requestSet.ceiling(currentFloor);
            } else {
                floor = requestSet.floor(currentFloor);
            }
        } else {
            if (requestSet.floor(currentFloor) != null) {
                floor = requestSet.floor(currentFloor);
            } else {
                floor = requestSet.ceiling(currentFloor);
            }
        }

        if (floor == null) {
            try {
                System.out.println("Waiting at Floor :" + getCurrentFloor());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            requestSet.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    public void setCurrentFloor(int currentFloor) {
        if (this.currentFloor > currentFloor) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(Direction.UP);
        }

        this.currentFloor = currentFloor;
        System.out.println("Floor: " + currentFloor);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public Thread getRequestProcessorThread() {
        return requestProcessThread;
    }

    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessThread = requestProcessorThread;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setRequestSet(TreeSet<Integer> requestSet) {
        this.requestSet = requestSet;
    }

    public TreeSet<Integer> getRequestSet() {
        return this.requestSet;
    }


}
