package util;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ContextUtil implements ElementLocatorFactory {
    private SearchContext context;
    private List<SearchContext> contextList = new ArrayList<>();


    public ContextUtil(SearchContext context) {
        this.context = context;
    }

    public ContextUtil(List<MobileElement> elementList) {
        List<SearchContext> searchContexts = new ArrayList<>();
        for (MobileElement element: elementList){
            searchContexts.add(element);
        }
        this.contextList = searchContexts;
    }

    public ElementLocator createLocator(final Field field) {
        return new DefaultElementLocator(context, field);
    }


}
