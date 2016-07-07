package me.doapps.webservices.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doapps.luis.ServicesWithCallBacks.R;
import me.doapps.webservices.beanDTO.Student;
import me.doapps.webservices.service.StudentService;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingresar extends Activity {

    private Button btnIngresar;
    private EditText editTextId;
    private EditText editTextNombre;
    private EditText editTextScore;
    private StudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        init();
        initEvents();
    }

    private void init() {
        btnIngresar = (Button) findViewById(R.id.btnIngresarS);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextScore = (EditText) findViewById(R.id.editTextScore);

        studentService = new StudentService(Ingresar.this);
    }

    private void initEvents() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValueEmpty(editTextId) ||
                        isValueEmpty(editTextNombre) ||
                        isValueEmpty(editTextScore)) {
                    Toast.makeText(Ingresar.this, "Completar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    studentService.addStudents(new Student(editTextId.getText().toString(),
                            editTextNombre.getText().toString(),
                            editTextScore.getText().toString()));
                }
            }
        });

        studentService.initAddStudentInterface(new StudentService.studentAddInterface() {
            @Override
            public void IaddStudents(JSONObject jsonObject) {
                try {
                    Toast.makeText(Ingresar.this, "Se ha ingresado a :"+
                            jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isValueEmpty(EditText editText) {
        if (editText.getText().toString().trim().equals("")) {
            return true;
        }
        return false;
    }


}
