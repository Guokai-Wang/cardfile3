package cardfile3;

public class Browser 
{
	private BrowserType browserType;
	
	/*
	 * value 0, means no matter the version (all versions)
	 */
	private float version;
	
	public Browser(BrowserType browserType, float version) {
		this.browserType = browserType;
		this.version = version;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public void setBrowserType(BrowserType browserType) {
		this.browserType = browserType;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	// Desktop
	public static Browser chrome = new Browser(BrowserType.chrome, 0); // means no matter the version (all versions)
	public static Browser chrome3 = new Browser(BrowserType.chrome, 3);
	public static Browser chrome4 = new Browser(BrowserType.chrome, 4);
	public static Browser chrome6 = new Browser(BrowserType.chrome, 6);
	public static Browser chrome8 = new Browser(BrowserType.chrome, 8);
	public static Browser chrome12 = new Browser(BrowserType.chrome, 12);
	public static Browser chrome25 = new Browser(BrowserType.chrome, 25);
	public static Browser chrome27 = new Browser(BrowserType.chrome, 27);
	public static Browser chrome33 = new Browser(BrowserType.chrome, 33);
	public static Browser chrome56 = new Browser(BrowserType.chrome, 56);
	public static Browser chrome29 = new Browser(BrowserType.chrome, 29);
	public static Browser chrome62 = new Browser(BrowserType.chrome, 62);
	
	public static Browser firefox = new Browser(BrowserType.firefox, 0); // means no matter the version (all versions)
	public static Browser firefox3_5 = new Browser(BrowserType.firefox, 3.5f);
	public static Browser firefox4 = new Browser(BrowserType.firefox, 4);
	public static Browser firefox3_6 = new Browser(BrowserType.firefox, 36);
	public static Browser firefox15 = new Browser(BrowserType.firefox, 15);
	public static Browser firefox20 = new Browser(BrowserType.firefox, 20);
	public static Browser firefox22 = new Browser(BrowserType.firefox, 22);
	public static Browser firefox28 = new Browser(BrowserType.firefox, 28);
	public static Browser firefox35 = new Browser(BrowserType.firefox, 35);
	public static Browser firefox42 = new Browser(BrowserType.firefox, 42);
	public static Browser firefox51 = new Browser(BrowserType.firefox, 51);
	
	public static Browser ie9 = new Browser(BrowserType.ie, 9);
	
	public static Browser edge = new Browser(BrowserType.edge, 0); // means no matter the version (all versions)
	public static Browser edge12 = new Browser(BrowserType.edge, 12);
	public static Browser edge14 = new Browser(BrowserType.edge, 14);
	public static Browser edge16 = new Browser(BrowserType.edge, 16);
	public static Browser edge17 = new Browser(BrowserType.edge, 17);
	public static Browser edge76 = new Browser(BrowserType.edge, 76);
	
	public static Browser opera = new Browser(BrowserType.opera, 0);
	public static Browser opera10_5 = new Browser(BrowserType.opera, 10.5f);
	public static Browser opera10_6 = new Browser(BrowserType.opera, 10.6f);
	public static Browser opera11_5 = new Browser(BrowserType.opera, 11.5f);
	public static Browser opera15 = new Browser(BrowserType.opera, 15);
	public static Browser opera16 = new Browser(BrowserType.opera, 16);
	public static Browser opera20 = new Browser(BrowserType.opera, 20);
	public static Browser opera25 = new Browser(BrowserType.opera, 25);
	public static Browser opera42 = new Browser(BrowserType.opera, 42);
	
	public static Browser safari = new Browser(BrowserType.safari, 0); // means no matter the version (all versions)
	public static Browser safari3_1 = new Browser(BrowserType.safari, 3.1f);
	public static Browser safari3_2 = new Browser(BrowserType.safari, 3.2f);
	public static Browser safari4 = new Browser(BrowserType.safari, 4);
	public static Browser safari11 = new Browser(BrowserType.safari, 11);
        
        
	// Mobile
	public static Browser webview_android = new Browser(BrowserType.webview_android, 0);
	public static Browser webview_android2_3 = new Browser(BrowserType.webview_android, 2.3f);
	public static Browser webview_android3 = new Browser(BrowserType.webview_android, 3);
	public static Browser webview_android4_4 = new Browser(BrowserType.webview_android, 4.4f);
	public static Browser webview_android67 = new Browser(BrowserType.webview_android, 67);
	
	public static Browser chrome_android29 = new Browser(BrowserType.chrome_android, 29);
	public static Browser chrome_android62 = new Browser(BrowserType.chrome_android, 62);
	public static Browser chrome_android71 = new Browser(BrowserType.chrome_android, 71);
	public static Browser chrome_android75 = new Browser(BrowserType.chrome_android, 75);
	
	public static Browser firefox_android = new Browser(BrowserType.firefox_android, 0);
	public static Browser firefox_android24 = new Browser(BrowserType.firefox_android, 24);
	public static Browser firefox_android42 = new Browser(BrowserType.firefox_android, 42);
	public static Browser firefox_android58 = new Browser(BrowserType.firefox_android, 58);
	public static Browser firefox_android67 = new Browser(BrowserType.firefox_android, 67);
	
	public static Browser firefox_os = new Browser(BrowserType.firefox_os, 0);
	public static Browser firefox_os1_01 = new Browser(BrowserType.firefox_os, 1.01f);
	
	public static Browser ie_mobile10 = new Browser(BrowserType.ie_mobile, 10);
	
	public static Browser opera_android11 = new Browser(BrowserType.opera_android, 11);
	public static Browser opera_android12 = new Browser(BrowserType.opera_android, 12);
	public static Browser opera_android12_1 = new Browser(BrowserType.opera_android, 12.1f);
	public static Browser opera_android16 = new Browser(BrowserType.opera_android, 16);
	public static Browser opera_android46 = new Browser(BrowserType.opera_android, 46);

	public static Browser opera_mini = new Browser(BrowserType.opera_mini, 0);
	
	public static Browser safari_ios = new Browser(BrowserType.safari_ios, 0);
	public static Browser safari_ios3_2 = new Browser(BrowserType.safari_ios, 3.2f);
	public static Browser safari_ios4 = new Browser(BrowserType.safari_ios, 4);
	public static Browser safari_ios11 = new Browser(BrowserType.safari_ios, 11);
        
	public static Browser samsung_android = new Browser(BrowserType.samsunginternet_android, 0);
	public static Browser samsung_android4 = new Browser(BrowserType.samsunginternet_android, 4);
	public static Browser samsung_android5 = new Browser(BrowserType.samsunginternet_android, 5);
        
}


