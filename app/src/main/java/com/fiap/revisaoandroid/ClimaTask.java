package com.fiap.revisaoandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TimerTask;

public class ClimaTask extends TimerTask{

    private String cidade;
    private Context context;

    public ClimaTask(String cidade, Context context){
        this.cidade = cidade;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            JSONArray a = new JSONArray(BackEnd.JSON);
            for (int i = 0; i < a.length(); i++){
                JSONObject obj = a.getJSONObject(i);
                if (obj.getString("cidade").equalsIgnoreCase(this.cidade)){
                    validarUmidade(obj.getInt("umidade"));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void validarUmidade(int umidade){

        String txt = "";
        if (umidade > 50){
            txt = "Levar Guarda-chuva";
        }else{
            txt = "Levar Água";
        }
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setContentTitle("Aviso Clima");
        b.setContentText(txt);
        b.setSmallIcon(android.R.drawable.btn_star);

        Notification n = b.build();

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, n);
    }
}
