
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex4.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_paint=findViewById(R.id.bt_paint);
        Button bt_animate=findViewById(R.id.bt_animate);
        Button bt_animate_fade=findViewById(R.id.bt_animate_fade);
        Button bt_animate_rotate=findViewById(R.id.bt_animate_rotate);
        Button bt_car=findViewById(R.id.bt_car);
        Button bt_forward=findViewById(R.id.bt_forward);
        Button bt_backward=findViewById(R.id.bt_backward);
        Button bt_circle=findViewById(R.id.bt_circle);
        Button bt_line=findViewById(R.id.bt_line);
        final Bitmap b=Bitmap.createBitmap(720,1280, Bitmap.Config.ARGB_8888);;
        final ImageView iv_animate=findViewById(R.id.iv_animate);

        bt_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in));
            }
        });
        bt_animate_fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
            }
        });
        bt_animate_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_in));
            }
        });
        bt_line.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                iv_animate.setImageResource(android.R.color.transparent);
                iv_animate.setBackgroundDrawable(new BitmapDrawable(b));
                Canvas canvas=new Canvas(b);
                Paint p=new Paint();
                p.setStrokeWidth(10);
                p.setColor(Color.RED);
                canvas.drawLine(100,100,600,600,p);
            }
        });
        bt_circle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                iv_animate.setImageResource(android.R.color.transparent);
                iv_animate.setBackgroundDrawable(new BitmapDrawable(b));
                Canvas canvas=new Canvas(b);
                Paint p=new Paint();
                p.setStrokeWidth(10);
                p.setColor(Color.RED);
                RectF r=new RectF(100,100,400,300);
                canvas.drawArc(100,100,400,300,0,360,true,p);

            }
        });
        bt_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.setImageResource(android.R.color.transparent);
                iv_animate.setBackgroundDrawable(new BitmapDrawable(b));
                Canvas canvas=new Canvas(b);
                Paint p=new Paint();
                p.setStrokeWidth(10);
                p.setColor(Color.RED);
                RectF r=new RectF(100,100,400,300);
                canvas.drawRect(r,p);
            }
        });

        bt_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.setImageResource(R.drawable.car);
            }
        });

        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(300f).setDuration(600);
            }
        });
        bt_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(-300f).setDuration(600);
            }
        });
    }
}
