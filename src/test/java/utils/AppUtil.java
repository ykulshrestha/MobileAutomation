package utils;

import pages.onboarding.CategoryPage;
import pages.onboarding.CitySelectPage;
import pages.onboarding.LocalitySelectPage;
import pages.onboarding.WelcomePage;
import util.ActionUtils;

public class AppUtil {

    public void buyerOnboarding(String city)  {
        WelcomePage welcomePage = new WelcomePage();
        CategoryPage categoryPage = new CategoryPage();
        CitySelectPage citySelectPage = new CitySelectPage();
        LocalitySelectPage localitySelectPage = new LocalitySelectPage();
        ActionUtils.clickButton(welcomePage.getHomeSearch());
        ActionUtils.clickButton(categoryPage.getBuy());
        ActionUtils.sendText(citySelectPage.getCityTextBox(), city);
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(citySelectPage.getElements(), city));
//        ActionUtils.clickButton(citySelectPage.getElements().get(0));
        ActionUtils.clickButton(localitySelectPage.getSkip());
    }
}
