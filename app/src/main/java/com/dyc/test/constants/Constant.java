package com.dyc.test.constants;

import java.io.File;

import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class Constant {

	/** 外部存储路径 */
	public static final String EXTERNAL_STORAGE_DIR = Environment
			.getExternalStorageDirectory().toString();
	// /** SD卡根路径 */
	public static final String ROOT_PATH = EXTERNAL_STORAGE_DIR
			+ File.separator + "xingfuhuaxia";
	/** apk文件目录 */
	public static final String APK_FILE_DIR = ROOT_PATH + File.separator
			+ "apk";
	/** update文件目录 */
	public static final String UPDATE_FILE_DIR = ROOT_PATH + File.separator
			+ "update";
	/** update文件名 */
	public static final String UPDATE_FILE_NAME = "update.p";

	// // 日期格式
	// /** 中文格式时间 */
	// String FORMAT_C = "yyyy年 MM月 dd日";
	/** 英文格式时间 */
	public static final String FORMAT_E = "yyyy-M-d HH:mm:ss";
	// /** 英文简洁格式时间 */
	// String FORMAT_E_SIMPLE = "yyyy-M-d";
	// String FORMAT_E_SIMPLE_1 = "yyyy.M.d";
	/** crash文件目录 */
	public static final String CRASH_FILE_DIR = ROOT_PATH + File.separator
			+ "crash";
	/** crash file name */
	public static final String CRASH_FILE_NAME = "crash.log";
	/** crash文件路径 */
	public static final String CRASH_FILE_PATH = CRASH_FILE_DIR
			+ File.separator + CRASH_FILE_NAME;

	/** update文件路径 */
	public static final String UPDATE_FILE_PATH = UPDATE_FILE_DIR
			+ File.separator + UPDATE_FILE_NAME;

	public static final CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;
	public static final int DISK_IMAGECACHE_QUALITY = 100; // PNG is lossless so
															// quality is
	// ignored
	// but must be provided
	// 测试
	public static final String BASEURL = "http://192.168.1.8:8080/wordpress?";

	public static final String TWODEMENSION = "http://wxsaletest.cfldcn.com:3000/VIEW/Regist/Regist.html?salerid=";

	public static final String MESSAGEURL = "http://wxsaletest.cfldcn.com:8889/";

	public static final String WEB_URL_ = "http://wxsaletest.cfldcn.com:8889/";

	public static final CharSequence VER_NAME = "首页-测试版";
	public static final String huaxiaUserid = "huaxiaUserid";
	public static final String huaxiaUserCookies = "huaxiaUserCookies";
	public static final String huaxiaJobType = "huaxiaJobType";
	public static final String huaxiaNotice = "huaxiaNotice";
	
	// 正式
	// public static final String BASEURL =
	// "http://saleapp.cfldcn.com/service/main.ashx";
	//
	// public static final String TWODEMENSION =
	// "http://wsale.cfldcn.com/VIEW/Regist/Regist.html?salerid=";
	//
	// public static final String MESSAGEURL = "http://isale.cfldcn.com:8889/";
	//
	// public static final String WEB_URL_ = "http://saleapp.cfldcn.com/";

	// public static final CharSequence VER_NAME = "首页-正式版";

}
