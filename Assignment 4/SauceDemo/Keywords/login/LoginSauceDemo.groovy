package login

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class LoginSauceDemo {

	def openBrowser() {
		WebUI.openBrowser(GlobalVariable.URL)
		WebUI.maximizeWindow()
	}

	def loginSauceDemo(HashMap hashLogin) {
		String hashUsername = hashLogin.get("username")
		String hashPassword = hashLogin.get("password")
		String hashTC = hashLogin.get("TC_ID")
		String typeTestCase = hashLogin.get("TYPE_TESTCASE")
		String expectedResult = hashLogin.get("EXPECTED_RESULT")

		WebUI.setText(findTestObject("Object Repository/username"), hashUsername, FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(findTestObject("Object Repository/password"), hashPassword, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject("Object Repository/loginBtn"), FailureHandling.STOP_ON_FAILURE)

		if(typeTestCase.equalsIgnoreCase("Positive")) {
			String getDashboardTitle = WebUI.getText(findTestObject("Object Repository/dashboardTitle"))
			WebUI.verifyMatch(getDashboardTitle, "Swag Labs", false)
			WebUI.takeFullPageScreenshot()

			if(hashTC.equalsIgnoreCase("TC_AUT_05")) {
				String currentURL = WebUI.getUrl()
				WebUI.verifyMatch(currentURL, GlobalVariable.DashboardURL, false)
			}
		}else if(typeTestCase.equalsIgnoreCase("Negative")) {
			String getErrMsg = WebUI.getText(findTestObject("Object Repository/wrongUsnAndPass"), FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(getErrMsg, expectedResult, false)
			WebUI.takeFullPageScreenshot()
		}else {
			KeywordUtil.logInfo("Please check the test case value type")
		}
	}
}