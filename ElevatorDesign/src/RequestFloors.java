import java.util.Scanner;

public class RequestFloors implements Runnable{

    @Override
    public void run() {
        while(true){
            Scanner scanner = new Scanner(System.in);
            String floorNumber = scanner.nextLine();

            if(isValidFloorNumber(floorNumber)){
                System.out.println("User Pressed: "+floorNumber);
                Elevator elevator = Elevator.getInstance();
                elevator.addFloor(Integer.parseInt(floorNumber));
            }else{
                System.out.println("Floor " + floorNumber+" Invalid!");
            }
        }
    }

    private boolean isValidFloorNumber(String floorNumber){
        if(floorNumber!=null&&floorNumber.matches("\\d{1,2}")){
            return true;
        }else{
            return false;
        }
    }
}
