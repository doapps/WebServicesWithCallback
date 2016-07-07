
package me.doapps.webservices.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.doapps.luis.ServicesWithCallBacks.R;
import me.doapps.webservices.service.StudentService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Buscar extends Activity {
    private static final String TAG = "Buscar";

    private Spinner spinner;
    private EditText editTextId;
    private EditText editTextNombre;
    private EditText editTextScore;
    private StudentService studentService;

    private ArrayList<String> strings = new ArrayList<String>();
    private boolean lokkForState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        init();
        loadData();
    }


    private void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextId = (EditText) findViewById(R.id.editTextIdB);
        editTextNombre = (EditText) findViewById(R.id.editTextNombreB);
        editTextScore = (EditText) findViewById(R.id.editTextScoreB);

        studentService = new StudentService(Buscar.this);
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
                if(spinner.getSelectedItemPosition() != 0){
                    executeLookEventEval(lokkForState);
                }
                else{
                    editTextId.setText("");
                    editTextNombre.setText("");
                    editTextScore.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    private void executeLookEventEval(boolean paramState){
        if(paramState){
            initServiceLookForEvents();
        }
        else{
            Toast.makeText(Buscar.this, "Los datos se estan preparando", Toast.LENGTH_SHORT).show();
        }
    }
}
