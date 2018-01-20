package cn.com.greattimes.support.utils;

public final class OSUtils {

	private final static String OS;

	static {
		OS = System.getProperty("os.name").toLowerCase();
	}

	public static boolean isLinux() {
		return OS.indexOf("linux") >= 0;
	}

	public static boolean isWindows() {
		return OS.indexOf("windows") >= 0;
	}

	public static boolean isMacOS() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0;
	}

}
