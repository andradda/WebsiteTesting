package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.ShoppingCartPage;
import org.example.utils.Configuration;
import org.junit.Assert;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    private LoginPage loginPage;
    private HomePage homePage;
    private ShoppingCartPage shoppingCartPage;

    @Step
    public  void logsIn(String username, String password) {
        loginPage.open();
        loginPage.setUsernameField(username);
        loginPage.setPasswordField(password);
        loginPage.clickLoginButton();
    }

    @Step
    public void checkLoginSuccessful() {
        Assert.assertTrue(homePage.isVisible());
        Assert.assertEquals(Configuration.BASE_URL + "inventory.html", getDriver().getCurrentUrl());
    }

    @Step
    public void checkLoginFailed() {
        Assert.assertTrue(loginPage.loginErrorMessageIsVisible());
    }

    @Step
    public void addItemToCart() {
        homePage.clickAddToCartButtonOnItem();
    }

    @Step
    public void checkAddItemToCartSuccessful() {
        Assert.assertEquals(1, homePage.getNumberOfAddedItemsBadgeCount());
        homePage.clickShoppingCartButton();
        Assert.assertEquals(1, shoppingCartPage.getQuantityOfShoppingCart());
    }

    @Step
    public void logsOut() {
        homePage.clickLogoutButton();
    }

    @Step
    public void checkLogoutSuccessful() {
        Assert.assertEquals(Configuration.BASE_URL, getDriver().getCurrentUrl());
    }

}