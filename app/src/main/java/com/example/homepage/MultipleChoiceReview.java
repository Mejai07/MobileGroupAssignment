package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MultipleChoiceReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_review);
        Button frbt=findViewById(R.id.button20);
        frbt.setTextColor(Color.BLACK);
        frbt.setEnabled(false);

        FileOutputStream fos;
        try {
            String reset="";
            fos = openFileOutput("Review.txt",
                    MODE_PRIVATE);
            fos.write(reset.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button firstbt=findViewById(R.id.button18);
        Button secondbt=findViewById(R.id.button9);
        Button thirdbt=findViewById(R.id.button14);
        Button fourthbt=findViewById(R.id.button15);

        Flashcard fc1=new Flashcard("Name","how to call someone");
        Flashcard fc2=new Flashcard("age","how many years someone live");
        Flashcard fc3=new Flashcard("Gender","male or female");
        Flashcard fc4=new Flashcard("parent","father and mother");
        Flashcard fc5=new Flashcard("family","include all relatives");
        String[] front={fc1.Front,fc2.Front,fc3.Front,fc4.Front,fc5.Front};
        String[] back={fc1.Back,fc2.Back,fc3.Back,fc4.Back,fc5.Back};
        Flashcard[] set;
        set= new Flashcard[]{fc1, fc2, fc3, fc4, fc5};
        SharedPreferences pref = getSharedPreferences("MySharedPreferences", 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putInt("sequence2",0);
        prefEditor.commit();
        prefEditor.putInt("BRC",0);
        prefEditor.commit();
        prefEditor.putInt("BRW",0);
        prefEditor.commit();
        frbt.setText(front[0]);




        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wrong!");
        builder.setMessage("");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();






            int se=pref.getInt("sequence2",0);
            Getnum gt=new Getnum();


                int nu=0;
                    frbt.setText(front[nu]);

                    while (true) {
                        int num1 = 0 + (int) (Math.random() * back.length);
                        int num2 = 0 + (int) (Math.random() * back.length);
                        int num3 = 0 + (int) (Math.random() * back.length);
                        int num4 = 0 + (int) (Math.random() * back.length);

                        if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3 && num2 != num4 && num3 != num4) {
                            if (num1==nu||num2==nu||num3==nu||num4==nu) {
                                firstbt.setText(back[num1]);
                                secondbt.setText(back[num2]);
                               thirdbt.setText(back[num3]);
                                fourthbt.setText(back[num4]);
                                       if(num1==nu)
                                        gt.num=1;
                                       if(num2==nu)
                                        gt.num=2;
                                       if(num3==nu)
                                        gt.num=3;
                                        if(num4==nu)
                                         gt.num=4;
                                break;
                            }
                        }
                    }




                    firstbt.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int num=pref.getInt("sequence2",0);

                            if(gt.num!=1) {
                                alert.show();
                                FileOutputStream fos;
                                try {
                                    fos = openFileOutput("Review.txt",
                                            MODE_APPEND);
                                    String input=front[num]+" : "+back[num]+"\n";
                                    fos.write(input.getBytes());
                                    fos.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                int w = pref.getInt("BRW", 0);
                                w = w + 1;
                                prefEditor.putInt("BRW", w);
                                prefEditor.commit();
                            }
                            else {
                                int c = pref.getInt("BRC", 0);
                                c = c + 1;
                                prefEditor.putInt("BRC", c);
                                prefEditor.commit();
                            }
                            num=num+1;
                            if (num == set.length) {
                                Intent t = new
                                        Intent(MultipleChoiceReview.this,  ReviewResult.class);
                                startActivity(t);
                                finish();
                            }
                                 prefEditor.putInt("sequence2",num);
                                 prefEditor.commit();
                                 frbt.setText(front[num]);
                            while (true) {
                                int num1 = 0 + (int) (Math.random() * back.length);
                                int num2 = 0 + (int) (Math.random() * back.length);
                                int num3 = 0 + (int) (Math.random() * back.length);
                                int num4 = 0 + (int) (Math.random() * back.length);

                                if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3 && num2 != num4 && num3 != num4) {
                                    if (num1==num||num2==num||num3==num||num4==num) {
                                        firstbt.setText(back[num1]);
                                        secondbt.setText(back[num2]);
                                        thirdbt.setText(back[num3]);
                                        fourthbt.setText(back[num4]);
                                        if(num1==num)
                                            gt.num=1;
                                        if(num2==num)
                                            gt.num=2;
                                        if(num3==num)
                                            gt.num=3;
                                        if(num4==num)
                                            gt.num=4;
                                        break;
                                    }
                                }
                            }


                        }
                    });

                      secondbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int num=pref.getInt("sequence2",0);
                if(gt.num!=2) {

                    alert.show();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("Review.txt",
                                MODE_APPEND);
                        String input=front[num]+" : "+back[num]+"\n";
                        fos.write(input.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int w = pref.getInt("BRW", 0);
                    w = w + 1;
                    prefEditor.putInt("BRW", w);
                    prefEditor.commit();
                }
                else {
                    int c = pref.getInt("BRC", 0);
                    c = c + 1;
                    prefEditor.putInt("BRC", c);
                    prefEditor.commit();
                }

                   num=num+1;
                if (num == set.length) {
                    Intent t = new
                            Intent(MultipleChoiceReview.this,  ReviewResult.class);
                    startActivity(t);
                    finish();
                }
                prefEditor.putInt("sequence2",num);
                prefEditor.commit();
                frbt.setText(front[num]);
                while (true) {
                    int num1 = 0 + (int) (Math.random() * back.length);
                    int num2 = 0 + (int) (Math.random() * back.length);
                    int num3 = 0 + (int) (Math.random() * back.length);
                    int num4 = 0 + (int) (Math.random() * back.length);

                    if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3 && num2 != num4 && num3 != num4) {
                        if (num1==num||num2==num||num3==num||num4==num) {
                            firstbt.setText(back[num1]);
                            secondbt.setText(back[num2]);
                            thirdbt.setText(back[num3]);
                            fourthbt.setText(back[num4]);
                            if(num1==num)
                                gt.num=1;
                            if(num2==num)
                                gt.num=2;
                            if(num3==num)
                                gt.num=3;
                            if(num4==num)
                                gt.num=4;
                            break;
                        }
                    }
                }

            }
        });


                      thirdbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int num=pref.getInt("sequence2",0);
                if(gt.num!=3) {
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("Review.txt",
                                MODE_APPEND);
                        String input=front[num]+" : "+back[num]+"\n";
                        fos.write(input.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    alert.show();
                    int w = pref.getInt("BRW", 0);
                    w = w + 1;
                    prefEditor.putInt("BRW", w);
                    prefEditor.commit();
                }
                else {
                    int c = pref.getInt("BRC", 0);
                    c = c + 1;
                    prefEditor.putInt("BRC", c);
                    prefEditor.commit();
                }

                num=num+1;
                if (num == set.length) {
                    Intent t = new
                            Intent(MultipleChoiceReview.this,  ReviewResult.class);
                    startActivity(t);
                    finish();
                }
                prefEditor.putInt("sequence2",num);
                prefEditor.commit();
                frbt.setText(front[num]);
                while (true) {
                    int num1 = 0 + (int) (Math.random() * back.length);
                    int num2 = 0 + (int) (Math.random() * back.length);
                    int num3 = 0 + (int) (Math.random() * back.length);
                    int num4 = 0 + (int) (Math.random() * back.length);

                    if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3 && num2 != num4 && num3 != num4) {
                        if (num1==num||num2==num||num3==num||num4==num) {
                            firstbt.setText(back[num1]);
                            secondbt.setText(back[num2]);
                            thirdbt.setText(back[num3]);
                            fourthbt.setText(back[num4]);
                            if(num1==num)
                                gt.num=1;
                            if(num2==num)
                                gt.num=2;
                            if(num3==num)
                                gt.num=3;
                            if(num4==num)
                                gt.num=4;
                            break;
                        }
                    }
                }
            }
        });


       fourthbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int num=pref.getInt("sequence2",0);
                if(gt.num!=4) {
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("Review.txt",
                                MODE_APPEND);
                        String input=front[num]+" : "+back[num]+"\n";
                        fos.write(input.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    alert.show();
                    int w = pref.getInt("BRW", 0);
                    w = w + 1;
                    prefEditor.putInt("BRW", w);
                    prefEditor.commit();
                }
                else {
                    int c = pref.getInt("BRC", 0);
                    c = c + 1;
                    prefEditor.putInt("BRC", c);
                    prefEditor.commit();
                }

                num=num+1;
                if (num == set.length) {
                    Intent t = new
                            Intent(MultipleChoiceReview.this,  ReviewResult.class);
                    startActivity(t);
                    finish();
                }

                prefEditor.putInt("sequence2",num);
                prefEditor.commit();
                frbt.setText(front[num]);
                while (true) {
                    int num1 = 0 + (int) (Math.random() * back.length);
                    int num2 = 0 + (int) (Math.random() * back.length);
                    int num3 = 0 + (int) (Math.random() * back.length);
                    int num4 = 0 + (int) (Math.random() * back.length);

                    if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3 && num2 != num4 && num3 != num4) {
                        if (num1==num||num2==num||num3==num||num4==num) {
                            firstbt.setText(back[num1]);
                            secondbt.setText(back[num2]);
                            thirdbt.setText(back[num3]);
                            fourthbt.setText(back[num4]);
                            if(num1==num)
                                gt.num=1;
                            if(num2==num)
                                gt.num=2;
                            if(num3==num)
                                gt.num=3;
                            if(num4==num)
                                gt.num=4;
                            break;
                        }
                    }
                }
            }
        });



























    }
}