public class RequestProcess implements Runnable {

    @Override
    public void run() {
        while (true) {
            Elevator elevator = Elevator.getInstance();
            int floor = elevator.nextFloor();
            int currentFloor = elevator.getCurrentFloor();

            try {
                if (floor >= 0) {
                    if (currentFloor > floor) {
                        while (currentFloor > floor) {
                            elevator.setCurrentFloor(--currentFloor);
                        }
                    } else {
                        while (currentFloor < floor) {
                            elevator.setCurrentFloor(++currentFloor);
                        }
                    }
                    System.out.println("Welcome to Floor: " + elevator.getCurrentFloor());
                }
            }catch (Exception e){
                if (elevator.getCurrentFloor() != floor) {
                    elevator.getRequestSet().add(floor);
                }
            }
        }
    }
}
