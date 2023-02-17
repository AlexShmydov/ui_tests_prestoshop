import core.pages.main.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Add products to cart")
public class AddProductsToCartTest extends BaseTest {

    private static final int PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE = 0;
    private static final int SECOND_PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE = 1;
    private static final int PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE = 0;
    private static final int SECOND_PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE = 1;
    private static final int EXPECTED_DEFAULT_QUANTITY = 1;
    private static final int EXPECTED_INCREASED_QUANTITY = 2;
    private static final int EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART = 1;
    private static final int EXPECTED_AMOUNT_OF_TWO_ITEMS_IN_CART = 2;

    @Autowired
    private MainPage mainPage;

    @Tags({
            @Tag("Cart"),
            @Tag("QuickView"),
            @Tag("123")
    })
    @Test
    void addDifferentProductsToCardViaQuickView() {
        mainPage.isProductsAvailable()
                .clickOnQuickViewOfProduct(PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE)
                .clickOnQuantityUp()
                .clickOnAddToCart()
                .clickOnContinueShopping();
        mainPage.clickOnQuickViewOfProduct(SECOND_PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE)
                .clickOnQuantityUp()
                .clickOnAddToCart()
                .clickOnProceedToCheckout()
                .checkAmountOfItemsInCart(EXPECTED_AMOUNT_OF_TWO_ITEMS_IN_CART)
                .checkAmountOfProductsInItem(EXPECTED_INCREASED_QUANTITY, PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE)
                .checkAmountOfProductsInItem(EXPECTED_INCREASED_QUANTITY, SECOND_PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE)
                .clickOnProceedToCheckout();
    }

    @Tags({
            @Tag("Cart"),
            @Tag("QuickView"),
            @Tag("456")
    })
    @Test
    void addProductsToCardViaQuickView() {
        mainPage.isProductsAvailable()
                .clickOnQuickViewOfProduct(PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE)
                .checkQuantityOfProductToAdd(EXPECTED_DEFAULT_QUANTITY)
                .clickOnQuantityUp()
                .clickOnQuantityUp()
                .clickOnQuantityDown()
                .checkQuantityOfProductToAdd(EXPECTED_INCREASED_QUANTITY)
                .clickOnAddToCart()
                .checkQuantityFieldValue(EXPECTED_INCREASED_QUANTITY)
                .clickOnProceedToCheckout()
                .checkAmountOfItemsInCart(EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART)
                .checkAmountOfProductsInItem(EXPECTED_INCREASED_QUANTITY, PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE)
                .clickOnProceedToCheckout();
    }

    @Tags({
            @Tag("Cart"),
            @Tag("Product"),
            @Tag("789")
    })
    @Test
    void addProductsToCardViaProductPage() {
        mainPage.isProductsAvailable()
                .clickOnProduct(PRODUCT_INDEX_TO_SELECT_ON_MAIN_PAGE)
                .clickOnQuantityUp()
                .checkQuantityOfProductToAdd(EXPECTED_INCREASED_QUANTITY)
                .clickOnAddToCart()
                .checkQuantityFieldValue(EXPECTED_INCREASED_QUANTITY)
                .clickOnProceedToCheckout()
                .checkAmountOfItemsInCart(EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART)
                .checkAmountOfProductsInItem(EXPECTED_INCREASED_QUANTITY, PRODUCT_INDEX_TO_SELECT_ON_CART_PAGE)
                .clickOnProceedToCheckout();
    }
}
