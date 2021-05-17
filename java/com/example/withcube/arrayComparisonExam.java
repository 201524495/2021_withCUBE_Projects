package com.example.withcube;
// 배열놀이 - 대소비교 실제 문제 푸는 화면
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class arrayComparisonExam extends AppCompatActivity {

    ImageButton btn_larger, btn_equal, btn_smaller;
    Button btn_hint;
    ImageButton btn_back;
    TextView tv_instruction, tv_correct;
    ImageView im_val1, im_val2;
    public int correct = 1;
    public int correctTimes, totalTimes, val1, val2; // 맞춘 횟수, 총 횟수
    
    public static final int compareTwoPicture[] = {R.drawable.word_airplane, R.drawable.word_snake, R.drawable.word_dog, R.drawable.word_airplane, R.drawable.word_snake, R.drawable.word_dog,
            R.drawable.word_airplane, R.drawable.word_snake, R.drawable.word_dog, R.drawable.word_airplane, R.drawable.word_snake, R.drawable.word_dog}; // 비교할 사진 // 0 ~ 9 까지 순서대로 

    Random random = new Random(); // Random 함수 활성화
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_comparison_exam);
        Log.e("Compareex","Compare Exam Activity");


//        arrayComparisonInstruction arraycomparisoninstruction = (arrayComparisonInstruction)arrayComparisonInstruction.arraycomparisoninstruction;
//        arraycomparisoninstruction.finish(); // instruction activity 종료
        //
        im_val1 = findViewById(R.id.im_exam_val1); // 첫 번째 이미지
        im_val2 = findViewById(R.id.im_exam_val2); // 두 번째 이미지
        tv_correct = findViewById(R.id.tv_correct); // 맞춘 횟수
        tv_instruction = findViewById(R.id.tv_instruction); //
        correctTimes = 0; totalTimes = 5;
        tv_correct.setText("( "+correctTimes+" / "+totalTimes+" )");

        // 버튼 활성화
        btn_equal = findViewById(R.id.btn_exam_equal);
        btn_larger = findViewById(R.id.btn_exam_larger);
        btn_smaller = findViewById(R.id.btn_exam_smaller);


        makeRand(); // 난수 val1, val2 생성
        //    if(correct == 1 && correctTimes<6) {
//            correct = 0;
//            while(correctTimes<6) { // 5번 반복
                Log.e("Compareex", "val1 : " + val1 + " val2 : " + val2);
        randImage(); // val1, val2에 해당하는 그림 생성
        tv_instruction.setText("val1 : "+val1+" val2 : "+val2);

                btn_larger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (val1 > val2) {
                            Log.e("Compareex", "Click Larger Button");
                            RandomLogic();
                        }
                    }
                });

                btn_equal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(val1 == val2) {
                            Log.e("Compareex", "Click Equal Button");
                            RandomLogic();
                        }
                    }
                });

                btn_smaller.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(val1 < val2) {
                            Log.e("Compareex", "Click Larger Button");
                            RandomLogic();
                        }
                    }
                });
                Log.e("compareex","correctTime : " + correctTimes);

        // 힌트 팝업
        btn_hint = findViewById(R.id.btn_hint);
        btn_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), arrayComparisonPopUp.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 == 메뉴 이동 == 액티비티 종료
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void RandomLogic() {
//        correct = 1;
        correctTimes++;
        if(correctTimes < 5) // 5 문제 다 맞췄을 때 그림 변화가 없어야 함
            makeRand();
        tv_correct.setText("( " + correctTimes + " / " + totalTimes + " )");
        randImage();
        tv_instruction.setText("val1 : " + val1 + " val2 : " + val2);
        Log.e("Compareex", "correct : " + correct + " val1 : " + val1 + " val2 : " + val2);

        checkTimes();
    }

    private void randImage() {
        im_val1.setImageResource(compareTwoPicture[val1]); // 랜덤 값으로 이미지 선정
        im_val2.setImageResource(compareTwoPicture[val2]); // 랜덤 값으로 이미지 선정
    }

    private void makeRand() {
        val1 = random.nextInt(10); // val1 값을 0 ~ 10으로 설정
        val2 = random.nextInt(10); // val2 값을 0 ~ 10으로 설정
    }

    private void checkTimes() {
        if (correctTimes >= 5) { // 5번 이상 맞추면
            tv_instruction.setText("참 잘했어요");
//            btn_back.setText("끝");
            btn_equal.setVisibility(View.INVISIBLE);
            btn_larger.setVisibility(View.INVISIBLE);
            btn_smaller.setVisibility(View.INVISIBLE);
            im_val1.setImageResource(R.drawable.withrobot_orange_01);
            im_val2.setImageResource(R.drawable.withrobot_red_01);
        }
    }
}