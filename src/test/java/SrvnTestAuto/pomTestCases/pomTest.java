//package SrvnTestAuto.pomTestCases;
//
//import java.util.List;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//import SrvnTestAuto.pageObjects.CartPage;
//import SrvnTestAuto.pageObjects.CheckOutPage;
//import SrvnTestAuto.pageObjects.HomePage;
//import SrvnTestAuto.pageObjects.LoginPage;
//import SrvnTestAuto.pageObjects.OrderHistoryPage;
//import SrvnTestAuto.pageObjects.OrderSummaryPage;
//import SrvnTestAuto.testComponents.BaseTest;
//
//public class pomTest extends BaseTest {
//	List<String> orderNos;
//	String userName = "srvnauto@test.com";
//	String password = "Srvnauto@1991";
//
//	@Test
//	public void createOrder() {
//
//		String productName = "ADIDAS ORIGINAL";
//		String country = "India";
//
//		LoginPage login = new LoginPage(getDriver());
//
//		HomePage home = login.loginToApp(userName, password);
//		home.AddProductToCart(productName);
//		CartPage cart = home.moveToCart();
//		Assert.assertTrue(cart.isProductPresentInCart(productName));
//		CheckOutPage checkOut = cart.moveToCheckOutPage();
//		checkOut.selectCountry(country);
//		OrderSummaryPage orderSummary = checkOut.submitOrder();
//		orderNos = orderSummary.getOrderNumbers();
//	}
//
//	@Test(dependsOnMethods = {"createOrder"})
//	public void checkOrderNoInOrderSummary() {
//		LoginPage login = new LoginPage(getDriver());
//
//		HomePage home = login.loginToApp(userName, password);
//		OrderHistoryPage orderhistory = home.moveToOrders();
//		
//		SoftAssert sAssert = new SoftAssert();
//		for (String string : orderNos) {
//			boolean match = orderhistory.checkOrderIdInHistory(string);
//			sAssert.assertTrue(match,string+" should be found in the list, but not");
//		}
//		sAssert.assertAll();
//	}
//
//}
