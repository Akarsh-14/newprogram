package practice;

import org.testng.annotations.Test;
@Test
public class TestNgInvoPrio {
	@Test(invocationCount=2,priority=2)
	public void createuser()
	{
		System.out.println("created");
	}
	public void updateuser()
	{
		System.out.println("updated");
	}
	public void deleteuser()
	{
		System.out.println("deleted");
	}
}
