package edu.cczu.ex1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Service_calBMI extends Service {
    public Service_calBMI() {
        Intent intent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
