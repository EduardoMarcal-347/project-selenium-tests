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
        List <WebElement> tds = driver.findElements(By.cssSelector("td"));
        String resultados[][] = {
                {"Conceição Evaristo","pequenos","conceicao@gmail.com","R$3500.00"},
                {"Erica Queiroz Pinto","grandes","erica@gmail.com", "R$4500.00"}
        };
        int i = 0;

        for (WebElement td : tds){
            assertEquals(resultados[i][0], tds.get(0).getText());
            assertEquals(resultados[i][1], tds.get(1).getText());
            assertEquals(resultados[i][2], tds.get(2).getText());
            assertEquals(resultados[i][3], tds.get(3).getText());
        }
    }

    @Test
    @DisplayName("Realiza Pesquisa de um veterinario")
    public void pesquisaVeterinario(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/find");
        WebElement nome = driver.findElement(By.id("nome"));
        WebElement btn = driver.findElement(By.cssSelector("button"));
        nome.sendKeys("Conceição Evaristo");
        btn.submit();
        WebElement campoPesquisado = driver.findElement(By.cssSelector("td:nth-child(2) > span"));

        assertEquals(campoPesquisado.getText(), "Conceição Evaristo");
    }

    @Test
    @DisplayName("Inserir novo veterinario")
    public void inserirNovoVeterinario(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/form");
        String[] novoVet = {"Paulo Marcos", "Tratador de cavalos", "pm@gmail.com",  "10000"};

        WebElement nome = driver.findElement(By.id("nome"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement especialidade = driver.findElement(By.id("inputEspecialidade"));
        WebElement salario = driver.findElement(By.id("inputSalario"));
        WebElement btn = driver.findElement(By.cssSelector("button"));
        nome.sendKeys(novoVet[0]);
        especialidade.sendKeys(novoVet[1]);
        email.sendKeys(novoVet[2]);
        salario.sendKeys(novoVet[3]);
        btn.submit();

        WebElement trInserida = driver.findElement(By.cssSelector("tr:nth-child(4)"));
        List <WebElement> tds = trInserida.findElements(By.cssSelector("td"));

        assertEquals(novoVet[0] ,tds.get(0).getText());
        assertEquals(novoVet[1] ,tds.get(1).getText());
        assertEquals(novoVet[2] ,tds.get(2).getText());
        assertEquals("R$"+novoVet[3].concat(".00") ,tds.get(3).getText());
    }



}
