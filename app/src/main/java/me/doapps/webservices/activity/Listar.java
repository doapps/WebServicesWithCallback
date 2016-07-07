package me.doapps.webservices.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.doapps.luis.ServicesWithCallBacks.R;
import me.doapps.webservices.adapter.StudentAdapter;
import me.doapps.webservices.beanDTO.Student;
import me.doapps.webservices.service.StudentService;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Listar extends Activity {
    private static final String TAG="Listar";
    private BaseAdapter baseAdapter;
    private ListView listView;
    private StudentService studentService;
    private StudentService.studentGetAllInterface studentGetAllInterface;
    private ArrayList<Student> students;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        initService();
    }

    private void initListViewParams(){
        baseAdapter = new StudentAdapter(Listar.this,students);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(baseAdapter);
    }
    private void initService() {
        students = new ArrayList<Student>();
        studentService = new StudentService(Listar.this);
        studentService.getAllStudents();
        studentService.initGetAllStudentInterface(new StudentService.studentGetAllInterface() {
            @Override
            public void IgetAllStudents(JSONArray jsonArray) {
                try {

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj=(JSONObject) jsonArray.get(i);
                        students.add(new Student(obj.getString("id"),obj.getString("name"),obj.getString("score")));
                        Log.e(TAG,"JSONObject name => "+obj.getString("name"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    initListViewParams();

                }
            }
        });
    }
}
