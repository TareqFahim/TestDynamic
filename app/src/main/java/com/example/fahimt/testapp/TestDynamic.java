package com.example.fahimt.testapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestDynamic extends AppCompatActivity {
    List<QuestionModel> qList = new ArrayList();
    LinearLayout linearLayout;
//    EditText editText;
    static int j = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dynamic);
        linearLayout = findViewById(R.id.test_dynamic_linearLayout);
        EditText editText1 = new EditText(this);

        final Integer[] parents1 = {1};
        final Integer[] Parents2 = {1,2};
        qList.add(new QuestionModel(1, "Text", "First Q?", true, null, null, null));
        qList.add(new QuestionModel(2, "Text", "Second Q?", false, null, parents1, "parent1 =='aa' || parent1 == 'hh'"));
        qList.add(new QuestionModel(3, "Text", "Second Q?", false, null, Parents2, "parent1 =='aa' && parent2 == 'ss'"));
//        editText1.setId(qList.get(0).id);
//        linearLayout.addView(linearLayout);

        if(qList.size() > 0){
            for (int i = 0; i < qList.size(); i++) {
                switch (qList.get(i).QType){
                    case "Text":
                        final EditText editText = new EditText(this);
                        editText.setId(qList.get(i).id);
                        editText.setTag(qList.get(i).getCondition());
                        editText.setHint("Test");
                        if(qList.get(i).getParentId() != null){
                            for (Integer Q:qList.get(i).getParentId()) {
                                final EditText parentEditText = findViewById(Q);
                                parentEditText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        ScriptEngineManager factory = new ScriptEngineManager();
                                        ScriptEngine engine = factory.getEngineByName("javascript");
                                        try{
                                            String cond = "";
                                            Integer[] Parents ;
                                            if(editText.getId() == Integer.parseInt("2"))
                                            {
                                                Parents = parents1;
                                            }
                                            else if(editText.getId() == Integer.parseInt("3"))
                                            {
                                                Parents = Parents2;
                                            }
                                            else {
                                                Parents = new Integer[]{};
                                            }
                                            for(Integer K:Parents)
                                            {
//                                            cond.concat("var parent"+Integer.toString(k)+" = "+((EditText)findViewById(k)).getText().toString()+" ;");
                                                cond += "var parent"+Integer.toString(K)+" = '"+((EditText)findViewById(K)).getText().toString()+"' ;";
                                            }
                                            cond += editText.getTag().toString();
                                            Boolean f = (Boolean) engine.eval(cond);
                                            if (f){
                                                editText.setVisibility(View.VISIBLE);
                                            }else{
                                                editText.setVisibility(View.GONE);
                                            }
                                        }catch (Exception e){
                                            String w = e.getMessage();
                                            w = w + " ";
                                        }

                                    }
                                });

                            }
                        }
                        linearLayout.addView(editText);
                        break;
                }
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private boolean eval(String str){
        try{
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("javascript");

//                    engine.eval("ans = \""+ str +"\"");
            Boolean f = (Boolean) engine.eval("ans = '"+str+"' ; ans.equals('aa')");

        }catch (Exception c){

        }
        return false;
    }
}
