package edu.cczu.ex1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MajorSettings extends AppCompatActivity {

    private final int SHORTCUT_MODIFY = 0;
    private final int SHORTCUT_DELETE = 1;
    private ArrayList<String> majorList;
    private ArrayAdapter<String> arrayAdapter;
    private EditText editText;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_settings);

        editText = (EditText) findViewById(R.id.editText_majorName);
        listView = (ListView) findViewById(R.id.major_settings_listview);

        //新建一个Intent来接受发来的数据
        Intent intent = getIntent();
        majorList = intent.getStringArrayListExtra("major");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, majorList);
        listView.setAdapter(arrayAdapter);

        //返回按钮
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent result = new Intent();
                result.putStringArrayListExtra("major", majorList);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        //添加按钮
        Button button_add = (Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (TextUtils.isEmpty(msg))
                    Toast.makeText(getApplicationContext(), "专业名不可为空", Toast.LENGTH_SHORT).show();
                else {
                    majorList.add(msg);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, SHORTCUT_MODIFY, 0, "修改");
                menu.add(0, SHORTCUT_DELETE, 1, "删除");
            }
        });

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) info.id;
        switch (item.getItemId()) {
            case SHORTCUT_MODIFY:
                String msg = editText.getText().toString();
                if (TextUtils.isEmpty(msg))
                    Toast.makeText(getApplicationContext(), "专业名不可为空", Toast.LENGTH_SHORT).show();
                else {
                    majorList.set(id, msg);
                    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, majorList);
                    listView.setAdapter(arrayAdapter);
                }
                return true;
            case SHORTCUT_DELETE:
                majorList.remove(id);
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, majorList);
                listView.setAdapter(arrayAdapter);
                return true;
        }
        return false;
    }


    //以下功能为使得EditText在完成输入时，自动失去焦点，收起软键盘
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                hideSoftInput(view);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    // 隐藏软键盘
    private void hideSoftInput(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
