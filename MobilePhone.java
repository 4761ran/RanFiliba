public class MobilePhone {
    
    private String brand;
    private String model;
    private double price;
    //Constructor
    public MobilePhone (String brand, String model, double price){
        this.brand=brand;
        this.model=model;
        this.price=price;
    }
    //printing the details
    void printDetails(){
        System.out.println("The brand is: " + this.brand);
        System.out.println("The model is:" + this.model);
        System.out.println("The price is:" + this.price);

    }

    void discount(){
        this.price=0.9*this.price;
    }
}
// THATS RAN FILIBA
