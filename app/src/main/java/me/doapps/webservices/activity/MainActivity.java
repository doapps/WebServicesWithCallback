package me.doapps.webservices.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.doapps.luis.ServicesWithCallBacks.R;

public class MainActivity extends Activity implements Button.OnTouchListener {

    private Button btnIngresar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnListar;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_main);

        init();

        InitTouchEvents();

    }


    /**
     * method to initialize UI variables
     */
    private void init() {
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
    }


    private void InitTouchEvents(){
        btnIngresar.setOnTouchListener(this);
        btnActualizar.setOnTouchListener(this);
        btnEliminar.setOnTouchListener(this);
        btnListar.setOnTouchListener(this);
        btnBuscar.setOnTouchListener(this);
    }

    private void setButtonWhiteTextColor(Button button) {
        try {
            button.setTextColor(getResources().getColor(R.color.colorwhite));
            button.setBackgroundResource(R.drawable.custom_button_blakcback);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void setButtonBlackTextColor(Button button) {
        try {
            button.setTextColor(getResources().getColor(R.color.colorBlack));
            button.setBackgroundResource(R.drawable.custom_button_whiteback);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int id = v.getId();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // PRESSED
                if (id == btnIngresar.getId()) {
                    setButtonBlackTextColor(btnIngresar);
                } else if (id == btnActualizar.getId()) {
                    setButtonBlackTextColor(btnActualizar);
                } else if (id == btnEliminar.getId()) {
                    setButtonBlackTextColor(btnEliminar);
                } else if (id == btnListar.getId()) {
                    setButtonBlackTextColor(btnListar);
                }else if (id == btnBuscar.getId()) {
                    setButtonBlackTextColor(btnBuscar);
                }
                return true;
            case MotionEvent.ACTION_UP:
                // RELEASED
                if (id == btnIngresar.getId()) {
                    setButtonWhiteTextColor(btnIngresar);
                    Intent intent  = new Intent(MainActivity.this,Ingresar.class);
                    startActivity(intent);
                } else if (id == btnActualizar.getId()) {
                    setButtonWhiteTextColor(btnActualizar);
                    Intent intent  = new Intent(MainActivity.this,Actualizar.class);
                    startActivity(intent);
                } else if (id == btnEliminar.getId()) {
                    setButtonWhiteTextColor(btnEliminar);
                    Intent intent  = new Intent(MainActivity.this,EliminarActivity.class);
                    startActivity(intent);
                } else if (id == btnListar.getId()) {
                    setButtonWhiteTextColor(btnListar);
                    Intent intent  = new Intent(MainActivity.this,Listar.class);
                    startActivity(intent);
                }else if (id == btnBuscar.getId()) {
                    setButtonWhiteTextColor(btnBuscar);
                    Intent intent  = new Intent(MainActivity.this,Buscar.class);
                    startActivity(intent);
                }
                return true;
        }
        return false;
    }
}
