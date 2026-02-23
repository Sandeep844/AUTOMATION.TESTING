package kuby.web.objectRepositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;

public class CustomerCompanionPage extends TestBase {

    // logger
    public static final Logger log = Logger.getLogger(CustomerCompanionPage.class);

    // Search / filters
    @FindBy(xpath = "//input[@placeholder='Search by name or keyword']")
    private WebElement searchByNameInput;

    @FindBy(xpath = "//button[normalize-space()='Filter']")
    private WebElement filterButton;

    // Companion card list (each card uses data-slot='card')
    @FindBy(xpath = "//div[@data-slot='card']")
    private List<WebElement> companionCards;

    // page heading
    @FindBy(xpath = "//h4[contains(.,'Find a companion')]")
    private WebElement pageHeading;

    public CustomerCompanionPage() {
        PageFactory.initElements(driver, this);
    }

    // Waits for the companion page to be visible
    public boolean isCompanionPageLoaded() {
        try {
            CommonUtilities.elementToBeVisible(pageHeading, 10);
            return true;
        } catch (Exception e) {
            log.info("Companion page heading not visible: " + e.getMessage());
            return false;
        }
    }

    // Search helpers
    public void searchCompanionByName(String name) {
        try {
            WebElement inp = CommonUtilities.elementToBeVisible(searchByNameInput, 10);
            CommonUtilities.sendkeys(inp, name);
            CommonUtilities.threadSleep(1000);
        } catch (Exception e) {
            log.error("Failed to enter search text: " + e.getMessage());
            throw e;
        }
    }

    public void clickFilter() {
        try {
            CommonUtilities.elementToBeClickable(filterButton, 10).click();
        } catch (Exception e) {
            log.error("Failed to click Filter button: " + e.getMessage());
            throw e;
        }
    }

    // Companion list helpers
    public int getCompanionCount() {
        try {
            return (companionCards == null) ? 0 : companionCards.size();
        } catch (Exception e) {
            log.error("Error getting companion count: " + e.getMessage());
            return 0;
        }
    }

    public List<String> getAllCompanionNames() {
        List<String> names = new ArrayList<>();
        try {
            if (companionCards != null) {
                for (WebElement card : companionCards) {
                    try {
                        WebElement nameEl = card.findElement(By.xpath(".//h3"));
                        names.add(nameEl.getText().trim());
                    } catch (NoSuchElementException ne) {
                        // fallback: try a heading inside card
                        try {
                            WebElement alt = card.findElement(By.xpath(".//h2|.//h4|.//p"));
                            names.add(alt.getText().trim());
                        } catch (Exception ex) {
                            names.add("");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while fetching companion names: " + e.getMessage());
        }
        return names;
    }

    public boolean isCompanionPresent(String name) {
        try {
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().trim().equalsIgnoreCase(name.trim()))
                        return true;
                } catch (Exception e) {
                    // ignore
                }
            }
        } catch (Exception e) {
            log.error("Error checking companion presence: " + e.getMessage());
        }
        return false;
    }

    public String getPriceForCompanion(String name) {
        try {
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().trim().equalsIgnoreCase(name.trim())) {
                        WebElement priceEl = card.findElement(By.xpath(".//span[contains(text(),'€') or contains(text()," + "'€')]") );
                        return priceEl.getText().trim();
                    }
                } catch (Exception e) {
                    // continue
                }
            }
        } catch (Exception e) {
            log.error("Error fetching price for companion: " + e.getMessage());
        }
        return "";
    }

    public void clickBookForCompanion(String name) {
        try {
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().trim().equalsIgnoreCase(name.trim())) {
                        WebElement bookBtn = card.findElement(By.xpath(".//button[contains(normalize-space(.),'Book your session') or contains(normalize-space(.),'Book session')]") );
                        CommonUtilities.elementToBeClickable(bookBtn, 10).click();
                        return;
                    }
                } catch (Exception e) {
                    // continue search
                }
            }
            throw new NoSuchElementException("Companion with name '" + name + "' not found to click Book");
        } catch (Exception e) {
            log.error("Error clicking Book for companion: " + e.getMessage());
            throw e;
        }
    }

    public void clickViewProfileOfCompanion(String name) {
        try {
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().trim().equalsIgnoreCase(name.trim())) {
                        WebElement profileLink = card.findElement(By.xpath(".//a[contains(@href,'/companions/') and .//h3]"));
                        CommonUtilities.JSClick(profileLink);
                        return;
                    }
                } catch (Exception e) {
                    // continue
                }
            }
            throw new NoSuchElementException("Companion with name '" + name + "' not found to click View Profile");
        } catch (Exception e) {
            log.error("Error clicking View Profile for companion: " + e.getMessage());
            throw e;
        }
    }

    public void clickFirstCompanionBook() {
        try {
            if (companionCards != null && !companionCards.isEmpty()) {
                WebElement firstCard = companionCards.get(0);
                WebElement bookBtn = firstCard.findElement(By.xpath(".//button[contains(normalize-space(.),'Book your session') or contains(normalize-space(.),'Book session')]") );
                CommonUtilities.elementToBeClickable(bookBtn, 10).click();
            } else {
                throw new NoSuchElementException("No companion cards available to click Book");
            }
        } catch (Exception e) {
            log.error("Error clicking Book for first companion: " + e.getMessage());
            throw e;
        }
    }

    public void clickBookForFirstMatching(String partialName) {
        try {
            if (companionCards == null || companionCards.isEmpty()) {
                throw new NoSuchElementException("No companion cards found on page");
            }
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().toLowerCase().contains(partialName.toLowerCase())) {
                        WebElement bookBtn = card.findElement(By.xpath(".//button[contains(normalize-space(.),'Book your session') or contains(normalize-space(.),'Book session')]") );
                        CommonUtilities.elementToBeClickable(bookBtn, 10).click();
                        return;
                    }
                } catch (Exception e) {
                    // ignore and try next
                }
            }
            throw new NoSuchElementException("No companion matching '" + partialName + "' found to click Book");
        } catch (Exception e) {
            log.error("Error in clickBookForFirstMatching: " + e.getMessage());
            throw e;
        }
    }

}
