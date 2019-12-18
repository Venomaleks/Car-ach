package e.aleks.carach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.view.Display;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class GView extends View {


    // Backgrund Full screen
    Bitmap backgraund;
    Rect rect;
    int Shight, Swhith;

    // Car objects
    Bitmap car[] = new Bitmap[3];
    int carx, cary;
    int carspeed, carFrame;
    int carWhith, carHight;
    boolean carSwitch;

    // Player objects

    Bitmap player[] = new Bitmap[2];
    int playerx, playery, playerspeed, playerFrame;
    int playerWhith, playerHight;
    int PlayerHP;

    long Score = 0;
    long PlayerScore;


    // animations objects

    private static final long COUNTDOWN_MILLIS = 6000; //180000milli 180sec 3 min
    long Time = COUNTDOWN_MILLIS;
    Random rand;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 1;
    Thread start;
    Intent END;

    public GView(final Context context) {
        super(context);

//BACKGRAUND

        backgraund = BitmapFactory.decodeResource(getResources(), R.drawable.gatan); // hämta bild

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay(); //hur man ska vissa bild
        Point size = new Point(); // vart bilden ska sluta/börja

        // Build.VERSION_CODES.HONEYCOMB_MR2)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        }


//        display.getSize(size);

            Swhith = size.x;
            Shight = size.y;

            rect = new Rect(0, 0, Swhith, Shight);

/*
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/gatan"

*/

// CARS

        car[0] = BitmapFactory.decodeResource(getResources(), R.drawable.greencar);
        car[2] = BitmapFactory.decodeResource(getResources(), R.drawable.redcar);
        car[1] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowcar);

        carx = (Swhith / 2) - (Swhith / 21);
        cary = 0;
        carSwitch = false;
        carFrame = 0;
        carWhith = car[0].getWidth() * 4;
        carHight = car[0].getHeight() * 4;

//PLAYER

        player[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bred);
        player[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bred2);

        int pmid = (Swhith / 2) - (Swhith / 50);

        playerx = (Shight/2)+(Shight/5);
        // playerx = 1600;
        playery = pmid;

        // mid 700=(Swhith/2)-(Swhith/50)
        // left 1025=(Swhith/2)+(Swhith/5)
        // right 380 = (Swhith/2)-(Swhith/4)

        playerspeed = 0;
        playerFrame = 0;
        playerWhith = player[0].getWidth();
        playerHight = player[0].getHeight();
        PlayerHP = 3;

// Animation


        // Bundle Fullscore = new Bundle();

        rand = new Random();
        handler = new Handler();
        runnable = new Runnable() {


            @Override
            public void run() {

                if (Time != 0) ;
                invalidate();



                if (Time != 0) {

                    Time -= UPDATE_MILLIS;
                    Score += UPDATE_MILLIS;

                    if (Time <= 6000 && Time > 5000) {

                        if (carspeed == 0) {
                            carspeed += 30;

                        }
                    }
                    if (Time <= 5000 && Time > 4000) {

                        if (carspeed == 30) {
                            carspeed += 10;

                        }
                    }

                    if (Time <= 4000 && Time > 3000) {

                        if (carspeed == 40) {
                            carspeed += 10;

                        }
                    }

                    if (Time <= 3000 && Time > 2000) {

                        if (carspeed == 50) {
                            carspeed += 20;

                        }
                    }

                    if (Time <= 2000 && Time > 1000) {

                        if (carspeed == 70) {
                            carspeed += 20;

                        }
                    }
                    if (Time <= 1000 && Time > 0) {

                        if (carspeed == 90) {
                            carspeed += 10;

                        }

                    }

                }
               if (Time == 0 || PlayerHP == 0) {

                    carspeed = 0;

                    PlayerScore = Score + (PlayerHP * 10000);

                    context.equals(PlayerScore);

                    END = new Intent(context, finalscore.class);

                    if (END != null) {

                        try {

                            String FPC = Long.toString(PlayerScore);

                            END.putExtra("Score", FPC);
                            context.startActivities(new Intent[]{END});

                            Toast.makeText(context, "This might take a secend", Toast.LENGTH_LONG).show();

                            start.start();
                            Thread.sleep(10000);


                        } catch (Exception e) {

                        }

                    }

                }

            }

        };
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(backgraund, rect, rect, null);

        canvas.drawBitmap(player[playerFrame], playery, playerx, null);

        canvas.drawBitmap(car[carFrame], carx, cary, null);

        if (Time != 0) {

            // car moving
            cary += carspeed;

            // Player animaton
            if (cary < Shight / 2) {

                playerFrame = 0;

            }
            if (cary > Shight / 2) {


                playerFrame = 1;
            }

            // car position & replay
            if (cary > +Shight) {

                switch (carx = rand.nextInt(3) + 1) {


                    case 1:

                        // left
                        carx = (Swhith / 3) - (Swhith / 10);
                        //carx = 350;
                        break;

                    case 2:

                        // mid
                        carx = (Swhith / 2) - (Swhith / 21);
                        //carx = 665;
                        break;

                    case 3:
                        // right
                        carx = Swhith- (Swhith / 3);
                        //carx = 975;

                        break;
                }

                cary = 0;
            }

            // all crach senarios, by sying where on the screen it is supposet to be .

            int pmid = (Swhith / 2) - (Swhith / 50);
            int pright = (Swhith / 2) + (Swhith / 5);
            int pleft = (Swhith / 2) - (Swhith / 4);

            int playerxach = playery;

            int caryach = cary;
            int carxach = carx;

            //int Start = (Shight/2)+(Shight/9); //== 1440
            //int END = (Shight/2)+(Shight/4)+(Shight/100); //== 1770

            boolean cahy = (caryach >= ((Shight/2)+(Shight/9)) && caryach <= ((Shight/2)+(Shight/4)+(Shight/100)) );

            boolean cahMid = (carxach == ((Swhith / 2)-(Swhith / 21)) && playerxach == pmid);

            boolean cahLeft = (carxach == ((Swhith / 3)-(Swhith / 10)) && playerxach == pleft);

            boolean cahRight = (carxach == (Swhith- (Swhith / 3)) && playerxach == pright);

            if (cahMid == true || cahLeft == true || cahRight == true) {


                if (cahy == true) {


                    if (PlayerHP == 1) {

                        PlayerHP--;

                        cary = 0;
                    }
                    if (PlayerHP == 2) {

                        PlayerHP--;
                        carFrame = 2;
                        cary = 0;
                    }
                    if (PlayerHP == 3) {

                        carFrame = 1;
                        PlayerHP = 2;
                        cary = 0;

                    }

                    handler.postDelayed(runnable, UPDATE_MILLIS);


                }

            }

            handler.postDelayed(runnable, UPDATE_MILLIS);

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        // float touchY = event.getY();

        boolean left = (touchX > playery); // playery == 700
        boolean right = (touchX < playery); // playery == 700

        int pmid = (Swhith / 2) - (Swhith / 50);
        int pleft = (Swhith / 2) + (Swhith / 5);
        int pright = (Swhith / 2) - (Swhith / 4);

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {

            if (right) {

                if (pleft == playery) {
                    playery = pmid;

                }else if (pmid == playery)
                    playery = pright;


            } else if (left) {

                if (pmid == playery) {
                    playery = pleft;

                }else if (pright == playery)
                    playery = pmid;

            }


        }
        return true;

    }

}