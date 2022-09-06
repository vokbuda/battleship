package com.battlefield.security;

import java.lang.reflect.ReflectPermission;
import java.security.Permission;

/**
 * @author dubkov
 *
 */
public class ExtendedSecurityManager extends SecurityManager {
	
	/**
	 *  Disallows user to write reflection in this application
	 *
	 */
	@Override
	public void checkPermission(Permission perm) {
	  if (perm instanceof ReflectPermission) {
		  if(!isJUnitTest()) {
			  throw new SecurityException("Reflection from not JUnit is not allowed!");
		  }		  
	  }
	  if (perm instanceof RuntimePermission) {
	    if (perm.implies(new RuntimePermission("accessDeclaredMembers"))) {
	    	if(!isJUnitTest()) {
				  throw new SecurityException("Reflection from not JUnit is not allowed!");
			  }
	    }
	 }
	}
	/**
	 * @return true if method had been accessed by Junit Test
	 */
	public static boolean isJUnitTest() {  
		  for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		    if (element.getClassName().startsWith("org.junit.")) {
		    	return true;
		    }           
		  }
		  
		  return false;
	}

}
