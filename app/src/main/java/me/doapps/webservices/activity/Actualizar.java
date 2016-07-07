package me.doapps.webservices.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.doapps.luis.ServicesWithCallBacks.R;
import me.doapps.webservices.beanDTO.Student;
import me.doapps.webservices.service.StudentService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Actualizar extends Activity {
    private static final String TAG = "Actualizar";

    private Spinner spinner;
    private EditText editTextId;
    private EditText editTextNombre;
    private EditText editTextScore;
    private StudentService studentService;

    private ArrayList<String> strings = new ArrayList<String>();
    private Student student = new Student();
    private boolean lokkForState = false;

    private EditText editTextId1;
    private EditText editTextNombre1;
    private EditText editTextScore1;
    private Button btnActualizarA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        init();
        loadData();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.spinnerE);
        editTextId = (EditText) findViewById(R.id.editTextIdA);
        editTextNombre = (EditText) findViewById(R.id.editTextNombreA);
        editTextScore = (EditText) findViewById(R.id.editTextScoreA);

        editTextId1 = (EditText) findViewById(R.id.editTextIdA1);
        editTextNombre1 = (EditText) findViewById(R.id.editTextNombreA1);
        editTextScore1 = (EditText) findViewById(R.id.editTextScoreA1);

        btnActualizarA = (Button) findViewById(R.id.btnActualizarA);

        studentService = new StudentService(Actualizar.this);
    }

    private void loadData() {
        studentService.getAllStudents();
        studentService.initGetAllStudentInterface(new StudentService.studentGetAllInterface() {
            @Override
            public void IgetAllStudents(JSONArray jsonArray) {
                try {
                    strings.add("Seleccionar ...");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        strings.add(obj.getString("id"));
                        Log.e(TAG, "JSONObject name => " + obj.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lokkForState = false;

                } finally {
                    lokkForState = true;
                    initEvents();
                }
            }
        });
    }

    private void initServiceLookForEvents() {
        studentService.LookForStudent(spinner.getSelectedItem().toString());
        studentService.initLookforStudentInterface(new StudentService.studentLookforInterface() {
            @Override
            public void ILookforStudents(JSONArray jsonArray) {
                try {
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = ((JSONObject) jsonArray.get(0));
                        editTextId.setText(jsonObject.getString("id"));
                        editTextNombre.setText(jsonObject.getString("name"));
                        editTextScore.setText(jsonObject.getString("score"));

                        editTextId1.setText(jsonObject.getString("id"));
                        editTextNombre1.setText(jsonObject.getString("name"));
                        editTextScore1.setText(jsonObject.getString("score"));
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }
        });
    }

    private void initEvents() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, strings);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() != 0) {
                    executeLookEventEval(lokkForState);
                } else {
                    editTextId.setText("");
                    editTextNombre.setText("");
                    editTextScore.setText("");

                    editTextId1.setText("");
                    editTextNombre1.setText("");
                    editTextScore1.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnActualizarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextId.getText().toString().trim().equals("") &&
                        !editTextNombre.getText().toString().trim().equals("") &&
                        !editTextScore.getText().toString().trim().equals("")) {
                        executeUpdate();
                }
                else {
                    Toast.makeText(Actualizar.this, " Seleccionar algun estudiante  ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void executeLookEventEval(boolean paramState) {
        if (paramState) {
            initServiceLookForEvents();
        } else {
            Toast.makeText(Actualizar.this, "Los datos se estan preparando",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void executeUpdate() {

        Student student = new Student();
        student.setId(editTextId1.getText().toString());
        student.setName(editTextNombre1.getText().toString());
        student.setScore(editTextScore1.getText().toString());

        studentService.UpdateStudent(student);
        studentService.initUpdateStudentInterface(new StudentService.studentUpdateInterface() {
            @Override
            public void IUpdateStudents(JSONArray jsonArray) {
                try {
                    if (jsonArray.length() >= 0) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                        Toast.makeText(Actualizar.this,
                                "Se ha actualizado el estudiante de " +
                                        "\n codigo  : " + jsonObject.getString("id") +
                                        "\n nombre : " + jsonObject.getString("name") +
                                        "\n nota : " + jsonObject.getString("score"),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Actualizar.this,
                                "Ocurrio algo y no se pudo actualizar ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Actualizar.this, "Ocurrio un error y no se pudo actualizar la", Toast.LENGTH_SHORT).show();
                    e.getMessage();
                }
            }
        });
    }
}
