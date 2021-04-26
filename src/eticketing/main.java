package eticketing;

public class main {
    public static void main(String[] args) {

        vectorWrapper.initVectors();

        for (event curentEvent:vectorWrapper.getEventVector()
             ) {
            System.out.println(curentEvent);
        }
        
        actions.decission();

    }
}
