package practice;

import org.testng.annotations.Test;
@Test
public class TestNgEnabled {
	@Test(enabled=false)
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
