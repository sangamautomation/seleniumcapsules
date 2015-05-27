package com.algocrafts.locators;

import com.algocrafts.conditions.HasElements;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.algocrafts.selectors.TagName.OPTION;
import static org.slf4j.LoggerFactory.getLogger;


public class SelectLocator<T extends SearchScope<T>>
        extends Locators<T, Select> {

    private static final Logger log = getLogger(SelectLocator.class);

    public SelectLocator(Supplier<By> selector) {
        super((T where) -> {
            final Element element = where.untilFound(selector);
            try {
                element.until(Locators.<Element>elements(OPTION).and(new HasElements<>()));
                return new Select(element);
            } catch (NoSuchElementException e) {
                element.click();
                where.onTimeout();
                log.error("Timeout waiting for the option list to populate.", e);
                throw e;
            }
        });
    }
}
