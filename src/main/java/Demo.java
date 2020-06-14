import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Demo {

    private static final String CHROME = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
    private static final String TIGER_TRADE = "D:\\UIAutomation\\TigerTrade5.9.0.0_Offline_06-05\\TigerTrade.exe";
    private static final String FTNN = "C:\\Program Files (x86)\\FTNN\\FTNN.exe";
    private static final String WECHAT = "C:\\Program Files (x86)\\Tencent\\WeChat\\WeChat.exe";

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setup();
    }


    private void setup() {
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps = getCaps();
        WindowsDriver<WindowsElement> driver = null;
        try {
            driver = new WindowsDriver<WindowsElement>(url, caps);
        } catch (Exception e) {
            caps.setCapability("app", "Root");
            driver = new WindowsDriver<WindowsElement>(url, caps);
            RemoteWebElement elementsByTagName = driver.findElementByClassName("Qt5QWindowIcon");
            String mainWindowHandle = elementsByTagName.getAttribute("NativeWindowHandle");
            mainWindowHandle = Integer.toHexString(Integer.parseInt(mainWindowHandle)); // Convert to Hex
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appTopLevelWindow", mainWindowHandle);
            driver = new WindowsDriver<WindowsElement>(url, capabilities);
        }
        driver.getKeyboard().releaseKey("qqqqqqq1");
        RemoteWebElement login = driver.findElementByName("立即登录 Enter");
        login.click();

       /* try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*WindowsElement  quoteAlert = driver.findElementByName("行情权限").findElementByAccessibilityId();
        quoteAlertConfirmButton.click();*/

        Set<String> s = driver.getContextHandles();
        for(int i=0; i<s.size();i++) {
            System.out.println(s.toArray()[0]);
        }

    }


    private DesiredCapabilities getCaps() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformVersion", "10");
        caps.setCapability("platformName", "Windows");
        caps.setCapability("deviceName", "WindowsPC");
        // caps.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        caps.setCapability("app", TIGER_TRADE);
        caps.setCapability("newCommandTimeout", 20000);
        caps.setCapability("createSessionTimeout", 1000);

        return caps;
    }
}