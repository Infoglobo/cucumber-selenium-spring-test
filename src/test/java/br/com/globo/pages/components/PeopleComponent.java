package br.com.globo.pages.components;

import br.com.globo.annotations.CucumberPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@CucumberPageObject
public class PeopleComponent {

    @FindBy(xpath = "//nav/a[text()[contains(.,'People')]]/span")
    private WebElement count;
    @FindBy(xpath = "//form/div/input")
    private WebElement findMemberTextBox;
    @FindBy(xpath = "//*[@id='org-members-table']/ul/li")
    private List<WebElement> memberList;

    public Integer size() {

        return Integer.parseInt(count.getText());
    }

    public void searchFor(String name) {

        findMemberTextBox.sendKeys(name);
    }

    public Integer sizeOfVisiblePeople() {

        return memberList.size();
    }
}
