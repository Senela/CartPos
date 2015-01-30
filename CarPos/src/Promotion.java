/**
 * Created by myqu on 15/1/30.
 */

import java.util.HashMap;
import java.util.Map;

public class Promotion {
    public Promotion ()
    {
        HashMap<String , Integer> disCountMap = new HashMap<String , Integer>();
        disCountMap.put("ITEM000001", 2);
        disCountMap.put("ITEM000005", 2);

        this.promotionHashMap = disCountMap;
    }


    private HashMap<String, Integer>  promotionHashMap;

    //根据统计的所有购买清单，得出可以获得优惠的物品总清单
    public HashMap<String, Integer> getDiscountMap(Map  bugMap)
    {
        HashMap<String, Integer> disCountHashMap = new HashMap();

        for (Object key :bugMap.keySet())
        {
            if(this.promotionHashMap.get(key) != null)
            {
                disCountHashMap.put((String)key, (Integer)bugMap.get(key)/(this.promotionHashMap.get(key)+1));
            }
        }

        return disCountHashMap;
    }

}
