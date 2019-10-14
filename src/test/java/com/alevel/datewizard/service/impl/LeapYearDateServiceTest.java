package com.alevel.datewizard.service.impl;

import com.alevel.datewizard.model.DateOfYear;
import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeapYearDateServiceTest {
    private LeapYearDateService defaultService;

    @BeforeEach
    void setUp() {
        defaultService = new LeapYearDateService(DayOfWeek.MON);
    }

    @Test
    void upperThresholdIs366() {
        defaultService = new LeapYearDateService(DayOfWeek.MON);
        assertEquals(366,defaultService.getUpperThreshold());
    }

    @Test
    void getMonthLengthShouldReturnValueForLeapYear() {
        assertEquals(31,defaultService.getMonthLength(Month.JAN));
        assertEquals(29,defaultService.getMonthLength(Month.FEB));
        assertEquals(31,defaultService.getMonthLength(Month.MAR));
        assertEquals(30,defaultService.getMonthLength(Month.APR));
        assertEquals(31,defaultService.getMonthLength(Month.MAY));
        assertEquals(30,defaultService.getMonthLength(Month.JUN));
        assertEquals(31,defaultService.getMonthLength(Month.JUL));
        assertEquals(31,defaultService.getMonthLength(Month.AUG));
        assertEquals(30,defaultService.getMonthLength(Month.SEP));
        assertEquals(31,defaultService.getMonthLength(Month.OCT));
        assertEquals(30,defaultService.getMonthLength(Month.NOV));
        assertEquals(31,defaultService.getMonthLength(Month.DEC));
    }

    @Test
    void whenDayNumberLessThanZero_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(0));
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(-1));
    }

    @Test
    void whenGreaterThan366_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(367));
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(368));
    }

    @Test
    void whenDayIs61_shouldBeMarch1st() {
        final DateOfYear dateOfYear = defaultService.getDateOfYear(61);
        assertEquals(1,dateOfYear.getDayOfMonth());
    }
}
