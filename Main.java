public class Main {


    public static void main(String[] args) {

        //creating the object

        MobilePhone p = new MobilePhone("Apple","iphoneX",4500);
        MobilePhone p2 = new MobilePhone("Xioami","Redmi Note 11",1120);
        System.out.println("Phone 1:");
        p.printDetails();
        System.out.println("Phone 2:");
        p2.printDetails();

        System.out.println("After Discount:");
        p.discount();
        p2.discount();
        p.printDetails();
        p2.printDetails();


    }
}
// THATS RAN FILIBA
