package me.dorukozgen.promist.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import me.dorukozgen.promist.managers.DriverManagement;
import me.dorukozgen.promist.pages.MainPage;

public class PromistTest {

    MainPage mainPage;

    @Given("{string} websitesine git")
    public void websitesine_git(String url) {
        mainPage = new MainPage(
                DriverManagement.getInstance().getDriver(),
                DriverManagement.getInstance().getWait()
        );
        mainPage.goToMainPage(url);
    }

    @And("Sağ üst kısımdaki menüye gel")
    public void sag_ust_kisimdaki_menuye_gel() {
        mainPage.checkNavbar();
    }

    @Then("Menüdeki tüm sayfa yönlendirmelerine tiklama yap ve çalıştığını kontrol et")
    public void menudeki_tum_sayfa_yonlendirmelerine_tiklama_yap_ve_calıstigini_kontrol_et() {
        mainPage.clickNavLinks();
    }


    @And("Sayfanın en altına in")
    public void sayfanin_en_altina_in() {
        mainPage.scrollDown();
    }

    @And("\"Bizi Takip Edin\" bölümüne gel")
    public void bizi_takip_edin_bölümüne_gel() {
        mainPage.checkMediasBar();
    }

    @Then("Bölümdeki tüm medya bağlantılarına orta tıklama yap ve çalıştığını kontrol et")
    public void bolumdeki_tum_medya_baglantılarina_orta_tiklama_yap_ve_calıstigini_kontrol_et() {
        mainPage.clickMediaLinks();
    }

}
