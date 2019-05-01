package edu.cczu.ex1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //创建快捷菜单
    final static int CONTEXT_MENU_1 = Menu.FIRST;
    final int MAJOR_SETTINGS = 1;
    final int SHOW_INFO = 2;
    private ArrayList<String> nameList;
    private ArrayList<String> majorList;
    private ArrayAdapter<String> aryAdapter_major;
    private ArrayAdapter<String> aryAdapter_name;
    private ListView listView;
    private Spinner spinner;
    private float BMI;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case MAJOR_SETTINGS:
                    majorList = data.getStringArrayListExtra("major");
                    aryAdapter_major = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, majorList);
                    spinner.setAdapter(aryAdapter_major);
                    break;
                default:
                    break;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //姓名列表
        nameList = new ArrayList<String>();
        nameList.add("赵明");
        nameList.add("李晓");
        nameList.add("王丽");

        //专业名称的列表
        majorList = new ArrayList<String>();
        majorList.add("计算机");
        majorList.add("软件工程");
        majorList.add("物联网");

        aryAdapter_major = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, majorList);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(aryAdapter_major);
        spinner.setSelection(0, true);//设置默认选项为“计算机”

        //点击关闭按钮退出app
        Button btn_exit = (Button) findViewById(R.id.button_close);//关闭按钮
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        aryAdapter_name = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, nameList);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(aryAdapter_name);
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, CONTEXT_MENU_1, 0, "查看");
            }
        });

        //添加按钮的点击事件
        Button btn_add = (Button) findViewById(R.id.button_add);//添加按钮
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtName = (EditText) findViewById(R.id.editText_name);
                if (TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "姓名不可为空", Toast.LENGTH_SHORT).show();
                else {
                    nameList.add(txtName.getText().toString());
                    aryAdapter_name.notifyDataSetChanged();
                }
            }
        });

        Button btn_cal = (Button) findViewById(R.id.button_cal);
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(((EditText) findViewById(R.id.editText_weight)).getText().toString());
                float height = Float.parseFloat(((EditText) findViewById(R.id.editText_height)).getText().toString());
                BMI = weight / height / height;

            }
        });
    }

    //设置菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        //使用xml实现
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //通过代码实现添加选项菜单
        // menu.add("settings").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        // 添加子菜单（代码实现）
//        SubMenu subMenu=menu.addSubMenu(0,0,0,"设置");
//        subMenu.add(0,1,0,"打印" );
        return super.onCreateOptionsMenu(menu);
    }

    //主菜单的响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu:
//                Toast.makeText(getApplicationContext(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.subMenu0:
//                Toast.makeText(getApplicationContext(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MajorSettings.class);
                intent.putStringArrayListExtra("major", majorList);
                startActivityForResult(intent, MAJOR_SETTINGS);
                return true;
            default:
                return true;
        }
    }

    //快捷菜单的响应
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) info.id;
        switch (item.getItemId()) {
            case CONTEXT_MENU_1:
                Intent intent = new Intent(MainActivity.this, ShowInfo.class);
//               intent.putExtra("data",temp.toArray());
//
                startActivityForResult(intent, SHOW_INFO);
                return true;
            default:
                return false;
        }
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
