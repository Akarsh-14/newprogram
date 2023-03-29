package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {
	@Test
	public void sample()
	{
		System.out.println("step1");
		System.out.println("step2");
		Assert.assertEquals(true, false);
		System.out.println("step3");
		System.out.println("step4");
	}

	@Test
	public void sample2()
	{
		SoftAssert s=new SoftAssert();
		System.out.println("step1");
		System.out.println("step2");
		s.assertEquals(true, false); //failure
		System.out.println("step3");
		s.assertTrue(false);
		
		System.out.println("step4");
		s.assertAll(); //log all the assertion failure
	}
}
