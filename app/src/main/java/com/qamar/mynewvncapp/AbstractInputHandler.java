package com.qamar.mynewvncapp;

import android.view.KeyEvent;
import android.view.MotionEvent;

public interface AbstractInputHandler {
    /**
     * Note: Menu key code is handled before this is called
     * @see android.app.Activity#onKeyDown(int keyCode, KeyEvent evt)
     */
    boolean onKeyDown(int keyCode, KeyEvent evt);
    /**
     * Note: Menu key code is handled before this is called
     * @see android.app.Activity#onKeyUp(int keyCode, KeyEvent evt)
     */
    boolean onKeyUp(int keyCode, KeyEvent evt);
    /* (non-Javadoc)
     * @see android.app.Activity#onTrackballEvent(android.view.MotionEvent)
     */
    boolean onTrackballEvent( MotionEvent evt);
    /* (non-Javadoc)
     * @see android.app.Activity#onTrackballEvent(android.view.MotionEvent)
     */
    boolean onTouchEvent( MotionEvent evt);

    /**
     * Return a user-friendly description for this mode; it will be displayed in a toaster
     * when changing modes.
     * @return
     */
    CharSequence getHandlerDescription();

    /**
     * Return an internal name for this handler; this name will be stable across language
     * and version changes
     */
    String getName();
}
