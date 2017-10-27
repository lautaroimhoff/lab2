package com.example.francisco.lab02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Lautaro on 20/9/2017.
 */

public class PagoPedido extends AppCompatActivity {
  private EditText enombre;
    private EditText eemail ;
    private EditText etarjeta ;
    EditText enro ;
    EditText efecha ;
    Button confirmar ;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pago_pedido);
       enombre = (EditText) findViewById(R.id.enombre);
         eemail = (EditText) findViewById(R.id.eemail);
        etarjeta = (EditText) findViewById(R.id.etarjeta);
         enro = (EditText) findViewById(R.id.enro);
      efecha = (EditText) findViewById(R.id.efecha);
        confirmar = (Button) findViewById(R.id.bconfirmar);
        confirmar.setOnClickListener(clickBotones);
        cancelar = (Button) findViewById(R.id.bcancelar);
        cancelar.setOnClickListener(clickBotones);

    }
    private View.OnClickListener clickBotones = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bconfirmar:
                    Intent iResultado = getIntent();
                    iResultado.putExtra("nombre" , enombre.getText().toString().trim());
                    iResultado.putExtra("email" , eemail.getText().toString().trim());
                    iResultado.putExtra("tarjeta" , etarjeta.getText().toString().trim());
                    setResult(RESULT_OK,iResultado);
                    finish();
                    break;
                case R.id.bcancelar:
                    setResult(RESULT_CANCELED,null);
                    finish();
            }

        }
    };

}
