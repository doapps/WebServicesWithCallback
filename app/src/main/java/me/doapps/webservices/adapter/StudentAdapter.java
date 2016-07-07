package me.doapps.webservices.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doapps.luis.ServicesWithCallBacks.R;
import me.doapps.webservices.beanDTO.Student;

import java.util.ArrayList;

/**
 * Created by frank on 30/06/16.
 */
public class StudentAdapter extends BaseAdapter {
    private static final String TAG="StudentAdapter";

    Context context;
    private ArrayList<Student> studentArrayList;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView", "" + position);

        StudentsHolder studentsHolder;
        if (convertView == null) {
            studentsHolder = new StudentsHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.row_student, null);

            studentsHolder.ly_score = (LinearLayout) convertView.findViewById(R.id.ly_score);
            studentsHolder.img_score = (ImageView) convertView.findViewById(R.id.img_score);
            studentsHolder.tv_name = (TextView) convertView.findViewById(R.id.name);
            studentsHolder.tv_score = (TextView) convertView.findViewById(R.id.score);

            convertView.setTag(studentsHolder);
        } else {
            studentsHolder = (StudentsHolder) convertView.getTag();
        }

                 Student student = studentArrayList.get(position);
                Log.e(TAG,"studentArrayList name => "+student.getName());

                double scoreVal ;
                if(student.getScore().trim().equals("")){
                    scoreVal = 0;

                    studentsHolder.tv_name.setText("No such data");
                    studentsHolder.tv_score.setText("No such data");
                    studentsHolder.img_score.setImageResource(R.drawable.ic_error_outline_white_24dp);
                    studentsHolder.ly_score.setBackgroundResource(R.color.colorPrimaryDark);
                }
                else{
                      scoreVal = Double.parseDouble(student.getScore().trim());
                    if (scoreVal < 30) {
                        studentsHolder.img_score.setImageResource(R.drawable.ic_clear_white_24dp);
                        studentsHolder.ly_score.setBackgroundResource(R.color.colorRed);
                    } else if (scoreVal < 50) {
                        studentsHolder.img_score.setImageResource(R.drawable.ic_clear_white_24dp);
                        studentsHolder.ly_score.setBackgroundResource(R.color.colorOrange);
                    } else if (scoreVal < 75) {
                        studentsHolder.img_score.setImageResource(R.drawable.ic_check_white_24dp);
                        studentsHolder.ly_score.setBackgroundResource(R.color.colorGreen);
                    } else {
                        studentsHolder.img_score.setImageResource(R.drawable.ic_check_white_24dp);
                        studentsHolder.ly_score.setBackgroundResource(R.color.colorGreenDark);
                    }

                    studentsHolder.tv_name.setText(student.getName());
                    studentsHolder.tv_score.setText(student.getScore());
                }


          return convertView;
    }

    private static class StudentsHolder {
        LinearLayout ly_score;
        ImageView img_score;
        TextView tv_name;
        TextView tv_score;
    }
}
