/**
 * Created by myqu on 15/1/30.
 */
public class Item {

    //产品类: 商品的编号，名称，价格
    public Item(String itemId, String name, String unit, double price)
    {
        this.itemId = itemId;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    private String itemId;
    private String name;
    private String unit;
    private double price;


    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setPrice(String unit)
    {
        this.unit = unit;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }


}
