package com.fiap.revisaoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtCidade;

    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, ClimaService.class);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
    }

    public void iniciar(View v){
        String cidade = edtCidade.getText().toString();
        i.putExtra("cidade", cidade);
        startService(i);
    }
    public void parar(View v){
        stopService(i);
    }
}
