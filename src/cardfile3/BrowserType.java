package cardfile3;

public enum BrowserType {
	
	webview_android("webview_android", true),
	chrome_android("chrome_android", true),
	firefox_android("firefox_android", true),
	firefox_os("firefox_os", true),
	ie_mobile("ie_mobile", true),
	opera_android("opera_android", true),
	opera_mini("opera_mini", true),
	safari_ios("safari_ios", true),
	samsunginternet_android("samsunginternet_android", true),
	
	chrome("chrome", false),
	firefox("firefox", false),
	ie("ie", false),
	edge("edge", false),
	opera("opera", false),
	safari("safari", false);

	private String name;
	private boolean mobile;
	
	private BrowserType(String name, boolean mobile) {
		this.name = name;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}
}
