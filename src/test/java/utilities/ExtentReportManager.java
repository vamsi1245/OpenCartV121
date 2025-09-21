package utilities;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBaseClass.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		spark = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
		spark.config().setDocumentTitle("OpenCart Test Project"); // Title of report
		spark.config().setReportName("Functional Test Automation Report"); // name of the report
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Brwoser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Included Groups", includedGroups.toString());

		}

	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getTestClass().getName()); // create new entry in the report
		test.assignCategory(tr.getMethod().getGroups()); // assign category to the test
		test.log(Status.PASS, "Test Passed"); // send the passed information to the report with GREEN color highlighted
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getTestClass().getName()); 
		test.assignCategory(tr.getMethod().getGroups()); 
		test.log(Status.FAIL, "Test Failed"); 
		test.log(Status.INFO, tr.getThrowable().getMessage()); 

		try {
			String imgPath = new BaseClass().captureScreen(tr.getName());
			test.addScreenCaptureFromPath(imgPath);// adding screen shot

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getTestClass().getName()); // create new entry in the report
		test.assignCategory(tr.getMethod().getGroups()); // assign category to the test
		test.log(Status.SKIP, "Test Skipped"); // send the passed information to the report with GREEN color highlighted
		test.log(Status.INFO, tr.getThrowable().getMessage()); // add error/exception message to the report

	}
	public void onFinish(ITestContext testContext) {
		if (extent != null) {
			extent.flush();
		}
	}
//	public String getReportName() {
//		return repName;
//	}
//	String getReportPath() {
//		return (".\\reports\\" + repName);
//	}
//	String path=System.getProperty("user.dir")+getReportPath();
//	

}
