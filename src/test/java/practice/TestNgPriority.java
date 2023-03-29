package practice;

import org.testng.annotations.Test;

public class TestNgPriority {
	@Test(priority=2)  //lower the value higher the priority (ex -3) priority default  value is 0
	public void createuser()

	{
		System.out.println("created");
	}
	public void updateuser()
	{
		System.out.println("updated");
	}

	@Test(priority=1)
	public void deleteuser()
	{
		System.out.println("deleted");
	}
}
