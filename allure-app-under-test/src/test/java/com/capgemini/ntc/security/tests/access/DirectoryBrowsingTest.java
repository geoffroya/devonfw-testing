package com.capgemini.ntc.security.tests.access;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.capgemini.ntc.security.tests.Constants;
import com.capgemini.ntc.security.tests.SecurityTest;
import com.capgemini.ntc.security.tests.SessionEnum;
import com.capgemini.ntc.security.tests.SubUrlEnum;

import io.restassured.specification.RequestSpecification;

/**
 * The test verifies that directory browsing is disabled.
 * OWASP ASVS requirement V4.5: Verify that directory browsing is disabled
 * unless deliberately desired. Additionally, applications should not allow
 * discovery or disclosure of file or directory metadata, such as Thumbs.db,
 * .DS_Store, .git or .svn folders.
 * Purpose: You don't want the attacker to learn about the system more then
 * needed and he certainly does not need to list files in the directories
 * on your server (unless you deliberately desire this feature). Putting
 * private content of your e.g. .git folder into your distribution package
 * to be uploaded to the server and downloaded by the attacker because of
 * directory browsing feature is a mortal sin on its own.
 *
 * @author Marek Puchalski, Capgemini
 */
@RunWith(Parameterized.class)
public class DirectoryBrowsingTest extends SecurityTest {
	
	private SessionEnum	session;
	private SubUrlEnum	path;
	private String		origin;
	private int			statusCode;
	
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
						{ SessionEnum.ANON, getEnvValue(Constants.CLIENT_ORIGIN), SubUrlEnum.IMG_DIR, 403 },
						{ SessionEnum.ANON, getEnvValue(Constants.SERVER_ORIGIN), SubUrlEnum.REST_ROOT, 403 },
		});
	}
	
	public DirectoryBrowsingTest(SessionEnum session, String origin, SubUrlEnum path, int statusCode) {
		this.session = session;
		this.origin = origin;
		this.path = path;
		this.statusCode = statusCode;
	}
	
	@Test
	public void testHeader() {
		RequestSpecification rs = initBuilder(session)
						.setBaseUri(origin)
						.setBasePath(path.toString())
						.build();
		given(rs)
						.when()
						.get()
						.then()
						.statusCode(statusCode);
	}
}