/**
 * Created by myqu on 15/1/30.
 */

import java.util.HashMap;
import java.util.Map;


public class Printer {
    public Printer( )
    {
        this.printStr = new String();
    }

    private String printStr;

    public void addString(String  append)
    {
        this.printStr = this.printStr+append;
    }

    public  void printOut(HashMap<String , Integer> bugMap, HashMap<String , Integer> discountMap, Map goodsList)
    {
        addString(printItemList(bugMap, discountMap, goodsList));
        addString(printDiscountList(discountMap, goodsList));
        addString(printPriceResult(bugMap, discountMap, goodsList));
        print();
    }


    private void print()
    {
        System.out.println(this.printStr);
        System.out.close();
    }

    //打印购物结账单
    private String printItemList(HashMap<String , Integer> buyHashMap, HashMap<String , Integer> disCountHashMap, Map goodsList)
    {
        String  itemStr = "***<没钱赚商店>购物清单***\n";;
        for (Object key : buyHashMap.keySet())
        {
            Item  Item = (Item)goodsList.get(key);
            Integer  discount =  disCountHashMap.get(key) == null ? 0 : disCountHashMap.get(key);
            if(Item != null)
            {
                itemStr = itemStr + "名称:" + Item.getName() +",数量:" + buyHashMap.get(key) + Item.getUnit() +",单价:" + String.format("%.2f", Item.getPrice())  + "(元),小计:"+ String.format("%.2f",Item.getPrice()*((Integer)buyHashMap.get(key)- discount)) + "(元)\n";

            }
        }

        return itemStr;
    }


    //打印优惠清单
    private String printDiscountList(HashMap<String , Integer> disCountHashMap, Map goodsList) {
        String discountStr =  "----------------------\n挥泪赠送商品:\n";
        for (String key : disCountHashMap.keySet())
        {
            Item  Item = (Item)goodsList.get(key);
            int discountAmount = disCountHashMap.get(key) != null?disCountHashMap.get(key):0;
            discountStr = discountStr + "名称:" + Item.getName() +",数量:" + discountAmount + Item.getUnit()+"\n";
        }
        return discountStr;
    }

    //打印price
    private String printPriceResult(HashMap<String , Integer> buyHashMap, HashMap<String , Integer> disCountHashMap , Map goodsList) {

        String settlementStr = "----------------------\n";
        double tempSum = 0.0;
        double tempDiscountSum = 0.0;
        for (String key : buyHashMap.keySet())
        {
            Item  Item =  (Item)goodsList.get(key);
            if(Item != null){
                int discountAmount = disCountHashMap.get(key) != null?disCountHashMap.get(key):0;
                tempDiscountSum = tempDiscountSum + Item.getPrice()*discountAmount;
                tempSum = tempSum + Item.getPrice()*(buyHashMap.get(key)-discountAmount);
            }
        }
        settlementStr = settlementStr + "总计:" + String.format("%.2f", tempSum) +"元\n";
        settlementStr = settlementStr +  "节省:" + String.format("%.2f", tempDiscountSum)  +"元\n";
        settlementStr = settlementStr +"**********************\n";
        return settlementStr;
    }

}
