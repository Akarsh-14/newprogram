package practice;

import org.testng.annotations.Test;


public class TestNgPInvocationcount {

	@Test(invocationCount=-2)//if we give negative it will remove. invocation default value is 1
	public void create()
	{
		System.out.println("create");
	}
	@Test(invocationCount=2)
	public void update()
	{
		System.out.println("updated");
	}
	@Test()
	public void deleteuser()
	{
		System.out.println("deleted");
}
}
