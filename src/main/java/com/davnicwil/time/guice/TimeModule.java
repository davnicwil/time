package com.davnicwil.time.guice;

import com.davnicwil.time.Clock;
import com.davnicwil.time.CurrentTime;
import com.davnicwil.time.impl.ClockImpl;
import com.davnicwil.time.impl.CurrentTimeImpl;
import com.google.inject.AbstractModule;

public class TimeModule extends AbstractModule {

    protected void configure() {
        bind(CurrentTime.class).to(CurrentTimeImpl.class);
        bind(Clock.class).to(ClockImpl.class);
    }
}
