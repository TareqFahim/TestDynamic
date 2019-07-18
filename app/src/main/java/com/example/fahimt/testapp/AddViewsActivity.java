package com.example.fahimt.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import java.util.ArrayList;
import java.util.List;

public class AddViewsActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Spinner spinner1;
    Button btn1;
    Button b;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_views);
        editText = new EditText(this);
        editText.setHint("Add a string");
        b = new Button(AddViewsActivity.this);
        linearLayout = findViewById(R.id.linear_layout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(editText);
//        btn1 = new Button(this);
//        btn1.setText("Click Me");
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AddViewsActivity.this, AddViewsActivity.this.btn1.getText(), Toast.LENGTH_LONG).show();
//            }
//        });
//        linearLayout.addView(btn1);
//        String str = "x = 5";
//        try{
//            ScriptEngineManager factory = new ScriptEngineManager();
//            ScriptEngine engine = factory.getEngineByName("javascript");
//
//            engine.eval("ans = \"HI\"");
//            engine.eval("x = 2");
//            Boolean f = (Boolean) engine.eval("!ans.equals(\"HI\") && x >= 2");
//
//            if(f)
//                Toast.makeText(this, "True", Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(this, "False", Toast.LENGTH_LONG).show();
//        }catch (Exception c){
//            Toast.makeText(this, c.getMessage(), Toast.LENGTH_LONG).show();
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = AddViewsActivity.this.editText.getText().toString();
                try{

                    List qlist = new ArrayList();
                    questions q = new questions();
                    q.id = 1;
                    q.quesiton = "aaa?";
                    questions q2 = new questions();
                    q2.id = 2;
                    q2.quesiton = "bbb?";
                    qlist.add(q);
                    qlist.add(q2);



                    ScriptEngineManager factory = new ScriptEngineManager();
                    ScriptEngine engine = factory.getEngineByName("javascript");

//                    engine.eval("ans = \""+ str +"\"");
                    Boolean f = (Boolean) engine.eval("ans = '"+str+"' ; ans.equals('aa')");

                    if(f) {
                        b.setVisibility(View.VISIBLE);
                        b.setText("Hello");
                        AddViewsActivity.this.linearLayout.addView(b);
                    }else{
                        b.setVisibility(View.GONE);
                    }
                }catch (Exception c){
                    Toast.makeText(AddViewsActivity.this, c.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    class questions{
        int id;
        String quesiton;
    }
}
