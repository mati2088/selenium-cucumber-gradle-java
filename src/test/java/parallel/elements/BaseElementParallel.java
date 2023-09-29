package parallel.elements;

 import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.hooks.BaseTest;

import java.util.logging.Logger;

public class BaseElementParallel {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BaseElementParallel.class));

    private final WebDriver driver = BaseTest.getDriver();
    private final WebDriverWait driverWait;

    protected BaseElementParallel() {
        this.driverWait = new WebDriverWait(this.driver, 5000L); // Usando un valor de espera de 5000 milisegundos (5 segundos)
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    protected WebDriverWait getDriverWait() {
        return this.driverWait;
    }

    protected boolean exists(By elementBy) {
        return this.exists(elementBy, (Wait)null);
    }

    protected boolean exists(By elementBy, boolean withWait) {
        return withWait ? this.exists(elementBy, this.getDriverWait()) : this.exists(elementBy, (Wait)null);
    }

    protected boolean exists(By elementBy, Wait wait) {
        boolean exists = false;

        try {
            if (wait == null) {
                exists = this.get(elementBy, false) != null;
            } else {
                exists = (Boolean)wait.until(ExpectedConditions.and(new ExpectedCondition[]{ExpectedConditions.presenceOfElementLocated(elementBy), ExpectedConditions.visibilityOfElementLocated(elementBy)}));
            }
        } catch (NoSuchElementException var5) {
            LOGGER.info("No such Element: " + elementBy);
        } catch (TimeoutException var6) {
            var6.printStackTrace();
        }

        return exists;
    }

    protected boolean waitUntil(WebDriverWait wait, ExpectedCondition<?> expectedCondition) {
        boolean condition = false;

        try {
            condition = (Boolean)wait.until(ExpectedConditions.and(new ExpectedCondition[]{expectedCondition}));
        } catch (TimeoutException | NoSuchElementException var5) {
            LOGGER.info(var5.getMessage());
        }

        return condition;
    }

    protected WebElement get(By elementBy) {
        return this.get(elementBy, false, (Wait)null);
    }

    protected WebElement get(By elementBy, boolean withWait) {
        return withWait ? this.get(elementBy, true, this.getDriverWait()) : this.get(elementBy, false, (Wait)null);
    }

    protected WebElement get(By elementBy, Wait wait) {
        return this.get(elementBy, true, wait);
    }

    private WebElement get(By elementBy, boolean withWait, Wait wait) {
        int tryCount = 0;
        byte maxTries = 6;

        while(tryCount <= maxTries) {
            try {
                if (!withWait) {
                    return this.getDriver().findElement(elementBy);
                }

                return (WebElement)wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            } catch (StaleElementReferenceException var9) {
                var9.printStackTrace();

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException var8) {
                    var8.printStackTrace();
                }

                ++tryCount;
            }
        }

        return null;
    }

    protected boolean present(By elementBy, Wait wait) {
        boolean exists = false;

        try {
            if (wait == null) {
                exists = this.get(elementBy, false) != null;
            } else {
                exists = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy)) != null;
            }
        } catch (NoSuchElementException var5) {
            LOGGER.info("No such Element: " + elementBy);
        } catch (TimeoutException var6) {
            var6.printStackTrace();
        }

        return exists;
    }
}
