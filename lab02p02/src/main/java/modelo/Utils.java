package modelo;

/**
 * Created by Francisco on 16/9/2017.
 */

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    DecimalFormat f = new DecimalFormat("##.00");

    private ElementoMenu[] listaBebidas;
     private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;

    public Utils(){
        listaBebidas = new ElementoMenu[7];
        listaPlatos = new ElementoMenu[14];
        listaPostre = new ElementoMenu[15];

    }

    public class ElementoMenu {
        private Integer id;
        private String nombre;
        private Double precio;

        public TipoElemento getTipoElemento() {
            return tipoElemento;
        }

        public void setTipoElemento(TipoElemento tipoElemento) {
            this.tipoElemento = tipoElemento;
        }

        private TipoElemento tipoElemento;
        public ElementoMenu() {
        }

        public ElementoMenu(Integer i, String n, Double p , TipoElemento t) {
            this.setId(i);
            this.setNombre(n);
            this.setPrecio(p);
            this.setTipoElemento(t);
        }

        public ElementoMenu(Integer i, String n , TipoElemento t ) {
            this(i,n,0.0,t);
            Random r = new Random();
            this.precio= (r.nextInt(3)+1)*((r.nextDouble()*100));

        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getPrecio() {
            return precio;
        }

        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return this.nombre+ " $"+f.format(this.precio);
            }
    }

    public void iniciarListas(){
        // inicia lista de bebidas
        //ElementoMenu[] listaBebidas = new ElementoMenu[7];
        listaBebidas[0]=new ElementoMenu(1,"Coca",TipoElemento.BEBIDA);
        listaBebidas[1]=new ElementoMenu(4,"Jugo",TipoElemento.BEBIDA);
        listaBebidas[2]=new ElementoMenu(6,"Agua",TipoElemento.BEBIDA);
        listaBebidas[3]=new ElementoMenu(8,"Soda",TipoElemento.BEBIDA);
        listaBebidas[4]=new ElementoMenu(9,"Fernet",TipoElemento.BEBIDA);
        listaBebidas[5]=new ElementoMenu(10,"Vino",TipoElemento.BEBIDA);
        listaBebidas[6]=new ElementoMenu(11,"Cerveza",TipoElemento.BEBIDA);
        // inicia lista de platos
        //ElementoMenu[] listaPlatos= new ElementoMenu[14];
        listaPlatos[0]=new ElementoMenu(1,"Ravioles",TipoElemento.PRINCIPAL);
        listaPlatos[1]=new ElementoMenu(2,"Gnocchi",TipoElemento.PRINCIPAL);
        listaPlatos[2]=new ElementoMenu(3,"Tallarines",TipoElemento.PRINCIPAL);
        listaPlatos[3]=new ElementoMenu(4,"Lomo",TipoElemento.PRINCIPAL);
        listaPlatos[4]=new ElementoMenu(5,"Entrecot",TipoElemento.PRINCIPAL);
        listaPlatos[5]=new ElementoMenu(6,"Pollo",TipoElemento.PRINCIPAL);
        listaPlatos[6]=new ElementoMenu(7,"Pechuga",TipoElemento.PRINCIPAL);
        listaPlatos[7]=new ElementoMenu(8,"Pizza",TipoElemento.PRINCIPAL);
        listaPlatos[8]=new ElementoMenu(9,"Empanadas",TipoElemento.PRINCIPAL);
        listaPlatos[9]=new ElementoMenu(10,"Milanesas",TipoElemento.PRINCIPAL);
        listaPlatos[10]=new ElementoMenu(11,"Picada 1",TipoElemento.PRINCIPAL);
        listaPlatos[11]=new ElementoMenu(12,"Picada 2",TipoElemento.PRINCIPAL);
        listaPlatos[12]=new ElementoMenu(13,"Hamburguesa",TipoElemento.PRINCIPAL);
        listaPlatos[13]=new ElementoMenu(14,"Calamares",TipoElemento.PRINCIPAL);
        // inicia lista de postres
        //ElementoMenu[] listaPostre= new ElementoMenu[15];
        listaPostre[0]=new ElementoMenu(1,"Helado",TipoElemento.POSTRE);
        listaPostre[1]=new ElementoMenu(2,"Ensalada de Frutas",TipoElemento.POSTRE);
        listaPostre[2]=new ElementoMenu(3,"Macedonia",TipoElemento.POSTRE);
        listaPostre[3]=new ElementoMenu(4,"Brownie",TipoElemento.POSTRE);
        listaPostre[4]=new ElementoMenu(5,"Cheescake",TipoElemento.POSTRE);
        listaPostre[5]=new ElementoMenu(6,"Tiramisu",TipoElemento.POSTRE);
        listaPostre[6]=new ElementoMenu(7,"Mousse",TipoElemento.POSTRE);
        listaPostre[7]=new ElementoMenu(8,"Fondue",TipoElemento.POSTRE);
        listaPostre[8]=new ElementoMenu(9,"Profiterol",TipoElemento.POSTRE);
        listaPostre[9]=new ElementoMenu(10,"Selva Negra",TipoElemento.POSTRE);
        listaPostre[10]=new ElementoMenu(11,"Lemon Pie",TipoElemento.POSTRE);
        listaPostre[11]=new ElementoMenu(12,"KitKat",TipoElemento.POSTRE);
        listaPostre[12]=new ElementoMenu(13,"IceCreamSandwich",TipoElemento.POSTRE);
        listaPostre[13]=new ElementoMenu(14,"Frozen Yougurth",TipoElemento.POSTRE);
        listaPostre[14]=new ElementoMenu(15,"Queso y Batata",TipoElemento.POSTRE);

    }

    public ElementoMenu[] getListaPostre(){
        return listaPostre;
    }

    public ElementoMenu[] getListaBebidas(){
        return listaBebidas;
    }

    public ElementoMenu[] getListaPlatos(){
        return listaPlatos;
    }

}
