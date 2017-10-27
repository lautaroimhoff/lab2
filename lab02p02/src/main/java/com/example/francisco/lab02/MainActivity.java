package com.example.francisco.lab02;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import modelo.*;

public class MainActivity extends AppCompatActivity {
    static int codigoPago = 1;
    Utils.ElementoMenu elementoSeleccionado;
    ListView lista;
    Double precio;
    TextView pedidoActual;
    ArrayList<Utils.ElementoMenu> listapedidos = new ArrayList<>();
    boolean confirmado = false;
    Pedido pedido = new Pedido();
    Switch avisar;
    Spinner desplegable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button agregar = (Button) findViewById(R.id.button);
        Button confirmarpedido = (Button) findViewById(R.id.button2);
        Button reiniciar = (Button) findViewById(R.id.button3);

        RadioButton plato = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton postre = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton bebida = (RadioButton) findViewById(R.id.radioButton6);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.radioGroup);
        pedidoActual = (TextView) findViewById(R.id.pedidoActual);
        pedidoActual.setMovementMethod(new ScrollingMovementMethod());

        lista = (ListView) findViewById(R.id.listview);
        avisar = (Switch) findViewById(R.id.switch1);
         desplegable = (Spinner) findViewById(R.id.spinner);

        String[] datos = {"20:00", "20:30", "21:00", "21:30", "22:00", "22:30"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        desplegable.setAdapter(adaptador);
        final String[] seleccionados = new String[0]; 
        ToggleButton elegir = (ToggleButton) findViewById(R.id.toggleButton);
        Utils utilidades = new Utils();
        utilidades.iniciarListas();


        final ArrayAdapter<Utils.ElementoMenu> adaptador2 = new ArrayAdapter<Utils.ElementoMenu>(this,android.R.layout.simple_list_item_single_choice,utilidades.getListaPlatos());
        final ArrayAdapter<Utils.ElementoMenu> adaptador3  = new ArrayAdapter<Utils.ElementoMenu>(this,android.R.layout.simple_list_item_single_choice,utilidades.getListaPostre());
        final ArrayAdapter<Utils.ElementoMenu> adaptador4 = new ArrayAdapter<Utils.ElementoMenu>(this,android.R.layout.simple_list_item_single_choice,utilidades.getListaBebidas());


        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                elementoSeleccionado = null;
                if (checkedId==R.id.radioButton4){
                    lista.setAdapter(null);
                    lista.setAdapter(adaptador2);}

                if (checkedId==R.id.radioButton5){
                    lista.setAdapter(null);
                    lista.setAdapter(adaptador3);}

                if (checkedId==R.id.radioButton6){
                    lista.setAdapter(null);
                    lista.setAdapter(adaptador4);}

            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                elementoSeleccionado = (Utils.ElementoMenu)adapterView.getItemAtPosition(i);
                
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean agregado = agregado(elementoSeleccionado);
                if(elementoSeleccionado != null && !confirmado && !agregado){
                    listapedidos.add(elementoSeleccionado);
                    pedidoActual.setText(pedidoActual.getText() + elementoSeleccionado.toString() + "\n");
                    }
                else{
                    if(agregado)
                        Toast.makeText(MainActivity.this,"Ya seleccionado.",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Debe seleccionar algo del menu",Toast.LENGTH_LONG).show();


                }
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listapedidos = new ArrayList<Utils.ElementoMenu>();
                pedidoActual.setText("");
                precio = 0.0;
                confirmado = false;


            }
        });



        precio=0.0;
        confirmarpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elementoSeleccionado = null;
                for(Utils.ElementoMenu elemento : listapedidos){
                    precio = precio +elemento.getPrecio();
                }
                pedidoActual.setText(pedidoActual.getText() + "TOTAL: $" + precio.toString().substring(0,5));
                confirmado = true;
                String accion = "modelo.intent.action.Pago";
                Intent intent = new Intent(accion);
                startActivityForResult(intent , codigoPago);



            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pedido" , pedidoActual.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pedidoActual.setText(savedInstanceState.getString("pedido"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == codigoPago) {
            if (resultCode == RESULT_OK) {
                pedido.setNombreCliente(data.getExtras().get("nombre").toString());
                pedido.setEmail(data.getExtras().get("email").toString());
                pedido.setNombre(data.getExtras().get("nombre").toString());
                agregarPedido();
                pedido.setCosto(precio);
                pedido.setEsDelivery(avisar.isChecked());
                pedido.setHoraEntrega(desplegable.getSelectedItem().toString());
                Toast.makeText(MainActivity.this, pedido.toString(), Toast.LENGTH_LONG).show();


            }
            if(resultCode == RESULT_CANCELED) {

                Toast.makeText(MainActivity.this, "EL PAGO HA SIDO CANCELADO", Toast.LENGTH_LONG).show();


            }

        }
    }

    public boolean agregado(Utils.ElementoMenu e){
        boolean agregado = false;
        if(e != null) {
            for (Utils.ElementoMenu elemento : listapedidos) {
                if (elemento.getTipoElemento().equals(e.getTipoElemento())) agregado = true;
            }
        }
        return  agregado;

    }
    public void agregarPedido(){
        for(Utils.ElementoMenu e : listapedidos){
            if(e.getTipoElemento() == TipoElemento.PRINCIPAL) pedido.setPlato(e);
            if(e.getTipoElemento() == TipoElemento.POSTRE) pedido.setPostre(e);
            if(e.getTipoElemento() == TipoElemento.BEBIDA) pedido.setBebida(e);

        }

    }
}
