package cn.com.greattimes.support.utils;

import java.io.File;

public final class PathUtils {

	public final static String FILE_SEPARATOR = System.getProperty("file.separator");

	private static String BASE_PATH;

	static {
		String path = String.valueOf(PathUtils.class.getResource("/"));
		if ("null".equals(path)) {
			path = System.getProperty("user.dir").toString();
		}
		path = path.replaceAll("%20", " ");
		File file = new File(path);
		path = file.getParent();
		if (OSUtils.isWindows()) {
			path = path.replace("file:" + FILE_SEPARATOR, "");
		} else if (OSUtils.isLinux()) {
			path = path.replace("file:", "");
		}
		setBasePath(path);
	}

	public static void setBasePath(String basePath) {
		if (!basePath.endsWith(FILE_SEPARATOR)) {
			basePath = basePath + FILE_SEPARATOR;
		}
		BASE_PATH = basePath;
	}

	public static String getBasePath() {
		return BASE_PATH;
	}

	public static String getConfigPath() {
		//System.out.println(BASE_PATH);
		return BASE_PATH + "conf" + FILE_SEPARATOR;
	}

	public static String getLibPath() {
		return BASE_PATH + "lib" + FILE_SEPARATOR;
	}

	public static String getLogPath() {
		return BASE_PATH + "logs" + FILE_SEPARATOR;
	}

	public static String getStoragePath() {
		return BASE_PATH + "storage" + FILE_SEPARATOR;
	}

	public static String getTempPath() {
		return BASE_PATH + "temp" + FILE_SEPARATOR;
	}

	public static String getWebPath() {
		return BASE_PATH + "web" + FILE_SEPARATOR;
	}
	
	public static String getJniPath() {
		return BASE_PATH + "jni" + FILE_SEPARATOR;
	}

	public static String wrapPath(String path) {
		if(path.endsWith(File.separator)){
			return path;
		} else {
			return path + FILE_SEPARATOR;
		}
	}
}
