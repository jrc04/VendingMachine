
/**
 * Write a description of class Transaction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Transaction
{
    private boolean success;
    private double payment;
    private PopCan item;
    private double price;
    public Transaction(boolean success, PopCan item, double payment)
    {
        this.success = success;
        this.item = item;
        this.price = item.getPrice();
        this.payment = payment;
    }
    public boolean getSuccess()
    {
        return success;
    }
    public double getPayment()
    {
        return payment;
    }
    public PopCan getItem()
    {
        return item;
    }
    public double getPrice()
    {
        return price;
    }
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    public void setPayment(double payment)
    {
        this.payment = payment;
    }
    public void setItem(PopCan item)
    {
        this.item = item;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public String toString()
    {
        //Sorry for the terribly long line of code.
        return "Successful: " + getSuccess() + "\n" + "Amount paid: " + getPayment() + "\n" + "Item bought: " + item.getName() + "\n" + "Price of item: " + getPrice() + "\n";
    }
}

