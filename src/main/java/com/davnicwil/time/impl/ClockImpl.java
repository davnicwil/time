package com.davnicwil.time.impl;


import com.davnicwil.time.Clock;
import com.davnicwil.time.CurrentTime;
import com.google.inject.Inject;

public class ClockImpl implements Clock {

    private CurrentTime currentTime;

    @Inject
    public ClockImpl(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean withinLastMinute(Long timestamp) {
        Long interval = currentTime.get() - timestamp;
        return 	0 <= interval && interval < 60000l;
    }
}
