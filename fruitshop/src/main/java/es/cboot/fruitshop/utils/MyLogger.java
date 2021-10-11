package es.cboot.fruitshop.utils;

public class MyLogger {

	private static final boolean DEBUG = false;
	
	public static void log(String msg) {
		if (DEBUG) {
			System.out.println(msg);
		}
	}
	
	public static void log(Object obj) {
		if (DEBUG) {
			System.out.println(obj.toString());
		}
	}
}
