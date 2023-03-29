package vtiger.GenericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * This class contain implementation for ITestListeners interface of TestNG
 * @author akarsh
 *
 */
public class ListenersImplementation implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"---Execution Started---");
		
		test=report.createTest(methodName);
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+"----PASS");
		test.log(Status.PASS, methodName+"----PASS");
	
	}

	public void onTestFailure(ITestResult result) {
		
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		String methodName = result.getMethod().getMethodName();
	//	System.out.println(methodName+"----FAIL");
		test.log(Status.FAIL,methodName+"----FAIL");
		test.log(Status.INFO, result.getThrowable());
		
		String ScreenshotName = methodName+"-"+jutil.getSystemDateInFormat();
		try
		{
			
			String path = wutil.takeScreenShot(BaseClass.sdriver, ScreenshotName);
			test.addScreenCaptureFromPath(path);
		}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+"----SKIP");
		test.log(Status.SKIP,methodName+"----SKIP");
		test.log(Status.INFO,result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println("Suite execution started");
		
		ExtentSparkReporter htmlReport=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("Vtiger Execution Reports");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("VTIGER EXECUTION REPORT");
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base URL","http://localhost:8888");
		report.setSystemInfo("Base Browser","Firefox");
		report.setSystemInfo("Reporter Name","Akarsh");
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("suite execution finished");
		
		report.flush();
	}

	
	
}
