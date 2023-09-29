package parallel.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.hooks.BaseTest;

import java.util.List;

public class WebElementParallel extends BaseElementParallel implements WebElement {

    private final By elementBy;
    private WebDriverWait wait;

    public WebElementParallel(By locator) {
        this.elementBy = locator;
        this.wait = this.getDriverWait();
    }

    public WebElementParallel withWait(WebDriverWait webDriverWait) {
        this.wait = webDriverWait;
        return this;
    }

    public void click() {
        this.get(this.elementBy).click();
    }

    public void submit() {
        this.get(this.elementBy).submit();
    }

    public void sendKeys(CharSequence... keysToSend) {
        this.get(this.elementBy).sendKeys(keysToSend);
    }

    public void clear() {
        this.get(this.elementBy).clear();
    }

    public String getTagName() {
        return this.get(this.elementBy).getTagName();
    }

    public String getAttribute(String name) {
        return this.get(this.elementBy).getAttribute(name);
    }

    public boolean isSelected() {
        return this.get(this.elementBy).isSelected();
    }

    public boolean isEnabled() {
        return this.get(this.elementBy).isEnabled();
    }

    public String getText() {
        return this.get(this.elementBy).getText();
    }

    public List<WebElement> findElements(By by) {
        return this.get(this.elementBy).findElements(by);
    }

    public WebElement findElement(By by) {
        return this.get(this.elementBy).findElement(by);
    }

    public boolean isDisplayed() {
        return this.exists(this.elementBy, this.wait);
    }

    public Point getLocation() {
        return this.get(this.elementBy).getLocation();
    }

    public Dimension getSize() {
        return this.get(this.elementBy).getSize();
    }

    public Rectangle getRect() {
        return this.get(this.elementBy).getRect();
    }

    public String getCssValue(String propertyName) {
        return this.get(this.elementBy).getCssValue(propertyName);
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return this.get(this.elementBy).getScreenshotAs(target);
    }

    public void selectOption(String option) {
        (new Select(this.get(this.elementBy))).selectByValue(option);
    }

    public boolean isPresent() {
        return this.present(this.elementBy, this.wait);
    }

    public By getElementBy() {
        return this.elementBy;
    }

    public void scrollToElement() {
        WebElement element = BaseTest.getDriver().findElement(this.elementBy);
        Actions builder = new Actions(BaseTest.getDriver());
        builder.moveToElement(element);
        builder.build().perform();
    }

    public boolean waitUntil(ExpectedCondition expectedCondition) {
        return this.waitUntil(this.wait, expectedCondition);
    }
}
