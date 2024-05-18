package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends PageObject {
    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElementFacade> inventoryItems;

    @FindBy(id = "react-burger-menu-btn")
    private WebElementFacade menuButton;

    @FindBy(className = "shopping_cart_link")
    private WebElementFacade shoppingCartButton;

    public boolean isVisible() {
        waitFor(inventoryItems.get(0));
        return inventoryItems.get(0).isCurrentlyVisible();
    }

    public void clickAddToCartButtonOnItem() { // For testing purposes we use the first item in the list
        waitFor(inventoryItems.get(0));
        WebElementFacade firstItem = inventoryItems.get(0);
        WebElementFacade addToCartButton = firstItem.find(By.className("btn_inventory"));
        addToCartButton.click();
    }

    public int getNumberOfAddedItemsBadgeCount() {
        WebElementFacade shoppingCartBadge = find(By.className("shopping_cart_badge"));
        String badgeText = shoppingCartBadge.getText();
        return Integer.parseInt(badgeText);
    }

    public void clickShoppingCartButton() {
        shoppingCartButton.click();
    }

    public void clickLogoutButton() {
        menuButton.click();
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));
        WebElementFacade menuWrap = find(By.className("bm-menu-wrap"));
        WebElementFacade logoutButton = menuWrap.find(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
}
