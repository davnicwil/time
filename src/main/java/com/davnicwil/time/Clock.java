package com.davnicwil.time;

import java.lang.Boolean;import java.lang.Long;public interface Clock {

    Boolean withinLastMinute(Long timestamp);
}
