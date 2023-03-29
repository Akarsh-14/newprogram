package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class TestNgDependsOn {

	
	public void createuser()
	{
		System.out.println("created");
		Assert.fail();
	}
	@Test(dependsOnMethods="createuser")
	public void updateuser()
	{
		System.out.println("updated");
	}
	@Test(dependsOnMethods={"createuser","updateuser"})
	public void deleteuser()
	{
		System.out.println("deleted");
	}
}
