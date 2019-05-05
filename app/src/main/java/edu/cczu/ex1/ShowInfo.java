package edu.cczu.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowInfo extends AppCompatActivity {
    private static Student student=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

//        LayoutInflater inflater = getLayoutInflater();
//        StudentAdapter studentAdapter = new StudentAdapter(stuData, inflater);


        TextView textView_name = (TextView) findViewById(R.id.textView_name);
        TextView textView_height = (TextView) findViewById(R.id.textView_height);
        TextView textView_weight = (TextView) findViewById(R.id.textView_weight);
        TextView textView_sex = (TextView) findViewById(R.id.textView_sex);
        TextView textView_hobby = (TextView) findViewById(R.id.textView_hobby);
        TextView textView_major = (TextView) findViewById(R.id.textView_major);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle != null)
            student=(Student) bundle.getSerializable("student");
        else Toast.makeText(getApplicationContext(),"数据为空",Toast.LENGTH_SHORT).show();

        textView_name.append(student.getName());
        textView_height.append(student.getHeight() + "cm");
        textView_weight.append(student.getWeight() + "kg");
        textView_sex.append(student.getSex());
        textView_hobby.append(student.hobby_toString());
        textView_major.append(student.getMajor());

        Button button = (Button) findViewById(R.id.button_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
