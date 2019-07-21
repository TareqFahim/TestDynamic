package com.example.fahimt.testapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.drm.DrmStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    Button btn;

    //    EditText editText;
    static int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dynamic);
        linearLayout = findViewById(R.id.test_dynamic_linearLayout);
        btn = findViewById(R.id.multi_choice_btn);
        final EditText editText1 = new EditText(this);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestDynamic.this);
//                mBuilder.setTitle("Select Disease");
//                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mSelectedItemIndex.contains(position)) {
//                                mSelectedItemIndex.add(position);
//                            } else {
//                                mSelectedItemIndex.remove(position);
//                            }
//                        }
//                    }
//                });
//                mBuilder.setCancelable(false);
//                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String ss = "";
//                        for (int i = 0; i < mSelectedItemIndex.size(); i++) {
//                            ss += listItems[mSelectedItemIndex.get(i)];
//                        }
//                        Toast.makeText(TestDynamic.this, ss, Toast.LENGTH_LONG).show();
//                    }
//                });
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
//            }
//        });
        final Integer[] parents1 = {1};
        final Integer[] Parents2 = {1, 3};
        qList.add(new QuestionModel(1, "Text", "First Q?", true, null, null, null));
        qList.add(new QuestionModel(2, "Text", "Second Q?", false, null, parents1, "parent1 =='qq' || parent1 == 'hh'"));
        qList.add(new QuestionModel(3, "Multi_Select", "Select Disease", false, null, null, null));
        qList.add(new QuestionModel(4, "Text", "Second Q?", false, null, Parents2, "parent1 =='aa' && parent3 == '[\"SARI\"]'"));

//        editText1.setId(qList.get(0).id);
//        linearLayout.addView(linearLayout);

        if (qList.size() > 0) {
            for (int i = 0; i < qList.size(); i++) {
                switch (qList.get(i).QType) {
                    case "Text":
                        final EditText editText = new EditText(this);
                        editText.setId(qList.get(i).id);
                        editText.setTag(qList.get(i).getCondition());
                        editText.setHint(Integer.toString(qList.get(i).getId()));
                        if (qList.get(i).getParentId() != null) {
                            for (Integer Q : qList.get(i).getParentId()) {
                                editText.setVisibility(View.GONE);
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
                                        int parentIndex = 1;
                                        qList.get(parentEditText.getId() - parentIndex).setAnswer(parentEditText.getText().toString());
                                        ScriptEngineManager factory = new ScriptEngineManager();
                                        ScriptEngine engine = factory.getEngineByName("javascript");
                                        try {
                                            String cond = "";
                                            Integer[] Parents;
                                            if (editText.getId() == Integer.parseInt("2")) {
                                                Parents = parents1;
                                            } else if (editText.getId() == Integer.parseInt("4")) {
                                                Parents = Parents2;
                                            } else {
                                                Parents = new Integer[]{};
                                            }
                                            for (Integer K : Parents) {
//                                            cond.concat("var parent"+Integer.toString(k)+" = "+((EditText)findViewById(k)).getText().toString()+" ;");
//                                                cond += "var parent" + Integer.toString(K) + " = '" + ((EditText) findViewById(K)).getText().toString() + "' ;";
                                                int index = findViewById(K).getId();
                                                cond += "var parent" + Integer.toString(K) + " = '" + qList.get(index - 1).getAnswer() + "' ;";
                                            }
                                            cond += editText.getTag().toString();
                                            Boolean f = (Boolean) engine.eval(cond);
                                            if (f) {
                                                editText.setVisibility(View.VISIBLE);
                                            } else {
                                                editText.setVisibility(View.GONE);
                                            }
                                        } catch (Exception e) {
                                            String w = e.getMessage();
                                            w = w + " ";
                                        }

                                    }
                                });

                            }
                        }
                        linearLayout.addView(editText);
                        break;
                    case "Multi_Select":

                        final EditText editText2 = new EditText(TestDynamic.this);
                        editText2.setId(qList.get(i).id);
                        editText2.setTag(qList.get(i).getCondition());
                        editText2.setHint(qList.get(i).getId() + " Alert");
                        final int index = i;
                        boolean[] checkedItems;
                        final String[] listItems;
                        final List<Integer> mSelectedItemIndex = new ArrayList<>();
                        listItems = getResources().getStringArray(R.array.diseases);
                        checkedItems = new boolean[listItems.length];
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestDynamic.this);
                        mBuilder.setTitle("AlertDialog");
                        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                                if (isChecked) {
                                    if (!mSelectedItemIndex.contains(position)) {
                                        mSelectedItemIndex.add(position);
                                    } else {
                                        mSelectedItemIndex.remove(position);
                                    }
                                }
                            }
                        });
                        mBuilder.setCancelable(false);
                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String ss = "";
                                for (int i = 0; i < mSelectedItemIndex.size(); i++) {
                                    if (i == 0) {
                                        ss += "[\"" + listItems[mSelectedItemIndex.get(i)] + "\"";
                                        if(i == mSelectedItemIndex.size() - 1){
                                            ss += "]";
                                        }
                                    } else if (i == mSelectedItemIndex.size() - 1) {
                                        ss += ",\"" + listItems[mSelectedItemIndex.get(i)] + "\"]";
                                    }else{
                                        ss += ",\"" + listItems[mSelectedItemIndex.get(i)] + "\"";
                                    }
                                }

                                editText2.setText(ss);
                                qList.get(index).setAnswer(ss);
                                Toast.makeText(TestDynamic.this, qList.get(index).getAnswer(), Toast.LENGTH_LONG).show();
                            }
                        });


                        editText2.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    AlertDialog mDialog = mBuilder.create();
                                    mDialog.show();
                                }
                                return false;
                            }
                        });
                        linearLayout.addView(editText2);
                        break;
                }
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private boolean eval(String str) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("javascript");

//                    engine.eval("ans = \""+ str +"\"");
            Boolean f = (Boolean) engine.eval("ans = '" + str + "' ; ans.equals('aa')");

        } catch (Exception c) {

        }
        return false;
    }
}
