package com.davnicwil.time;

import java.lang.Boolean;import java.lang.Long;

public interface Clock {

    Long now();

    Boolean withinLastMinute(Long timestamp);
}
