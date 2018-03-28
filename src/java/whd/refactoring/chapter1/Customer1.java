package java.whd.refactoring.chapter1;

import java.util.Enumeration;
import java.util.Vector;

public class Customer1{  
    private String name;  
    private Vector _rentals=new Vector();  
    private Customer1(String name){  
        this.name=name;  
    }  
    public void addRental(Rental rental){  
        _rentals.addElement(rental);  
    }  
    public String getName(){  
        return name;  
    }  
    public String statement() {  
        double totalAmount = 0;  
        int frequentRentalPoints = 0;  
        Enumeration rentals = _rentals.elements();  
        String result = "Rental Record for" + getName() + "\n";  
        while (rentals.hasMoreElements()) {  
            double thisAmount = 0;  
            Rental each = (Rental) rentals.nextElement();  
  
            switch (each.getMovie().getPriceCode()) {  
                case Movie.REGULAR:  
                    thisAmount += 2;  
                    if (each.getDaysRented() > 2) {  
                        thisAmount += (each.getDaysRented() - 2) * 1.5;  
                    }  
                    break;  
                case Movie.NEW_RELEASE:  
                    thisAmount += each.getDaysRented() * 3;  
                    break;  
                case Movie.CHILDRENS:  
                    thisAmount += 1.5;  
                    if (each.getDaysRented() > 3) {  
                        thisAmount += (each.getDaysRented() - 3) * 1.5;  
                    }  
                    break;  
  
            }  
            //计算积分  
            frequentRentalPoints++;  
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {  
                frequentRentalPoints++;  
            }  
  
            result+="\t"+each.getMovie().getTitle()+"\t"+String.valueOf(thisAmount)+"\n";  
            totalAmount+=thisAmount;  
        }  
        result += "应收总金额是" + String.valueOf(totalAmount) + "\n";  
        result += "您赚取了" + String.valueOf(frequentRentalPoints) + "积分";  
        return result;  
  
    }  
}  
