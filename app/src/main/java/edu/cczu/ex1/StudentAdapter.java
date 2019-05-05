package edu.cczu.ex1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Student student;
    private LayoutInflater inflater;

    public StudentAdapter(Student stuData, LayoutInflater inflater) {
        student = stuData;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        //用于返回这一行的数据，一般情况下只需要返回索引就能找到
        return position;
    }

    @Override
    public long getItemId(int position) {
        //要不要这行数据来个id，返回索引
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_show_info, null);

        TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
        TextView textView_height = (TextView) view.findViewById(R.id.textView_height);
        TextView textView_weight = (TextView) view.findViewById(R.id.textView_weight);
        TextView textView_sex = (TextView) view.findViewById(R.id.textView_sex);
        TextView textView_hobby = (TextView) view.findViewById(R.id.textView_hobby);
        TextView textView_major = (TextView) view.findViewById(R.id.textView_major);

        textView_name.append(student.getName());
        textView_height.append(student.getHeight() + "cm");
        textView_weight.append(student.getWeight() + "kg");
        textView_sex.append(student.getSex());
        textView_hobby.append(hobby_toString(student));
        textView_major.append(student.getMajor());
        return view;
    }

    public String Sex_int2String(int Sex) {
        if (Sex == 1) return "男";
        return "女";
    }

    public String hobby_toString(Student student) {
        char[] hobby = student.getHobby().toCharArray();
        String res = null;
        int a[] = new int[3];
        int j = 0;
        for (char c : hobby) {
            a[j++] = hobby[j] - '0';
            ;
        }
        for (int i : a) {
            if (res != null)
                res = res + "," + student.HobbyName[i];
            else res += student.HobbyName[i];
        }
        return res;
    }

//    public String major_int2String(int Major) {
//        switch (Major) {
//            case 1:
//                return "软件工程";
//            case 2:
//                return "物联网";
//            default:
//                return "计算机";//默认为计算机：0
//        }
//    }
}
