package com.davnicwil.time.impl;

import com.davnicwil.time.Clock;
import com.davnicwil.time.CurrentTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClockImplUTest {

    private static final Long ONE_MINUTE = 60000l;
    private static final Long TEN_SECONDS = 10000l;

    private Clock testObj;

    @Mock
    private CurrentTime currentTimeMock;

    @Before
    public void setup() {
        testObj = new ClockImpl(currentTimeMock);
    }

    @Test
    public void itsShouldReportTimesLessThanOneMinuteAgoAreWithinLastMinute() {
        Long currentTime = 10 * ONE_MINUTE;
        mockCurrentTimeAs(currentTime);

        // edge case - exactly one minute ago, plus 1 millisecond
        assertTrue(testObj.withinLastMinute((currentTime - ONE_MINUTE) + 1l));

        // edge case - exactly on current time
        assertTrue(testObj.withinLastMinute(currentTime));

        // non-edge cases
        assertTrue(testObj.withinLastMinute(currentTime - TEN_SECONDS));
        assertTrue(testObj.withinLastMinute(currentTime - (2 * TEN_SECONDS)));
        assertTrue(testObj.withinLastMinute(currentTime - (3 * TEN_SECONDS)));
        assertTrue(testObj.withinLastMinute(currentTime - (4 * TEN_SECONDS)));
        assertTrue(testObj.withinLastMinute(currentTime - (5 * TEN_SECONDS)));
    }

    @Test
    public void itsShouldReportTimesInTheFutureAreNotWithinLastMinute() {
        mockCurrentTimeAs(0l);

        assertFalse(testObj.withinLastMinute(1l));
        assertFalse(testObj.withinLastMinute(10l));
        assertFalse(testObj.withinLastMinute(100l));
        assertFalse(testObj.withinLastMinute(1000l));
        assertFalse(testObj.withinLastMinute(10000l));
        assertFalse(testObj.withinLastMinute(100000l));
        assertFalse(testObj.withinLastMinute(1000000l));
        assertFalse(testObj.withinLastMinute(10000000l));
        assertFalse(testObj.withinLastMinute(100000000l));
        assertFalse(testObj.withinLastMinute(1000000000l));
        assertFalse(testObj.withinLastMinute(10000000000l));
    }

    @Test
    public void itsShouldReportTimesMoreThanOneMinuteAgoAreNotWithinLastMinute() {
        Long currentTime = 10 * ONE_MINUTE;
        mockCurrentTimeAs(currentTime);

        // edge case - exactly one minute ago
        assertFalse(testObj.withinLastMinute(currentTime - ONE_MINUTE));

        // non-edge cases
        assertFalse(testObj.withinLastMinute(currentTime - ( 2 * ONE_MINUTE)));
        assertFalse(testObj.withinLastMinute(currentTime - ( 3 * ONE_MINUTE)));
        assertFalse(testObj.withinLastMinute(currentTime - ( 4 * ONE_MINUTE)));
        assertFalse(testObj.withinLastMinute(currentTime - ( 5 * ONE_MINUTE)));
    }

    private void mockCurrentTimeAs(Long timestamp) {
        when(currentTimeMock.get()).thenReturn(timestamp);
    }

}