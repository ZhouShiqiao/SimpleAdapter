package edu.fjnu.com.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private Button button;

    private SimpleAdapter simplead;

    private int position = -1;

    private String[] name = {"cat", "dog", "elephant", "lion", "monkey", "tiger"};
    private int[] picture = {R.drawable.cat, R.drawable.dog, R.drawable.elephant,
            R.drawable.lion, R.drawable.monkey, R.drawable.tiger};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdate();
        findview();
        initview();
    }

    private void initdate() {
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("name", name[i]);
            listem.put("image", picture[i]);
            listems.add(listem);
        }
        simplead = new SimpleAdapter(this, listems,
                R.layout.item, new String[]{"name", "image"},
                new int[]{R.id.name, R.id.image});
    }

    private void findview() {
        list = (ListView) findViewById(R.id.mylistview);
        button = (Button) findViewById(R.id.button);
    }

    private void initview() {
        list.setAdapter(simplead);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.position = position + 1;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                if (position > -1) {
                    toast = Toast.makeText(MainActivity.this, "选中的是第" + position + "行：" + name[position - 1], Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(MainActivity.this, "你未选中", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });

    }
}
