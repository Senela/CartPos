import java.util.HashMap;



//产品类: 商品的编号，名称，价格
class Product{
	
	public Product(String itemId, String name, String unit, double price, int promotionCount) 
	{ 
		this.itemId = itemId;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.promotionCount = promotionCount;
	}

	private String itemId; 
	private String name; 
	private String unit;
	private double price;
	private int   promotionCount;//买promotionCount赠1，若为0则表示不赠送
	

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
	 
	
	public int getPromotionCount() 
	{ 
		return promotionCount;
	}

	public void setPromotionCount(int promotionCount)
	{
		this.promotionCount = promotionCount;
	}
	 
}


public class Cart {

	 
	//初始化商品价目表
	static HashMap<String ,Product> list = new HashMap<>();
	static
	{
		list.put("ITEM000001", new Product("ITEM000001", "羽毛球", "个", 1.00, 2));
		list.put("ITEM000002", new Product("ITEM000002", "乒乓球", "个", 1.90, 1));
		list.put("ITEM000003", new Product("ITEM000003", "苹果","斤", 5.50, 2));
		list.put("ITEM000004", new Product("ITEM000004", "牙膏", "个", 2.00, 0));
		list.put("ITEM000005", new Product("ITEM000005", "可口可乐", "瓶", 3.00, 2));
	}
	
	//获取各个分类个有多少个物品   输入参数array：购物商品条形码数组   输出参数buyHashMap：购物清单
		public void getAmountByType(String[] array,HashMap<String, Integer> buyHashMap)
		{
			for (String t : array)
			{
				String[] tempArray = t.split("-");
				if (buyHashMap.get(tempArray[0]) != null)  // 购物车中已有该商品
				{
					if(tempArray.length > 1) //输入信信息是ITEM000003-2类型
						buyHashMap.put(tempArray[0], buyHashMap.get(tempArray[0]) + Integer.valueOf(tempArray[1]));
					else  //输入信息是ITEM000003类型
						buyHashMap.put(tempArray[0], buyHashMap.get(tempArray[0]) + 1);
	
				}
				else  // 购物车中没有该商品
				{
					if(tempArray.length > 1) //输入信信息是ITEM000003-2类型
						buyHashMap.put(tempArray[0], Integer.valueOf(tempArray[1]));
					else //输入信息是ITEM000003类型
						buyHashMap.put(tempArray[0], 1); 
				}
			}
		}
		
		//根据统计的所有购买清单，得出可以获得优惠的物品总清单
		public void getDiscountAmount(HashMap<String, Integer> buyHashMap, HashMap<String, Integer> disCountHashMap) {
			for (String key : buyHashMap.keySet()) 
			{
				Product product  = list.get(key);
				if(product != null && product.getPromotionCount() !=0)
				{
					if(buyHashMap.get(key) > product.getPromotionCount()){//超过getPromotionCount个才有优惠
						disCountHashMap.put(key, buyHashMap.get(key)/(product.getPromotionCount()+1));
				}
				}
			}
		}
		
		//打印购物结账单
		private void printProductList(HashMap<String, Integer> buyHashMap,HashMap<String, Integer> disCountHashMap) {
			for (String key : buyHashMap.keySet()) 
			{ 
				Product  product = list.get(key);
				if(product != null)
				{
					int discountAmount = disCountHashMap.get(key) != null?disCountHashMap.get(key):0;
					System.out.println("名称:" + product.getName() +",数量:" + buyHashMap.get(key) + product.getUnit() +",单价:" + String.format("%.2f", product.getPrice())  + "(元),小计:" + String.format("%.2f",product.getPrice()*(buyHashMap.get(key)-discountAmount)) + "(元)");	
				} 
			}
		}
		
		//打印优惠清单
		private void printDiscountList(HashMap<String, Integer> disCountHashMap) {
			for (String key : disCountHashMap.keySet()) 
			{
				Product  product = list.get(key);
				if(product != null){
					int discountAmount = disCountHashMap.get(key) != null?disCountHashMap.get(key):0;
					System.out.println("名称:" + product.getName() +",数量:" + discountAmount + product.getUnit());
				} 
			}
		}
		
		//打印price
		private void printPriceResult(HashMap<String, Integer> buyHashMap,HashMap<String, Integer> disCountHashMap) {
			double tempSum = 0.0;
			double tempDiscountSum = 0.0;
			for (String key : buyHashMap.keySet()) 
			{
				Product  product = list.get(key);
					if(product != null){
						int discountAmount = disCountHashMap.get(key) != null?disCountHashMap.get(key):0;
						tempDiscountSum = tempDiscountSum + product.getPrice()*discountAmount;
						tempSum = tempSum + product.getPrice()*(buyHashMap.get(key)-discountAmount);
					} 
			}
			System.out.println("总计:" + String.format("%.2f", tempSum) +"元");
			System.out.println("节省:" + String.format("%.2f", tempDiscountSum)  +"元");
		}
		
		
		
		
		private void  printInventory(String[] array)
		{  
			HashMap<String, Integer> buyHashMap = new HashMap<>();      //购物清单
			HashMap<String, Integer> disCountHashMap = new HashMap<>(); //优惠清单
			
			getAmountByType(array, buyHashMap);
			getDiscountAmount(buyHashMap, disCountHashMap);
			System.out.println("***<没钱赚商店>购物清单***");
			printProductList(buyHashMap, disCountHashMap); 
			System.out.println("----------------------\n挥泪赠送商品:");
			printDiscountList(disCountHashMap);
			System.out.println("----------------------");
			printPriceResult(buyHashMap, disCountHashMap);
			System.out.println("**********************");
			
			System.out.close();
		}
		
		//程序主入口
		public static void main(String[] args) {
			Cart cart = new Cart();
			
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

			cart.printInventory(cartArray);	
		}	
}
