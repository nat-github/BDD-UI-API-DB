package CommonUtils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.example.enums.Browsers;

import java.util.Date;

public class UIUtils {
    Playwright playwright = Playwright.create();
    Page page;
    Browser browser;
    BrowserContext context;

    public void setDriver(String browserType){
        if(browserType.equalsIgnoreCase(Browsers.CHROME.name())) {
            BrowserType chrome = playwright.chromium();
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browser = chrome.launch(options);
            page = browser.newPage();
            page.setDefaultTimeout(10000); // set the timeout to 10 seconds
        }
        else if(browserType.equalsIgnoreCase(Browsers.FIREFOX.name())){
            BrowserType firefox = playwright.firefox();
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browser = firefox.launch(options);
            page = browser.newPage();
            page.setDefaultTimeout(10000);
        }
    }
    public void navigate(String url) {
        page.navigate(url);
    }

    public Locator findElement(String locator) {
        return page.locator(locator);
    }

    public void click(String locator) {
        findElement(locator).click();
    }

    public void type(String locator, String text) {
        findElement(locator).fill(text);
    }
    public void selectOptionByValue(String selector, String value) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setValue(value)});
    }
    public void selectOptionByText(String selector, String text) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setLabel(text)});
    }

    public void selectOptionByIndex(String selector, int index) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setIndex(index)});
    }

    public void selectMultipleOptionsByValues(String selector, String[] values) {
        SelectOption[] options = new SelectOption[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = new SelectOption().setValue(values[i]);
        }
        page.selectOption(selector, options);
    }
    public String getText(String locator) {
        return findElement(locator).textContent();
    }
}
