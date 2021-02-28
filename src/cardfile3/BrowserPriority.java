/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardfile3;

/**
 *
 * @author a.schild
 */
public class BrowserPriority {
    private final Browser   browser;
    private final int       priority;

    public BrowserPriority(Browser browser, int priority) {
        this.browser = browser;
        this.priority = priority;
    }

    /**
     * @return the browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }
    
    
}
