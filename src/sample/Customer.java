package sample;

public class Customer {

    private String id;
    private int numberOfOrders;

    private static int countAllOrders = 0;

    public Customer(String id, int numberOfOrders) {
        this.id = id;
        this.numberOfOrders = numberOfOrders;
        Customer.countAllOrders += numberOfOrders;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getNumberOfOrders() {
        return numberOfOrders;
    }
    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public static int getCountAllOrders() {
        return Customer.countAllOrders;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", numberOfOrders=" + numberOfOrders + "]";
    }





}

