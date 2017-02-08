package es.cice.androidstackexchange.events;

import android.database.Cursor;

/**
 * Created by cice on 7/2/17.
 */

public class NewDataEvent {
    private Cursor cursor;

    public NewDataEvent(Cursor cursor) {
        this.cursor = cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setC(Cursor cursor) {
        this.cursor = cursor;
    }
}
