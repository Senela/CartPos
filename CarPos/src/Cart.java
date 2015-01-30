/**
 * Created by myqu on 15/1/30.
 */

import java.util.HashMap;

public class Cart {
    //获取各个分类个有多少个物品   输入参数array：购物商品条形码数组   输出参数buyHashMap：购物清单
    public HashMap getAmountByType(String[] array)
    {
        HashMap<String, Integer> buyHashMap = new HashMap<String, Integer>();
        for (String t : array)
        {
            String[] tempArray = t.split("-");
            Integer  hasCount =  buyHashMap.get(tempArray[0])!= null ? buyHashMap.get(tempArray[0]) : 0;
            Integer lastCount =  tempArray.length > 1 ? Integer.valueOf(tempArray[1]) : 1;

            buyHashMap.put(tempArray[0], hasCount +lastCount);

        }

        return buyHashMap;
    }

}
