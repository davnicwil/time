package com.davnicwil.time.impl;


import com.davnicwil.time.CurrentTime;

public class CurrentTimeImpl implements CurrentTime {

    public Long get() { return System.currentTimeMillis(); }
}
