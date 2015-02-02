package com.jquery.datepicker;


import com.algocrafts.clickables.Url;
import com.algocrafts.datepicker.Calendar;
import com.algocrafts.datepicker.CalendarBuilder;
import com.algocrafts.datepicker.Datepicker;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;

import java.time.Month;

import static com.algocrafts.conditions.PagePredicates.JQUERY_CALENDAR_NOT_DISPLAYED;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.jquery.datepicker.locators.CalendarClicker.*;
import static com.jquery.datepicker.locators.CalendarIntegerLocator.DISPLAYED_MONTH;
import static com.jquery.datepicker.locators.CalendarIntegerLocator.DISPLAYED_YEAR;
import static com.jquery.datepicker.JQueryDayLocatorFactory.JQUERY_DAY;

public class JQueryDatePickerPage extends Page {

    public JQueryDatePickerPage(Browser<?> browser) {
        super(browser, new Url<>(browser, "http://jqueryui.com/datepicker/"));
    }

    private final Datepicker datepicker = new Datepicker(
            new CalendarBuilder()
                    .setPage(this)
                    .setTrigger(TRIGGER)
                    .setDisplayedYear(DISPLAYED_YEAR)
                    .setDisplayedMonth(DISPLAYED_MONTH)
                    .setPreviousMonth(PREVIOUS_MONTH)
                    .setNextMonth(NEXT_MONTH)
                    .setDayLocatorFactory(JQUERY_DAY)
                    .setCalendarClosed(JQUERY_CALENDAR_NOT_DISPLAYED)
                    .build()
    );

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return new ElementLocator<Page>(DATE_PICKER)
                .andThen(VALUE)
                .locate(this);
    }

}
