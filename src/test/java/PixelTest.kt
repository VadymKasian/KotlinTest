import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.net.MalformedURLException
import java.net.URL

class PixelTest {
    var driver: AndroidDriver<MobileElement>? = null
    var wait: WebDriverWait? = null
    private var result: String? = null

    @BeforeMethod
    @Throws(MalformedURLException::class)
    fun setup() {
        val caps = DesiredCapabilities()
        caps.setCapability("deviceName", "Pixel_2_API_26")
        caps.setCapability("udid", "emulator-5554")
        caps.setCapability("platformName", "Android")
        caps.setCapability("platformVersion", "8.0")
        caps.setCapability("skipUnlock", "true")
        caps.setCapability("appActivity", "com.android.calculator2")
        caps.setCapability("noReset", "false")
        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), caps)
        wait = WebDriverWait(driver, 10)
    }

    @Test
    @Throws(InterruptedException::class)
    fun basicTest() {

        wait!!.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.android.calculator2:id/digit_5"))).click()

        wait!!.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.android.calculator2:id/op_mul"))).click()

        wait!!.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.android.calculator2:id/digit_6"))).click()

        result = wait!!.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.android.calculator2:id/result"))).text

        Assert.assertEquals(result, "30")
    }

    @AfterMethod
    fun teardown() {
        driver!!.quit()
    }
}