package com.capgemini.ntc.security;

import com.capgemini.ntc.security.session.ISessionManager;
import com.capgemini.ntc.security.session.SessionManagerModule;
import com.capgemini.ntc.test.core.BaseTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The class offers security relevant functionality to be shared among test cases e.g. session management.
 *
 * @author Marek Puchalski, Capgemini
 */
public abstract class SecurityTest extends BaseTest {
	
	private static final Injector	injector	= Guice.createInjector(new SessionManagerModule());
	private ISessionManager				sessionManager;
	
	public SecurityTest() {
		super();
		sessionManager = injector.getInstance(ISessionManager.class);
	}
	
	public ISessionManager getSessionManager() {
		return sessionManager;
	}
	
	@Override
	public void setUp() {
		// nothing
	}
	
	@Override
	public void tearDown() {
		// nothing
	}
}
