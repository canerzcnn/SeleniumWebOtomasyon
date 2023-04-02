import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();    //fullscreen
        //Start Scenario
        driver.get("https://www.beymen.com/");
        Thread.sleep(2000);

        WebElement AcceptButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        AcceptButton.click();
        Thread.sleep(1000);

        WebElement CloseModal = driver.findElement(By.xpath("//button[@class = 'o-modal__closeButton bwi-close']"));
        CloseModal.click();
        Thread.sleep(1500);

        WebElement homePage = driver.findElement(By.xpath("//img[@class = 'o-header__logo--img --blue']"));
        String homeCheckboxClass = homePage.getAttribute("class");
        //Check Home Page
        if (homeCheckboxClass.equals("o-header__logo--img --blue")){
            System.out.println("Homepage is checked!");
        }else {
            System.out.println("Homepage is unchecked!");
        }
        Thread.sleep(1500);

        //Excelden veriyi çekme

        String dosyaYolu= "src/test/java/data.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("Sayfa1");

        System.out.println(workbook.getSheet("Sayfa1").getRow(0).getCell(0));
        String cell1 = workbook.getSheet("Sayfa1").getRow(0).getCell(0).toString();
        System.out.println(cell1);
        System.out.println(workbook.getSheet("Sayfa1").getRow(1).getCell(0));
        String cell2 = workbook.getSheet("Sayfa1").getRow(1).getCell(0).toString();
        System.out.println(cell2);

        WebElement searchButton = driver.findElement(By.xpath("//input[@class = 'default-input o-header__search--input']"));
        searchButton.click();
        searchButton.sendKeys(cell1);
        Thread.sleep(500);
        searchButton.clear();
        searchButton.sendKeys(Keys.BACK_SPACE);
        searchButton.sendKeys(Keys.BACK_SPACE);
        searchButton.sendKeys(Keys.BACK_SPACE);
        searchButton.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        searchButton.sendKeys(cell2);
        searchButton.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        WebElement product = driver.findElement(By.xpath("//div[@class = 'm-productImageList']"));
        product.click();
        Thread.sleep(1200);

        //ürün bilgisi
        WebElement productDesc = driver.findElement(By.xpath("//span[@class = 'o-productDetail__description']"));
        String productText = productDesc.getText();
        System.out.println(productText);
        Thread.sleep(1000);
        WebElement productPrice = driver.findElement(By.id("priceNew"));
        String priceText = productPrice.getText();
        System.out.println(priceText);
        Thread.sleep(1000);

        //Size
        WebElement productSize = driver.findElement(By.xpath("//span[@class = 'm-variation__item']"));
        String sizeText = productSize.getText();
        System.out.println(sizeText);
        Thread.sleep(1000);

        if (sizeText.equals("S")){
            productSize.click();
        }else {
            productSize.click();
        }
        Thread.sleep(1000);
        WebElement addToCart = driver.findElement(By.id("addBasket"));
        addToCart.click();
        Thread.sleep(5500);
        //Product Description
        WebElement clickToCart = driver.findElement(By.xpath("//a[@title = 'Sepetim']"));
        clickToCart.click();
        Thread.sleep(1000);
        //Cart Information
        WebElement cartPrice = driver.findElement(By.xpath("//span[@class = 'm-productPrice__salePrice']"));
        String cartPriceText = cartPrice.getText();
        System.out.println(cartPriceText);

        if (priceText.equals(cartPriceText)){
            System.out.println("Product price is True");
        }else {
            System.out.println("Product price is not the same");
        }
        Thread.sleep(1000);

        WebElement addToPiece = driver.findElement(By.xpath("//option[@value = '2']"));
        addToPiece.click();
        Thread.sleep(1500);

        WebElement removedToCart = driver.findElement(By.id("removeCartItemBtn0-key-0"));
        removedToCart.click();
        Thread.sleep(2000);
        WebElement emptyProduct = driver.findElement(By.xpath("//div[@id='emtyCart']//strong[@class = 'm-empty__messageTitle']"));
        String emptyProductText = emptyProduct.getText();
        System.out.println(emptyProductText);   //Sepetin boş olduğu kontrolü

        driver.quit();
    }
}
