package com.fiap.revisaoandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;

public class ClimaService extends Service {

    private Timer timer;
    private ClimaTask task;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String cidade = intent.getExtras().getString("cidade");
        Toast.makeText(this, "INICIADO SERVICO: " + cidade, Toast.LENGTH_SHORT).show();
        task = new ClimaTask(cidade, this);

        timer = new Timer();
        timer.schedule(task, 0, 1000 * 15);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        task.cancel();
        timer.cancel();
        Toast.makeText(this, "SERVICO FINALIZADO", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
