package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderpractice2 {
	@Test(dataProvider="electronics")
	public void dataproviderpractice2(String phone,int price)
	{
		System.out.println(phone+"---"+price);
	}

	@DataProvider(name="phone")
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

	@DataProvider(name="electronics")
	public Object[][] getdata1()
	{
		Object[][] data=new Object[3][2];
		data[0][0]="oneplus";
		data[0][1]=111;
		
		data[1][0]="samsung";
		data[1][1]=222;
		
		data[2][0]="iphone";
		data[2][1]=333;
		
		return data;
		
	}
}
