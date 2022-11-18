package jp.ay.kadai1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    String hugoBox = null;
    double firstNum = 0;
    double secondNum = 0;
    boolean isDot = false;
    boolean isAfterKeisan = false;
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
        button1.setOnTouchListener(clickNum);
        button2 = findViewById(R.id.tow);
        button2.setOnTouchListener(clickNum);
        button3 = findViewById(R.id.three);
        button3.setOnTouchListener(clickNum);
        button4 = findViewById(R.id.four);
        button4.setOnTouchListener(clickNum);
        button5 = findViewById(R.id.five);
        button5.setOnTouchListener(clickNum);
        button6 = findViewById(R.id.six);
        button6.setOnTouchListener(clickNum);
        button7 = findViewById(R.id.seven);
        button7.setOnTouchListener(clickNum);
        button8 = findViewById(R.id.eight);
        button8.setOnTouchListener(clickNum);
        button9 = findViewById(R.id.nine);
        button9.setOnTouchListener(clickNum);
        button0 = findViewById(R.id.zero);
        button0.setOnTouchListener(clickNum);
        buttonAc = findViewById(R.id.AC);
        buttonAc.setOnTouchListener(clickAC);
        buttonDivied = findViewById(R.id.div);
        buttonDivied.setOnTouchListener(clickhugo);
        buttonMultiple = findViewById(R.id.mul);
        buttonMultiple.setOnTouchListener(clickhugo);
        buttonMinus = findViewById(R.id.min);
        buttonMinus.setOnTouchListener(clickhugo);
        buttonPlus = findViewById(R.id.plu);
        buttonPlus.setOnTouchListener(clickhugo);
        buttonDot = findViewById(R.id.dot);
        buttonDot.setOnTouchListener(clickDot);
        buttonEqual = findViewById(R.id.equ);
        buttonEqual.setOnTouchListener(clickEqual);
    }

    //全ての値を初期化
    private void reset() {
        firstNum = 0;
        secondNum = 0;
        hugoBox = null;
        goukeilabel.setText("0");
        keisanlabel.setText("");
        isDot = false;
        isAfterKeisan = false;
        isEmptySecondNum = true;
    }


    //数字が押された時の処理
    private View.OnTouchListener clickNum = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            changeButtonTint(v, event);

            if(event.getAction() == MotionEvent.ACTION_UP) {
                //計算直後だったら状態をリセットする
                if(isAfterKeisan == true) {
                    reset();
                }

                String buttonNum = ((Button)v).getText().toString();
                //例えば、0の時に3を押すと03じゃなくて3にする
                if(goukeilabel.getText().toString().equals("0")){
                    goukeilabel.setText(buttonNum);
                }
                else{
                    goukeilabel.setText(goukeilabel.getText() + buttonNum);
                }

                if(hugoBox == null){
                    //符号が押される前の数字を覚える
                    firstNum = Double.parseDouble(goukeilabel.getText().toString());
                }

                else {
                    //符号が押された後の数字を覚える
                    secondNum = Double.parseDouble(goukeilabel.getText().toString());
                    isEmptySecondNum = false;
                }
            }
            return true;
        }
    };

    //符号が押された時の処理
    private View.OnTouchListener clickhugo = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            changeButtonTint(v, event);

            if(event.getAction() == MotionEvent.ACTION_UP) {
                //計算直後に符号が押されたら、次の計算を始める
                // 例：「1」「+」「1」「=」のあとに「×」を押すと、「2×」の状態にする。
                if(isAfterKeisan == true){
                    firstNum = Double.parseDouble(goukeilabel.getText().toString());
                }

                //連続で計算が行われた時の処理
                // 例：「1」「+」「1」のときに「×」を押すと、「2×」の状態にする。
                if(secondNum != 0){
                    firstNum = keisan();
                    secondNum = 0;
                    isEmptySecondNum = true;
                }

                hugoBox = ((Button)v).getText().toString();
                keisanlabel.setText(firstNum + hugoBox);
                goukeilabel.setText("0");
                isDot = false;
                isAfterKeisan = false;
            }
            return true;
        }

    };

    //＝が押された時の処理
    private View.OnTouchListener clickEqual = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            changeButtonTint(v, event);

            if(event.getAction() == MotionEvent.ACTION_UP) {
                //計算直後だったら状態をリセットする
                if(isAfterKeisan == true) {
                    reset();
                }
                else{
                    //計算して結果を表示
                    goukeilabel.setText(String.valueOf(keisan()));

                    //各プロパティを初期化
                    keisanlabel.setText("");
                    firstNum = 0;
                    secondNum = 0;
                    hugoBox = null;
                    isDot = false;
                    isAfterKeisan = true;
                    isEmptySecondNum = true;

                }
            }
            return true;
        }
    };

    //.が押された時の処理
    private View.OnTouchListener clickDot = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            changeButtonTint(v, event);

            String buttonNum = ((Button)v).getText().toString();
            if(event.getAction() == MotionEvent.ACTION_UP) {
                //計算直後だったら状態をリセットする
                if(isAfterKeisan == true){
                    reset();
                }

                //.が押されていたら何もしない
                if(isDot == true){
                    return true;
                }
                goukeilabel.setText(goukeilabel.getText() + buttonNum);
                isDot = true;
            }
            return true;
        }
    };

    //ACが押された時の処理
    private View.OnTouchListener clickAC = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            changeButtonTint(v, event);

            if(event.getAction() == MotionEvent.ACTION_UP) {
                reset();
            }
            return true;
        }
    };

    //ボタンの色をオレンジ⇔グレーで反転させる
    private void changeButtonTint(View v, MotionEvent event){
        int orangeColor = ContextCompat.getColor(MainActivity.this, R.color.button_orange);
        int grayColor = ContextCompat.getColor(MainActivity.this, R.color.button_gray);

        //ボタンを押すか、もしくはボタンを離したら色を反転
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            if(v.getBackgroundTintList().getDefaultColor() == orangeColor){
                v.setBackgroundTintList(ColorStateList.valueOf(grayColor));
            }
            else{
                v.setBackgroundTintList( ColorStateList.valueOf(orangeColor));
            }
        }
    }
    private double keisan(){

        double result = 0;

        //符号ボックスが空かどうかを調べる
        if(hugoBox == null){
            return result;
        }

        //例えば2+=は2+2をする
        if(isEmptySecondNum == true){
            secondNum = firstNum;
            isEmptySecondNum = false;
        }

        switch(hugoBox){
            case "+":
                //足し算の処理->adding
                result = firstNum+secondNum;
                break;
            case "-":
                //引き算の処理->subtracting
                result = firstNum-secondNum;
                break;
            case "×":
                //掛け算の処理->multiplying
                result = firstNum*secondNum;
                break;
            case "÷":
                //割り算の処理->dividing
                result = firstNum/secondNum;
                break;
            default:
                break;
        }

        return result;
    }
}