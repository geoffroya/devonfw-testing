package com.capgemini.ntc.core.groupTestCases.testSuites;

import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSmoke;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@IncludeCategories({ TestsSmoke.class })
@ExcludeCategories({})
@SuiteClasses({ "../**/*Test.class" })
public class TS_SmokeTests {
	
}
