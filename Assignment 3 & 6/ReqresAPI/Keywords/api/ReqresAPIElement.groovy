package api

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable

public class ReqresAPIElement {

	def sendGetUsersRequest() {
		def response = WS.sendRequest(findTestObject('Object Repository/GET_Users'))
		return response
	}

	def verifyStatusCode(def response) {
		WS.verifyResponseStatusCode(response, 403)
	}

	def verifyUsersResponseStructure(def response) {
		def json = new JsonSlurper().parseText(response.getResponseText())

		assert json.page != null
		assert json.per_page != null
		assert json.total != null
		assert json.total_pages != null
		assert json.data != null
	}

	def sendGetUsersRequest(String pageValue) {
		TestObject request = new TestObject('GET_Users')
		request.setRestRequestMethod('GET')
		request.setRestUrl('https://reqres.in/api/users?page=${pageValue}')

		def response = WS.sendRequest(request)
		return response
	}
}
