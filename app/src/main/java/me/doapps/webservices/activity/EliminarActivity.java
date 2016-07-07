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
import me.doapps.webservices.service.StudentService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EliminarActivity extends Activity {
    private static final String TAG = "EliminarActivity";

    private Spinner spinnerE;
    private EditText editTextIdE;
    private EditText editTextNombreE;
    private EditText editTextScoreE;
    private StudentService studentService;
    private Button btnEliminarE;

    private ArrayList<String> strings = new ArrayList<String>();
    private boolean lokkForState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        init();
        loadData();
    }

    private void init() {
        spinnerE = (Spinner) findViewById(R.id.spinnerE);
        editTextIdE = (EditText) findViewById(R.id.editTextIdE);
        editTextNombreE = (EditText) findViewById(R.id.editTextNombreE);
        editTextScoreE = (EditText) findViewById(R.id.editTextScoreE);

        studentService = new StudentService(EliminarActivity.this);
        btnEliminarE = (Button) findViewById(R.id.btnEliminarE);
    }

    private void initEvents() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, strings);
        spinnerE.setAdapter(adapter);
        spinnerE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerE.getSelectedItemPosition() != 0) {
                    executeLookEventEval(lokkForState);
                } else {
                    editTextIdE.setText("");
                    editTextNombreE.setText("");
                    editTextScoreE.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnEliminarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinnerE.getSelectedItemPosition()!=0){
                    initServiceDeleteEvents();
                }
                else{
                    Toast.makeText(EliminarActivity.this,
                            "Seleccione un estudiante",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initServiceLookForEvents() {
        studentService.LookForStudent(spinnerE.getSelectedItem().toString());
        studentService.initLookforStudentInterface(new StudentService.studentLookforInterface() {
            @Override
            public void ILookforStudents(JSONArray jsonArray) {
                try {

                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = ((JSONObject) jsonArray.get(0));
                        editTextIdE.setText(jsonObject.getString("id"));
                        editTextNombreE.setText(jsonObject.getString("name"));
                        editTextScoreE.setText(jsonObject.getString("score"));
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }
        });
    }

    private void initServiceDeleteEvents() {

        studentService.EliminarStudent(spinnerE.getSelectedItem().toString());
        studentService.initDeleteStudentInterface(new StudentService.studentDeleteInterface() {
            @Override
            public void IDeleteStudents( int statusCode) {
                try {
                    Log.e(TAG, "IDeleteStudents  executed");
                    if (statusCode == 204) {
                        Toast.makeText(EliminarActivity.this,
                                "Se ha eliminado el usuario",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EliminarActivity.this,
                                "ocurrio una decepcion",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.getMessage();
                } finally {
                    loadData();
                }
            }
        });
    }

    private void loadData() {
        lokkForState = false;
        strings.clear();

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

    private void executeLookEventEval(boolean paramState) {
        if (paramState) {
            initServiceLookForEvents();
        } else {
            Toast.makeText(EliminarActivity.this, "Los datos se estan preparando", Toast.LENGTH_SHORT).show();
        }
    }
}
