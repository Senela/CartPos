/**
 * Created by myqu on 15/1/30.
 */

import java.util.HashMap;
import java.util.Map;


public class Pos {
    public Pos()
    {
        //初始化商品价目表
        Map list = new HashMap<String, Item>();
        list.put("ITEM000001", new Item("ITEM000001", "羽毛球", "个", 1.00));
        list.put("ITEM000002", new Item("ITEM000002", "乒乓球", "个", 1.90));
        list.put("ITEM000003", new Item("ITEM000003", "苹果", "斤", 5.50));
        list.put("ITEM000004", new Item("ITEM000004", "牙膏", "个", 2.00));
        list.put("ITEM000005", new Item("ITEM000005", "可口可乐", "瓶", 3.00));

        this.goodsList = list;
    }

    public Map goodsList;

    public void  printInventory(String[] array)
    {
        Cart cart = new Cart();
        HashMap<String, Integer> buyHashMap = cart.getAmountByType(array); //购物清单

        Promotion promotion = new Promotion();
        HashMap<String, Integer> disCountHashMap = promotion.getDiscountMap(buyHashMap);//优惠清单

        Printer  printer = new Printer(); // 打印购物清单
        printer.printOut(buyHashMap, disCountHashMap, goodsList);
    }



    //程序主入口
    public static void main(String[] args) {
        Pos pos = new Pos();

        String[] cartArray = new String[]{
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000003-2",
                "ITEM000005",
                "ITEM000005",
                "ITEM000005"};	//输入

        pos.printInventory(cartArray);
    }

}
