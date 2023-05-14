package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{ //mainfunnction

    TextView solutiontv,resulttv;
    MaterialButton buttondivide,buttonadd,buttonequals,buttonsubtract,buttonmultiply;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttondot;
    MaterialButton buttonopenbracket,buttonclosebracket,buttonC;
    MaterialButton buttonAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //buttons linkage
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutiontv=findViewById(R.id.solution_textview);
        resulttv=findViewById(R.id.result_textview);

        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttondot,R.id.button_dot);
        assignId(buttonC,R.id.button_c);
        assignId(buttonopenbracket,R.id.button_open_bracket);
        assignId(buttonclosebracket,R.id.button_close_bracket);
        assignId(buttonadd,R.id.button_add);
        assignId(buttonsubtract,R.id.button_subtract);
        assignId(buttonmultiply,R.id.button_multiply);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonequals,R.id.button_equals);
    }

    void assignId(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //asssigning button

        Object view;
        MaterialButton button=(MaterialButton) v;
        String buttonText=button.getText().toString();
        String datacalc=solutiontv.getText().toString();

        if(buttonText.equals("AC")) //all_clear_button
        {
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }
        if (buttonText.equals("=")) //equal to button
        {
            solutiontv.setText(resulttv.getText());
            return;
        }
        if(buttonText.equals("C")) //C button
        {
            datacalc=datacalc.substring(0,datacalc.length()-1);
        }else
        {
            datacalc=datacalc+buttonText;
        }

        solutiontv.setText(datacalc);
        String finalResult=getResult(datacalc);
        if(!finalResult.equals("ERROR"))
        {
            resulttv.setText(finalResult);
        }
    }
    String getResult(String data) //evaluation
    {
        try { //caculation_part
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {
            return "ERROR";
        }
    }
}