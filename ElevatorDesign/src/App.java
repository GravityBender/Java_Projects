public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the Lift!");

        Thread requestListenerThread = new Thread(new RequestFloors(), "RequestListenerThread");

        Thread requestProcessorThread = new Thread(new RequestProcess(), "RequestProcessorThread");

        Elevator.getInstance().setRequestProcessorThread(requestProcessorThread);
        requestListenerThread.start();
        requestProcessorThread.start();
    }

}
