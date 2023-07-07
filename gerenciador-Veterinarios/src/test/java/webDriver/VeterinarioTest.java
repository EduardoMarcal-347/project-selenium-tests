package webDriver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VeterinarioTest {

    @Test
    @DisplayName("Verifica Veterinarios Listados")
    public void listaVeterinarios(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/home");
        List<WebElement> tr1 = driver.findElements(By.cssSelector("tbody > tr"));
        String resultados[][] = {
                {"Conceição Evaristo","pequenos","conceicao@gmail.com","R$3500.00"},
                {"Erica Queiroz Pinto","grandes","erica@gmail.com", "R$4500.00"}
        };
        int i = 0;

        for (WebElement tr : tr1){
            List <WebElement> tds = driver.findElements(By.cssSelector("td"));
            assertEquals(resultados[i][0], tds.get(0).getText());
            assertEquals(resultados[i][1], tds.get(1).getText());
            assertEquals(resultados[i][2], tds.get(2).getText());
            assertEquals(resultados[i][3], tds.get(3).getText());
        }
    }

    

}
