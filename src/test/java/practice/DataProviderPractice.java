package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	@Test(dataProvider="getdata")
	public void dataproviderpractice(String phone,int price)
	{
		System.out.println(phone+"---"+price);
	}

	@DataProvider
	public Object[][] getdata()
	{                              //row //column
		Object[][] data=new Object[3][2]; //3 sets of data each consit's of 2 information
		
		data[0][0]="oneplus";
		data[0][1]=1200;
		
		data[1][0]="samsung";
		data[1][1]=1000;
		
		data[2][0]="iphone";
		data[2][1]=800;
		return data;
		
	}
}
