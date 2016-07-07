package me.doapps.webservices.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import me.doapps.webservices.uri.UrlService;
import me.doapps.webservices.beanDTO.Student;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by frank on 30/06/16.
 */
public class StudentService {
    private static final String TAG = "StudentService";
    private Context context;
    private ProgressDialog progressDialog;
    private studentAddInterface studentInterface;
    private studentGetAllInterface studentGetAllInterface;
    private studentUpdateInterface studentUpdateInterface;
    private studentDeleteInterface studentDeleteInterface;
    private studentLookforInterface studentLookforInterface;

    public StudentService(Context context){
        this.context=context;
        progressDialog= new ProgressDialog(context);
    }

    public void addStudents(Student student) {
        try {
            progressDialog.setMessage("Agregando usuario");
            progressDialog.show();

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            RequestParams requestParams= new RequestParams();
            requestParams.add("id",student.getId());
            requestParams.add("name",student.getName());
            requestParams.add("score",student.getScore());

            asyncHttpClient.post(context, UrlService.IngresarDataURL, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try{
                        Log.d(TAG,"onSuccess");
                        Log.d(TAG,"onSuccess statuscode :"+i);
                        String jobject = new String(bytes);
                        JSONObject jsonObject = new JSONObject(jobject);
                        studentInterface.IaddStudents(jsonObject);
                        Log.d(TAG,""+jobject);
                        progressDialog.dismiss();
                    }
                    catch (Exception ex){
                        ex.getMessage();
                        progressDialog.dismiss();
                    }
                }
                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Log.d(TAG,"onFailure");
                    Log.d(TAG,"onFailure statuscode :"+i);
                    progressDialog.dismiss();
                    Toast.makeText(context, "No hay respuesta del servidor ,verificar su conexion a internet", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void getAllStudents() {
        progressDialog.setMessage("Obteniendo Estudiantes");
        progressDialog.show();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, UrlService.ListarDataURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    Log.d(TAG, "onSuccess");
                    Log.d(TAG, "onSuccess statuscode :" + i);

                    String jobject = new String(bytes);
                    JSONArray jsonObject = new JSONArray(jobject);
                    studentGetAllInterface.IgetAllStudents(jsonObject);
                    progressDialog.dismiss();
                } catch (JSONException ex) {
                    ex.getMessage();
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d(TAG, "onFailure");
                Log.d(TAG, "onFailure statuscode :" + i);
                progressDialog.dismiss();
                Toast.makeText(context, "No hay respuesta del servidor ,verificar su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void LookForStudent(String id){
        progressDialog.setMessage("Buscando Estudiantes");
        progressDialog.show();

       // RequestParams requestParams=new RequestParams();
       // requestParams.add("id",id);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //asyncHttpClient.get(UrlService.BuscarDataURL,requestParams, new AsyncHttpResponseHandler() {
        asyncHttpClient.get(UrlService.BuscarDataURL+"id="+id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.d(TAG, "onSuccess");
                    Log.d(TAG, "onSuccess LookForStudent statuscode :" + statusCode);
                    String jobject = new String(responseBody);
                    JSONArray jsonArray = new JSONArray (jobject);
                    studentLookforInterface.ILookforStudents(jsonArray);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    e.getMessage();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure");
                Log.d(TAG, "onFailure statuscode :" + statusCode);
                progressDialog.dismiss();
                Toast.makeText(context, "No hay respuesta del servidor ,verificar su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UpdateStudent(Student student){
        progressDialog.setMessage("Actualizando Estudiante");
        progressDialog.show();

         RequestParams requestParams=new RequestParams();
        requestParams.add("id",student.getId());
        requestParams.add("name",student.getName());
        requestParams.add("score",student.getScore());

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.put(context,UrlService.ActualizarDataURL+student.getId(),requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.e(TAG, "onSuccess");
                    Log.e(TAG, "onSuccess LookForStudent statuscode :" + statusCode);
                    String jobject = new String(responseBody);
                    JSONArray jsonArray = new JSONArray (jobject);
                    studentUpdateInterface.IUpdateStudents(jsonArray);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    e.getMessage();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, "onFailure statuscode :" + statusCode);
                Log.e(TAG, "onFailure json data :" + new String(responseBody));
                progressDialog.dismiss();
                Toast.makeText(context, "No hay respuesta del servidor ," +
                        "verificar su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void EliminarStudent(String id){
        progressDialog.setMessage("Eliminando Estudiante");
        progressDialog.show();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.delete(context,UrlService.EliminarDataURL+"/id/"+id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.e(TAG, "onSuccess");
                    Log.e(TAG, "onSuccess IDeleteStudents statuscode :" + statusCode);

                    studentDeleteInterface.IDeleteStudents( statusCode);
                    Log.e(TAG, "onSuccess IDeleteStudents");

                    progressDialog.dismiss();
                }
                catch (Exception e){
                    e.getMessage();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, "onFailure statuscode :" + statusCode);
                Log.e(TAG, "onFailure json data :" + new String(responseBody));
                progressDialog.dismiss();

                if(statusCode==404){
                    Toast.makeText(context, "No se ha encontrado la data ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "No hay respuesta del servidor ," +
                            "verificar su conexion a internet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public interface studentAddInterface {
        void IaddStudents(JSONObject jsonObject);
    }
    public interface  studentGetAllInterface{
        void IgetAllStudents(JSONArray jsonArray);
    }
    public interface studentDeleteInterface{
        void IDeleteStudents(int statusCode);
    }
    public interface studentUpdateInterface{
        void IUpdateStudents(JSONArray jsonArray );
    }
    public interface studentLookforInterface{
        void ILookforStudents(JSONArray jsonArray);
    }

    public void initAddStudentInterface(studentAddInterface studentInterface) {
        this.studentInterface = studentInterface;
    }
    public void initGetAllStudentInterface(studentGetAllInterface studentGetAllInterface) {
        this.studentGetAllInterface = studentGetAllInterface;
    }
    public void initUpdateStudentInterface(studentUpdateInterface studentUpdateInterface) {
        this.studentUpdateInterface = studentUpdateInterface;
    }
    public void initDeleteStudentInterface(studentDeleteInterface studentDeleteInterface) {
        this.studentDeleteInterface = studentDeleteInterface;
    }
    public void initLookforStudentInterface(studentLookforInterface studentLookforInterface) {
        this.studentLookforInterface = studentLookforInterface;
    }

}
