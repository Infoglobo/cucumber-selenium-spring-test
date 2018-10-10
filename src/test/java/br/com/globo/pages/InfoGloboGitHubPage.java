package br.com.globo.pages;

import br.com.globo.annotations.CucumberPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@CucumberPageObject
public class InfoGloboGitHubPage {

    @Autowired
    private WebDriver webDriver;
    @Value("${grupo-globo.github.root}")
    private String address;

    @FindBy(css = "#js-pjax-container > div > header > div > nav > a:nth-child(2)")
    private WebElement peopleCategory;

    public void access() {

        webDriver.get(address);
    }

    public void clickOnPeopleCategory() {

        peopleCategory.click();
    }
}
