package kuby.web.objectRepositories;

import java.time.Duration;
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

/**
 * Page object for the booking/slot selection panel that opens after clicking "Book your session".
 * Implemented defensively using multiple locator fallbacks because the booking modal HTML may vary.
 */
public class BookAComapnionSlotPage extends TestBase {

    public static final Logger log = Logger.getLogger(BookAComapnionSlotPage.class);

    // ============ Locators using @FindBy ============

    @FindBy(xpath = "//button[normalize-space()='Continue' or normalize-space()='Continue to payment']")
    private WebElement continueBtnPrimary;

    @FindBy(xpath = "//button[normalize-space()='Continue to booking']")
    private WebElement continueBtnBooking;

    @FindBy(xpath = "//button[contains(.,'Continue') or contains(.,'continue')]")
    private WebElement continueBtnGeneric;

    @FindBy(xpath = "//div[@role='dialog' or contains(@class,'booking') or contains(@class,'slot')]")
    private WebElement bookingDialog;

    @FindBy(xpath = "//div[contains(@id,'booking') or contains(@data-slot,'booking')]")
    private WebElement bookingPanel;

    @FindBy(xpath = "//div[contains(@class,'dates')]//button")
    private List<WebElement> dateButtonsPrimary;

    @FindBy(xpath = "//div[contains(@class,'dateList')]//button")
    private List<WebElement> dateButtonsSecondary;

    @FindBy(xpath = "//button[contains(@aria-label,'day') or contains(@class,'date')]")
    private List<WebElement> dateButtonsTertiary;

    @FindBy(xpath = "//ul[contains(@class,'dates')]/li/button")
    private List<WebElement> dateButtonsList;

    @FindBy(xpath = "//div[@role='tab' and contains(@aria-label,'Date')]")
    private List<WebElement> dateTabButtons;

    @FindBy(xpath = "//div[contains(@class,'time-slots')]//button")
    private List<WebElement> timeSlotsPrimary;

    @FindBy(xpath = "//div[contains(@class,'slots')]//button")
    private List<WebElement> timeSlotsSecondary;

    @FindBy(xpath = "//button[contains(@class,'time-slot') or contains(@aria-label,'time')]")
    private List<WebElement> timeSlotsTertiary;

    @FindBy(xpath = "//ul[contains(@class,'slots')]/li/button")
    private List<WebElement> timeSlotsList;

    public BookAComapnionSlotPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for booking panel/modal to appear. We try a few common indicators.
     */
    public boolean waitForBookingPanel(int timeoutSeconds) {
        try {
            // Try primary continue button
            CommonUtilities.setExplicitWait(Duration.ofSeconds(timeoutSeconds));
            CommonUtilities.elementToBeVisible(continueBtnPrimary, timeoutSeconds);
            return true;
        } catch (Exception e) {
            // Fallback: wait for booking dialog
            try {
                CommonUtilities.setExplicitWait(Duration.ofSeconds(timeoutSeconds));
                if (bookingDialog != null && bookingDialog.isDisplayed()) {
                    return true;
                }
            } catch (Exception ex) {
                // nothing
            }

            // Fallback: wait for booking panel
            try {
                CommonUtilities.setExplicitWait(Duration.ofSeconds(timeoutSeconds));
                if (bookingPanel != null && bookingPanel.isDisplayed()) {
                    return true;
                }
            } catch (Exception ex) {
                // nothing
            }

            log.info("Booking panel not visible: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns list of date elements (buttons) visible in the booking panel. Tries multiple fallbacks.
     */
    public List<WebElement> getAvailableDateElements() {
        List<WebElement> dates = new ArrayList<>();
        try {
            // Try primary date buttons
            if (dateButtonsPrimary != null && dateButtonsPrimary.size() > 0) {
                return dateButtonsPrimary;
            }

            // Try secondary date buttons
            if (dateButtonsSecondary != null && dateButtonsSecondary.size() > 0) {
                return dateButtonsSecondary;
            }

            // Try tertiary date buttons
            if (dateButtonsTertiary != null && dateButtonsTertiary.size() > 0) {
                return dateButtonsTertiary;
            }

            // Try list-based date buttons
            if (dateButtonsList != null && dateButtonsList.size() > 0) {
                return dateButtonsList;
            }

            // Try tab-based date buttons
            if (dateTabButtons != null && dateTabButtons.size() > 0) {
                return dateTabButtons;
            }
        } catch (Exception e) {
            log.info("Error fetching date elements: " + e.getMessage());
        }
        return dates;
    }

    /**
     * After selecting a date element, fetch available time slot elements for that date.
     */
    public List<WebElement> getTimeSlotsForDate(WebElement dateElement) {
        List<WebElement> slots = new ArrayList<>();
        try {
            // Click the date to load slot times
            CommonUtilities.JSClick(dateElement);
            CommonUtilities.threadSleep(1000);

            // Try primary time slots
            if (timeSlotsPrimary != null && timeSlotsPrimary.size() > 0) {
                return timeSlotsPrimary;
            }

            // Try secondary time slots
            if (timeSlotsSecondary != null && timeSlotsSecondary.size() > 0) {
                return timeSlotsSecondary;
            }

            // Try tertiary time slots
            if (timeSlotsTertiary != null && timeSlotsTertiary.size() > 0) {
                return timeSlotsTertiary;
            }

            // Try list-based time slots
            if (timeSlotsList != null && timeSlotsList.size() > 0) {
                return timeSlotsList;
            }
        } catch (Exception e) {
            log.info("Error fetching slot elements: " + e.getMessage());
        }
        return slots;
    }

    /**
     * Selects the first available slot across dates. Returns true if a slot was selected.
     */
    public boolean selectFirstAvailableSlot() {
        try {
            List<WebElement> dates = getAvailableDateElements();
            if (dates == null || dates.size() == 0) {
                log.info("No dates available to select");
                return false;
            }
            for (WebElement date : dates) {
                try {
                    List<WebElement> slots = getTimeSlotsForDate(date);
                    if (slots != null && slots.size() > 0) {
                        // click first available slot
                        CommonUtilities.elementToBeClickable(slots.get(0), 10).click();
                        log.info("Selected first slot for date");
                        return true;
                    }
                } catch (Exception e) {
                    // try next date
                    log.info("No slots for this date, trying next date: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.info("Error selecting first slot: " + e.getMessage());
        }
        return false;
    }

    /**
     * Click Continue button in booking flow
     */
    public void clickContinue() {
        try {
            CommonUtilities.elementToBeClickable(continueBtnPrimary, 10).click();
            log.info("Clicked Continue button (Primary)");
        } catch (NoSuchElementException ne) {
            // try secondary continue button
            try {
                CommonUtilities.elementToBeClickable(continueBtnBooking, 10).click();
                log.info("Clicked Continue button (Booking)");
            } catch (Exception e) {
                // try generic continue button
                try {
                    CommonUtilities.elementToBeClickable(continueBtnGeneric, 10).click();
                    log.info("Clicked Continue button (Generic)");
                } catch (Exception ex) {
                    throw new RuntimeException("Continue button not found: " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Quick check if any slot is available (returns true if any slots found for any date)
     */
    public boolean isAnySlotAvailable() {
        try {
            List<WebElement> dates = getAvailableDateElements();
            if (dates == null || dates.size() == 0) return false;
            for (WebElement date : dates) {
                List<WebElement> slots = getTimeSlotsForDate(date);
                if (slots != null && slots.size() > 0) return true;
            }
        } catch (Exception e) {
            log.info("Error checking slot availability: " + e.getMessage());
        }
        return false;
    }

}
