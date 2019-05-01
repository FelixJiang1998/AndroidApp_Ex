package edu.cczu.ex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ShowInfo extends AppCompatActivity {
    private List<Student> stuData;

    private void initial() {
        final Student ZhaoMing = new Student("赵明", 175, 72, 1, "110", 0);
        final Student LiXiao = new Student("李晓", 173, 86, 1, "010", 1);
        final Student WangLi = new Student("王丽", 163, 48, 0, "001", 2);

        stuData = new ArrayList<>();
        stuData.add(ZhaoMing);
        stuData.add(LiXiao);
        stuData.add(WangLi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        LayoutInflater inflater = getLayoutInflater();
        initial();
        StudentAdapter studentAdapter = new StudentAdapter(stuData, inflater);

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
