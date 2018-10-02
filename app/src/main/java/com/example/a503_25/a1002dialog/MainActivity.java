package com.example.a503_25.a1002dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button player;
    //선택 여부를 판단하기 위한 배열
    boolean [] mSelect = {false,false,false,false,false,false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (Button)findViewById(R.id.player);

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("NBA 선수").setIcon(R.drawable.james)
                        .setMultiChoiceItems(R.array.player, mSelect, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(MainActivity.this,"다이어로그",Toast.LENGTH_SHORT).show();
                                //선택한 항목 위치의 값에 선택여부를 저장
                                mSelect[which] = isChecked;
                                if(isChecked==true){
                                    Toast.makeText(MainActivity.this,which+1 + "번 체크",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("취소",null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //array.xml 파일에 만든 문자열 배열 가져오기
                                String []  player = getResources().getStringArray(R.array.player);
                                //선택 여부가 저장되어 있는 배열을 순회하면서
                                //true 일 때 player의 데이터를 문자열에 추가
                                String result ="";
                                for(int i=0; i<mSelect.length; i= i+1) {
                                    //mSelect[i]가 트루일때
                                    if (mSelect[i]) {
                                        result = result + player[i] + "\t";
                                    }
                                }
                                //선택한 데이터 문자열을 토스트로 출력
                                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });


        Button customdialog = (Button)findViewById(R.id.customdialog);
        customdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //레이아웃 파일의 내용 불러오기
                //anonymous 클래스에서 사용하기 위해서 final을 붙임
                //anonymous 클래스에서는 자신을 포함하는 메소드의 지역변수를 사용할 수 없습니다,
                //final은 가능합니다.
                final LinearLayout login = (LinearLayout)View.inflate(MainActivity.this,R.layout.login,null);
                new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.james).setTitle("로그인").setView(login).setNegativeButton("취소",null).setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText id = (EditText)login.findViewById(R.id.id);
                        EditText password =(EditText)login.findViewById(R.id.password);

                        String result = "id :" + id.getText().toString() + "password:" + password.getText().toString();
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();

                    }
                }).show();
            }
        });
        //popup.xml 내용을 가지는 뷰를 생성
        final LinearLayout popup = (LinearLayout)View.inflate(MainActivity.this,R.layout.popup,null);
        final Button popupbtn = (Button)findViewById(R.id.popupwindow);
        popupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //팝업 윈도우
                //true는 포커스를 받을수 있을지 없을 지
                //크기
                final PopupWindow popupWindow = new PopupWindow(popup,500,500,true);
                //화면 출력
                //두번째와 세번째는 좌표 네번째 포커스 여부 첫번째는 부모뷰 아무나 가져다 써라
                //출력위치
                popupWindow.showAtLocation(popupbtn,Gravity.CENTER,100,100);

                // 팝업 닫는 코드 위에 final은 익명으로 만들어진 클래스여서 파이널을 붙여줘야 밑어서 사용가능
                Button close = (Button)popup.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });



    }
}
