package com.capgemini.ntc.test.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersCore;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.test.core.testRunners.ParallelTestClassRunner;
import com.google.inject.Guice;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	
	@ru.yandex.qatools.allure.annotations.Parameter("Width")
	public static int windowWidth;
	
	@ru.yandex.qatools.allure.annotations.Parameter("Height")
	public static int windowHeight;
	
	@ru.yandex.qatools.allure.annotations.Parameter("Username")
	private String defaultUsername_lastUsedInTest;
	
	private static IEnvironmentService environmentService;
	
	public BaseTest() {
		
		setPropertiesSettings();
		setRuntimeParametersCore();
		setEnvironmetInstance();
		
	}
	
	public static IEnvironmentService getEnvironmentService() {
		return environmentService;
	}
	
	public static void setEnvironmentService(IEnvironmentService environmentService) {
		BaseTest.environmentService = environmentService;
	}
	
	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}
	
	@AfterClass
	public static final void tearDownClass() {
		// DriverManager.stop();
	}
	
	@After
	public void tearDownTestLast() {
		
	}
	
	@Override
	abstract public void setUp();
	
	@Override
	abstract public void tearDown();
	
	@Rule
	public TestWatcher testWatcher = new BaseTestWatcher(this);
	
	private void setEnvironmetInstance() {
		// Environment variables either from environmnets.csv or any other input data.
		IEnvironmentService environmetInstance = Guice.createInjector(new EnvironmentModule())
				.getInstance(IEnvironmentService.class);
		environmetInstance.setEnvironment(RuntimeParametersCore.ENV.getValue());
		BaseTest.setEnvironmentService(environmetInstance);
	}
	
	private void setRuntimeParametersCore() {
		// Read System or maven parameters
		BFLogger.logDebug(RuntimeParametersCore.ENV.toString());
	}
	
	private void setPropertiesSettings() {
		/*
		 * For now there is no properties settings file for Core module. In future, please have a look on Selenium
		 * Module 
		 * PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesSettingsModule.init())
		 * 		  .getInstance(PropertiesSelenium.class);
		 */
		
	}
}
