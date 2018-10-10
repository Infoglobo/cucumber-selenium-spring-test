package br.com.globo.steps;

import br.com.globo.pages.InfoGloboGitHubPage;
import br.com.globo.pages.components.PeopleComponent;
import br.com.globo.support.WebDriverAssertions;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TEN_SECONDS;

public class FindMemberOrganizationSteps {

    @Autowired
    private WebDriverAssertions webDriverAssertions;
    @Autowired
    private InfoGloboGitHubPage infoGloboGitHub;
    @Autowired
    private PeopleComponent peopleComponent;

    @Dado("^que o usuário esteja na página da Infoglobo$")
    public void a() {

        infoGloboGitHub.access();

        assertThat(webDriverAssertions.evaluatePageTitle(title -> title.equals("Editora Globo · GitHub"))).isTrue();
    }

    @Quando("^clicar na categoria Pessoas$")
    public void b() {

        infoGloboGitHub.clickOnPeopleCategory();

        await().atMost(TEN_SECONDS).untilAsserted(() -> assertThat(peopleComponent.size()).isOne());
    }

    @E("^procurar por \"([^\"]*)\"$")
    public void c(String name) {

        peopleComponent.searchFor(name);
    }

    @Entao("^deve retornar um único membro$")
    public void d() {

        await().atMost(TEN_SECONDS).untilAsserted(() -> assertThat(peopleComponent.sizeOfVisiblePeople()).isOne());
    }
}
