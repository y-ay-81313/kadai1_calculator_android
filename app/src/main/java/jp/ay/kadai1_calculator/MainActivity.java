package jp.ay.kadai1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    String hugoBox = null;
    double firstNum = 0;
    double secondNum = 0;
    boolean isDot = false;
    boolean isEmptyFirstNum = false;
    boolean isEmptySecondNum = false;

    TextView goukeilabel;
    TextView keisanlabel;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonAc;
    Button buttonDivied;
    Button buttonMultiple;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonDot;
    Button buttonEqual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goukeilabel = findViewById(R.id.goukei);
        keisanlabel = findViewById(R.id.keisan);
        button1 = findViewById(R.id.one);
        button1.setOnTouchListener(touchButton);
        button2 = findViewById(R.id.tow);
        button3 = findViewById(R.id.three);
        button4 = findViewById(R.id.four);
        button5 = findViewById(R.id.five);
        button6 = findViewById(R.id.six);
        button7 = findViewById(R.id.seven);
        button8 = findViewById(R.id.eight);
        button9 = findViewById(R.id.nine);
        button0 = findViewById(R.id.zero);
        buttonAc = findViewById(R.id.AC);
        buttonDivied = findViewById(R.id.div);
        buttonMultiple = findViewById(R.id.mul);
        buttonMinus = findViewById(R.id.min);
        buttonPlus = findViewById(R.id.plu);
        buttonDot = findViewById(R.id.dot);
        buttonEqual = findViewById(R.id.equ);
    }

    private void reset() {
        setFirstNum(0);
        clearSecondNum();
        hugoBox = null;
        goukeilabel.setText("0");
        keisanlabel.setText("");
        isDot = false;
    }

    private void clearFirstNum(){
        firstNum = 0;
        isEmptyFirstNum = true;
    }
    private void setFirstNum(double num){
        firstNum = num;
        isEmptyFirstNum = false;
    }
    private void clearSecondNum(){
        secondNum = 0;
        isEmptySecondNum = true;
    }
    private void setSecondNum(double num){
        secondNum = num;
        isEmptySecondNum = false;
    }

    private View.OnTouchListener touchButton = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setBackgroundTintList( ColorStateList.valueOf(Color.BLUE));
                    break;
                case MotionEvent.ACTION_UP:
                    v.setBackgroundTintList( ColorStateList.valueOf(
                            ContextCompat.getColor(MainActivity.this, R.color.button_gray)));

                    String str = ((Button)v).getText().toString();
                    goukeilabel.setText(str);
                    /*

                    //firstNumがnilだったら、計算直後なので状態をリセットする
                    if firstNum == nil{
                        reset()
                    }

                    //例えば、0の時に3を押すと03じゃなくて3にする
                    if goukeilabel.text == "0" {
                        goukeilabel.text = button.currentTitle!
                    }
                    else {
                        goukeilabel.text =  goukeilabel.text! + button.currentTitle!
                    }

                    if hugoBox == nil{
                        //符号が押される前の数字を覚える
                        firstNum = NSDecimalNumber(string: goukeilabel.text!)
                    }
                    else{
                        //符号が押された後の数字を覚える
                        secondNum = NSDecimalNumber(string: goukeilabel.text!)
                    }
                     */
                    break;
            }
            return true;
        }
    };

}