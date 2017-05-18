package com.binupjewelry.test.seleniumTests;

import org.testng.annotations.Test;
import com.binupjewelry.test.enums.Products;
import com.binupjewelry.test.pages.CartPage;
import com.binupjewelry.test.pages.CheckOutPage;
import com.binupjewelry.test.pages.HomePage;
import com.binupjewelry.test.pages.LoginPage;
import com.binupjewelry.test.pages.ProductDetailPage;
import com.binupjewelry.test.pages.SearchPage;
import com.binupjewelry.test.pages.SecureCheckOutPage;
import com.binupjewelry.test.pojo.Authentication;
import com.binupjewelry.test.pojo.CreditCardInfo;
import com.binupjewelry.test.pojo.ShippingInfo;
import com.binupjewelry.framework.utilities.HelperUtil;

/**
 * Created by binup on 5/13/2017.
 */
public class SearchAndBuyRingTest extends TestAnnotations {
	
	@Test 		
    public void searchAndBuyRing() throws InterruptedException {    	
    	System.out.println("\n EXECUTING TEST--> Search and Buy Ring");    	
    	
    	Products testProduct=Products.PandoraSilverRoseRing;
    	
    	//Instantiating POJO objects
    	Authentication testUser=new Authentication();
    	ShippingInfo testShippingInfo= new ShippingInfo();
    	CreditCardInfo creditCardInfo=new CreditCardInfo();
    	
    	//Instantiating Page objects
    	HomePage homePage= new HomePage();
    	LoginPage loginPage = new LoginPage();
    	SearchPage searchPage = new SearchPage();    	
    	ProductDetailPage productDetailPage=new ProductDetailPage();
    	CartPage cartPage=new CartPage();
    	CheckOutPage checkOutPage=new CheckOutPage();
    	SecureCheckOutPage secureCheckOutPage=new SecureCheckOutPage();
    	//OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();    	
        
    	homePage.clickSignIn();        
        loginPage.Login(testUser.getUserName(), testUser.getPassWord());
        HelperUtil.waitForElementToBeVisible(homePage.lnkSignOut);
        
        homePage.selectHomeMenu();
        homePage.searchProduct(testProduct.getProductTitle());        
        System.out.println("\nSearch result number: "+searchPage.getSearchResultNumber());
        searchPage.clickDetails();

        productDetailPage.verifyProductTitle(testProduct.getProductTitle());
        productDetailPage.clickBuy();     
        cartPage.clickCheckOut();
        cartPage.verifyClickCheckOut();
        
        checkOutPage.clickAddAddress();        
        checkOutPage.createShippingInfo(testShippingInfo.getFirstName(), testShippingInfo.getLastName(), testShippingInfo.getAddressLine1(), testShippingInfo.getCity(), testShippingInfo.getCountry(), testShippingInfo.getState(), testShippingInfo.getZip());
        
        checkOutPage.clickContinueToShippingOptions();        
        checkOutPage.clickContinueToPayment();
        
        secureCheckOutPage.completeCreditCardInformation(creditCardInfo.getNameOnCard(), creditCardInfo.getCardNumber(), creditCardInfo.getExpiryMM(), creditCardInfo.getExpiryYYYY(), creditCardInfo.getCVC(), creditCardInfo.getZip());
        
        secureCheckOutPage.clickCompleteOrder();
        secureCheckOutPage.checkRadioTermsAndConditions();
        secureCheckOutPage.clickCompleteOrder();        
        
        String currentURL= HelperUtil.getCurrentURL();
		
        //Take screenshot at the end
        HelperUtil.grabScreenShot("TCsearchAndBuyRing");
       
    }
}
