package com.sonlcr1.baseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //참조 변수 생성
    EditText user_100,user_10,user_1;
    Button btn;
    TextView tv;
    int com, com_100,com_10,com_1;
    int my_100,my_10,my_1;
    int strike,ball;
    String s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //컴퓨터 랜덤값(정답) 설정하기, 각자리수 중복되면 안될것, 첫자리 0 아닐것
        while(true) {
            com = new Random().nextInt(999) + 1;
            com_100 = com/100;
            com_10 = com/10%10;
            com_1 = com%10;
            if(com_100!=0) {
                if (com_100 != com_10 &&
                        com_10 != com_1 &&
                        com_100 != com_1) break;
            }
        }

        //참조 변수 id로 참조 하기
        user_100 = findViewById(R.id.et_100);
        user_10 = findViewById(R.id.et_10);
        user_1 = findViewById(R.id.et_1);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //사용자가 입력한값 문자열에서 정수형으로 변환
                String s_100 = user_100.getText().toString();
                my_100 = Integer.parseInt(s_100);

                String s_10 = user_10.getText().toString();
                my_10 = Integer.parseInt(s_10);

                String s_1 = user_1.getText().toString();
                my_1 = Integer.parseInt(s_1);

                //사용자 값과 컴퓨터값 비교 하여 strike, ball 체크
                if(my_100==com_100) strike++;
                else if(my_100==com_10 ||
                        my_100==com_1) ball++;

                if(my_10==com_10) strike++;
                else if(my_10==com_100 ||
                        my_10==com_1) ball++;

                if(my_1==com_1) strike++;
                else if(my_1==com_100 ||
                        my_1==com_10) ball++;



                //결과값이 초기화 되지 않고 계속 다음줄로 이어지도록 설정
                s = s+my_100+my_10+my_1+" : "+strike+"strike, "+ball+"ball\n";  //   \n을 마지막에 붙이기
                tv.setText(s);

                //정답이 맞을경우 문구 생성
                if(my_100==com_100 &&
                        my_10==com_10 &&
                        my_1==com_1 ) {
                    tv.setText("축하합니다 정답입니다.");
                    tv.setGravity(Gravity.CENTER);
                }

                //strike,ball,EditText창 초기화
                strike = 0;
                ball = 0;

                user_100.setText("");
                user_10.setText("");
                user_1.setText("");
            }
        });




    }

}
