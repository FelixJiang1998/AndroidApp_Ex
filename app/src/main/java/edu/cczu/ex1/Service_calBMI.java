package edu.cczu.ex1;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.NumberFormat;

public class Service_calBMI extends Service {
    private Binder myBinder=new LocalBinder();

    public class LocalBinder extends Binder {
        Service_calBMI getService(){
            return Service_calBMI.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    public double cal_BMI(double h, double w) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        double res=w/h/h;
        return Double.parseDouble(nf.format(res));
    }
}
