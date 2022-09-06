package com.battlefield.tests;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

import com.battlefield.settings.Status;
import com.battlefield.userInput.UserInputHandler;

class UserInputHandlerTest {

	 
	    /**
	     *  Runs all tests in pseudoparallelism
	     */
	    public void runAllTests() {
	        Class<?>[] classes = { ParallelTest1.class, ParallelTest2.class,ParallelTest3.class,ParallelTest4.class };
	        JUnitCore.runClasses(new ParallelComputer(true, true), classes);
	    }
	 /**
	 *  method will take an integer instead of cell, it should return Status.ProblemCell
	 *
	 */
	public static class ParallelTest1 {
	        @Test
	        public void test1() {
	        	InputStream in = new ByteArrayInputStream("5".getBytes());
	    		System.setIn(in);
	    		Object[] result=UserInputHandler.INSTANCE.cellInput();
	    		Assertions.assertEquals((Status)result[0], Status.ProblemCell);
	            duration(400);
	        }
	    }

	    /**
	     *  method will take an integer instead of cell, it should return Status.ProblemCell
	     * 
	     *
	     */
	    public static class ParallelTest2 {
	        @Test
	        public void test2() {
	        	InputStream in = new ByteArrayInputStream("10".getBytes());
	    		System.setIn(in);
	    		Object[] result=UserInputHandler.INSTANCE.cellInput();
	    		Assertions.assertEquals((Status)result[0], Status.ProblemCell);
	            duration(400);
	        }
	    }
	    /**
	     *  Controls if method takes a parameter which is out of bound
	     *
	     */
	    public static class ParallelTest3{
	    	@Test
	    	public void test3() {
	    		InputStream in = new ByteArrayInputStream("4".getBytes());
	    		System.setIn(in);
	    		Object[] result=UserInputHandler.INSTANCE.numberMenuInputWithBackTurnOff(5);
	    		Assertions.assertEquals((Status)result[0],Status.NumberIsNotValid);
	    		duration(400);
	    		
	    	}
	    }
	    /**
	     *  Instead of integer method will take string as parameter
	     *
	     */
	    public static class ParallelTest4{
	    	@Test
	    	public void test4() {
	    		InputStream in = new ByteArrayInputStream("a".getBytes());
	    		System.setIn(in);
	    		Object[] result=UserInputHandler.INSTANCE.numberMenuInputWithBackTurnOff(5);
	    		Assertions.assertEquals((Status)result[0],Status.NumberIsNotValid);
	    		duration(400);
	    		
	    	}
	    }
	    

	    public static void duration(long ms) {
	        try {
	            Thread.sleep(ms);
	        } catch (InterruptedException e) {
	            System.out.println("interrupted");
	        }
	    }

}
