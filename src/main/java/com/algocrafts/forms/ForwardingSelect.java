package com.algocrafts.forms;

import com.algocrafts.locators.SelectLocator;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class ForwardingSelect<Where extends SearchScope<Where>>
        extends Locating<Where, Select> {

    private static final Logger log = getLogger(ForwardingSelect.class);

    /**
     * Constructor of the Select, It is a wrapper for the Select from
     * Selenium UI.
     *
     * @param where
     * @param locator
     */
    public ForwardingSelect(Where where, SelectLocator<Where> locator) {
        super(where, locator);
    }

    public void selectByVisibleText(Object text) {
        log.info("selecting " + this + " using [" + text + "]");
        locate().selectByVisibleText(text.toString());
        if (!locate().getFirstSelectedOption().getAttribute("value").equals(text.toString())) {
            locate().getOptions().stream().filter( (WebElement e) -> e.getText().equals(text.toString())).findFirst().get().click();
        }
}

    public boolean isMultiple() {
        return locate().isMultiple();
    }

    public Stream<Element> getOptions() {
        return locate().getOptions().stream().map(Element::new);
    }

    public Stream<Element> getAllSelectedOptions() {
        return locate().getAllSelectedOptions().stream().map(Element::new);
    }

    public Element getFirstSelectedOption() {
        return new Element(locate().getFirstSelectedOption());
    }

    public void selectByEnumOrdinal(Enum index) {
        locate().selectByIndex(index.ordinal());
    }

    public void selectByIndex(int index) {
        locate().selectByIndex(index);
    }

    public void selectByValue(Object value) {
        locate().selectByValue(value.toString());
    }

    public void deselectAll() {
        locate().deselectAll();
    }

    public void deselectByValue(Object value) {
        locate().deselectByValue(value.toString());
    }

    public void deselectByEnumOrdinal(Enum index) {
        locate().deselectByIndex(index.ordinal());
    }

    public void deselectByIndex(int index) {
        locate().deselectByIndex(index);
    }

    public void deselectByVisibleText(Object text) {
        locate().deselectByVisibleText(text.toString());
    }
}
