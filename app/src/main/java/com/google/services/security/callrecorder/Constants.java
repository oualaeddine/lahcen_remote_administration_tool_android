package com.google.services.security.callrecorder;

/**
 * Created by ouala_eddine on 2/1/2019.
 * Project : lahcen.
 */
public class Constants {
    public static final String TAG = "Call recorder";

    public static final String FILE_NAME_PATTERN = "^[\\d]{14}_[_\\d]*\\..+$";

    public static final int STATE_INCOMING_NUMBER = 1;
    public static final int STATE_CALL_START = 2;
    public static final int STATE_CALL_END = 3;
    public static final int RECORDING_ENABLED = 4;
    public static final int RECORDING_DISABLED = 5;
}
