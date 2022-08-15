package utils;

import pages.onboarding.CategoryPage;
import pages.onboarding.CitySelectPage;
import pages.onboarding.LocalitySelectPage;
import pages.onboarding.WelcomePage;
import util.ActionUtils;

public class AppUtil {

    public void onboarding()  {
        WelcomePage welcomePage = new WelcomePage();
        CategoryPage categoryPage = new CategoryPage();
        CitySelectPage citySelectPage = new CitySelectPage();
        LocalitySelectPage localitySelectPage = new LocalitySelectPage();
        ActionUtils.clickButton(welcomePage.getHomeSearch());
        ActionUtils.clickButton(categoryPage.getBuy());
        ActionUtils.sendText(citySelectPage.getCityTextBox(), "Delhi");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(citySelectPage.getElements(),"Delhi"));
//        ActionUtils.clickButton(citySelectPage.getElements().get(0));
        ActionUtils.clickButton(localitySelectPage.getSkip());
    }
}
