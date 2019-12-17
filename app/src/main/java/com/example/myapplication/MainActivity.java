package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    float firstNum = 0;
    float secondNum = 0;
    float result = 0;
    float prevR = 0;
    int opration = 0;
    int state = 0; // 0 is give first number 1 is give second number
    String textView = "";
    boolean numFreeze = false;

    boolean firstHaveDot = false;
    int firstDotNum = 0;

    boolean secondHaveDot = false;
    int secondDotNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public int getNumberCount(int num) {
        int numCount = 0;
        while (num >= 1) {
            num = num / 10;
            numCount++;
        }
        return numCount;
    }

    public void acClicked(View view) {
        this.completeRestart();
    }

    public void  completeRestart() {
        this.restart();
        TextView secondTextView = (TextView)findViewById(R.id.textView2);
        secondTextView.setText("Result");

        TextView firstTextView = (TextView)findViewById(R.id.textView);
        firstTextView.setText("Your statement");
    }

    public void restart() {
        TextView firstTextView = (TextView)findViewById(R.id.textView);
        firstTextView.setText("");
        firstNum = 0;
        secondNum = 0;
        state = 0;
        textView = "";
        numFreeze = false;

        firstHaveDot = false;
        firstDotNum = 0;

        secondHaveDot = false;
        secondDotNum = 0;
    }

    public void equal() {
        if (firstHaveDot && state == 0) {
            firstNum = firstNum + (float) firstDotNum / this.pow(10 , getNumberCount(firstDotNum));
        } else if(secondHaveDot && state == 1) {
            secondNum = secondNum + (float) secondDotNum / this.pow(10 , getNumberCount(secondDotNum));
        }

        TextView secondTextView = (TextView)findViewById(R.id.textView2);

        if (opration == 1) {
            result = firstNum + secondNum;
        } else if(opration == 2) {
            result = firstNum - secondNum;
        } else if(opration == 3) {
            result = firstNum / secondNum;
        } else if(opration == 4) {
            result = firstNum * secondNum;
        }

        secondTextView.setText("" + result + "");
        prevR = result;
        this.restart();
    }

    public void equalClicked(View view) {
        this.equal();
    }

    public void percentClicked(View view) {
        TextView firstTextView = (TextView) findViewById(R.id.textView);

        if (state == 0 && firstNum != 0) {
            firstNum = firstNum / 100;
            textView = textView.concat("%");
            firstTextView.setText(textView);
            numFreeze = true;
        } else if (state == 1 && secondNum !=0) {
            secondNum = secondNum/ 100;
            textView = textView.concat("%");
            firstTextView.setText(textView);
            this.equal();
        }
    }

    public void backClicked(View view) {
        textView = this.removeLastChar(textView);
        TextView firstTextView = (TextView)findViewById(R.id.textView);
        firstTextView.setText(textView);
        if(state == 0) {
            firstNum = (int) firstNum / 10;
        } else if (state == 1) {
            if (secondNum == 0) {
                state = 0;
            }
            secondNum = (int) secondNum / 10;
        }

    }

    public String removeLastChar(String str) {
        if (str.length() > 0 && str != null) {
            return str.substring(0, str.length() - 1);
        } else {
            return str;
        }
    }

    public void dotClicked(View view) {
        TextView firstTextView = (TextView) findViewById(R.id.textView);
        textView = textView.concat(".");
        firstTextView.setText(textView);
        if (state == 0) {
            firstHaveDot= true;
            secondHaveDot = false;
        } else if(state == 1) {
            firstHaveDot = false;
            secondHaveDot = true;
        }
    }

    public int pow(int a, int b) {
        int result = 1;
        for(int i=0;i<b;i++) {
            result *= a;
        }

        return result;
    }

    public void oprationFunc(int oprationNum) {
        if (firstHaveDot && state == 0) {
            firstNum = firstNum + (float) firstDotNum / this.pow(10 , getNumberCount(firstDotNum));
        } else if(secondHaveDot && state == 1) {
            secondNum = secondNum + (float) secondDotNum / this.pow(10 , getNumberCount(secondDotNum));
        }

        numFreeze = false;

        if (state == 0  && firstNum == 0 ) {
            firstNum =prevR;
            textView = textView.concat("" + firstNum + "");
        } else if (state == 1) {
                this.equal();
                firstNum = prevR;
                textView = textView.concat("" + prevR + "");
        }

        TextView firstTextView = (TextView) findViewById(R.id.textView);
        opration = oprationNum;
        if (oprationNum == 1)
            textView = textView.concat("+");
        else if (oprationNum == 2)
            textView = textView.concat("-");
        else if (oprationNum == 3)
            textView = textView.concat("/");
        else if (oprationNum == 4)
            textView = textView.concat("*");

        state = 1;

        firstTextView.setText(textView);
    }

    public void plusClicked(View view) {
        this.oprationFunc(1);
    }

    public void minusClicked(View view) {
        this.oprationFunc(2);
    }

    public void divideClicked(View view) {
        this.oprationFunc(3);
    }

    public void multipleClicked(View view) {
        this.oprationFunc(4);
    }

    public void buttonFunc(int num) {
        if (!numFreeze) {
            TextView firstTextView = (TextView) findViewById(R.id.textView);
            TextView secondTextView = (TextView) findViewById(R.id.textView2);

            if (state == 0 && firstNum == 0) {
                secondTextView.setText("");
            }

            textView = textView.concat("" + num + "");
            firstTextView.setText(textView);
            if (firstHaveDot && state == 0) {
                firstDotNum *= 10;
                firstDotNum += num;
            } else if(secondHaveDot && state == 1) {
                secondDotNum *= 10;
                secondDotNum += num;
            } else if (state == 0) {
                firstNum *= 10;
                firstNum += num;
            } else if (state == 1) {
                secondNum *= 10;
                secondNum += num;
            }
        }
    }

    public void zeroClicked(View view) {
        this.buttonFunc(0);
    }

    public void oneClicked(View view) {
        this.buttonFunc(1);
    }

    public void twoClicked(View view) {
        this.buttonFunc(2);
    }

    public void threeClicked(View view) {
        this.buttonFunc(3);
    }

    public void fourClicked(View view) {
        this.buttonFunc(4);
    }

    public void fiveClicked(View view) {
        this.buttonFunc(5);
    }

    public void sixClicked(View view) {
        this.buttonFunc(6);
    }

    public void sevenClicked(View view) {
        this.buttonFunc(7);
    }

    public void eightClicked(View view) {
        this.buttonFunc(8);
    }

    public void nineClicked(View view) {
        this.buttonFunc(9);
    }
}
