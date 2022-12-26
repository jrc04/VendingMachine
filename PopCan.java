
/**
 * Write a description of class PopCan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PopCan
{
    //member variables
    private double volume;
    private int height;
    private String name;
    private String material;
    private int radius;
    private double price;
    //Constructor
    public PopCan(double volume, int height, String name, String material, int radius, double price)
    {
        this.volume = volume;
        this.height = height;
        this.name = name;
        this.material = material;
        this.radius = radius;
        this.price = price;
    }
    //getters
    public double getVolume()
    {
        return volume;
    }
    public int getHeight()
    {
        return height;
    }
    public String getName()
    {
        return name;
    }
    public String getMaterial()
    {
        return material;
    }
    public int getRadius()
    {
        return radius;
    }
    public double getPrice()
    {
        return price;
    }
    //setters
    public void setVolume(double volume)
    {
        this.volume = volume;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setMaterial(String material)
    {
        this.material = material;
    }
    public void setRadius(int radius)
    {
        this.radius = radius;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    //toString method: not necessary but makes coding easier
    public String toString()
    {
        return "Name: " + name + "\n" + "Price: " + price + "\n" + "Volume: " + volume + "\n" + "Material: " + material + "\n" + "Radius: " + radius + "\n" + "Height: " + height + "\n";
    }
}
