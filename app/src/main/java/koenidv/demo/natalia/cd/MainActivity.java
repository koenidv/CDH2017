package koenidv.demo.natalia.cd;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int mCurColor = -1;
    private TextView mCityTextView;
    private ConstraintLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), 0);

        mCityTextView = findViewById(R.id.cityTextView);
        final Button mNewCityButton = findViewById(R.id.newCityButton);
        mBackground = findViewById(R.id.backgroundConstraintLayout);

        mBackground.setBackgroundColor(Color.WHITE);
        ColorDrawable colorDrawable = new ColorDrawable(manipulateColor(Color.WHITE, 0.6f));
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
        } catch (java.lang.NullPointerException npe) {
            npe.printStackTrace();
        }

        mNewCityButton.setBackground(new ColorDrawable(manipulateColor(Color.WHITE, 0.6f)));
        mNewCityButton.setTextColor(manipulateColor(Color.WHITE, 1.6f));

        mCityTextView.setText(settings.getString("greeting", getString(R.string.launch_city)));

        mNewCityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View mView) {

                String[] cities = {
                        settings.getString("city0", getString(R.string.defaultCity0)),
                        settings.getString("city1", getString(R.string.defaultCity1)),
                        settings.getString("city2", getString(R.string.defaultCity2)),
                        settings.getString("city3", getString(R.string.defaultCity3)),
                        settings.getString("city4", getString(R.string.defaultCity4)),
                        settings.getString("city5", getString(R.string.defaultCity5))
                };

                Random r = new Random();

                int a = r.nextInt(cities.length);

                while (cities[a].equals(mCityTextView.getText())) {
                    a = r.nextInt(cities.length);
                }

                mCityTextView.setText(cities[a]);

                if (settings.getBoolean("colors", true)) {
                    List<String> colors;
                    colors = new ArrayList<>();

                    if (settings.getBoolean("lightColors", true)) {
                        colors.add("#ffb3ba");
                        colors.add("#ffdfba");
                        colors.add("#ffffba");
                        colors.add("#baffc9");
                        colors.add("#bae1ff");
                    }
                    if (settings.getBoolean("darkColors", false)) {
                        colors.add("#5a0909");
                        colors.add("#dbbc3d");
                        colors.add("#3c2d2d");
                        colors.add("#4b1e1e");
                        colors.add("#431F3E");
                    }
                    if (settings.getBoolean("drearyColors", false)) {
                        colors.add("#1b85b8");
                        colors.add("#5a5255");
                        colors.add("#559e83");
                        colors.add("#ae5a41");
                        colors.add("#c3cb71");
                    }
                    if (settings.getBoolean("saturatedColors", false)) {
                        colors.add("#47f611");
                        colors.add("#6448bf");
                        colors.add("#0faae5");
                        colors.add("#b018e5");
                        colors.add("#ebf90b");
                    }
                    if (settings.getBoolean("mutedColors", true)) {
                        colors.add("#99b899");
                        colors.add("#feceab");
                        colors.add("#ff847c");
                        colors.add("#e84a5f");
                        colors.add("#2a363b");
                    }
                    if (settings.getBoolean("materialColors", true)) {
                        colors.add("#ef5350");
                        colors.add("#64b5f6");
                        colors.add("#ffee58");
                        colors.add("#66bb6a");
                        colors.add("#ab47bc");
                    }

                    if (colors.size() != 0) {

                        int thisColor = r.nextInt(colors.size());

                        while (thisColor == mCurColor) {
                            thisColor = r.nextInt(colors.size());
                        }

                        final ValueAnimator ColorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                                mCurColor == -1 ? Color.WHITE : Color.parseColor(colors.get(mCurColor)), Color.parseColor(colors.get(thisColor)));
                        ColorAnimator.setDuration(400);
                        ColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator mValueAnimator) {
                                mBackground.setBackgroundColor((int) ColorAnimator.getAnimatedValue());
                                ColorDrawable colorDrawable = new ColorDrawable(manipulateColor((int) ColorAnimator.getAnimatedValue(), 0.9f));
                                try {
                                    //noinspection ConstantConditions
                                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                                } catch (java.lang.NullPointerException npe) {
                                    npe.printStackTrace();
                                }

                                mNewCityButton.setBackground(new ColorDrawable(manipulateColor((int) ColorAnimator.getAnimatedValue(), 0.8f)));
                                mNewCityButton.setTextColor(manipulateColor((int) ColorAnimator.getAnimatedValue(), 1.6f));
                            }
                        });
                        ColorAnimator.start();

                        mCurColor = thisColor;
                    }
                }
            }
        });
    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.getItem(menu.size() - 1);
        SpannableString spanString = new SpannableString(menu.getItem(menu.size() - 1).getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, spanString.length(), 0); //fix the color to white
        item.setTitle(spanString);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.action_cdh) {
            try {
                PackageManager pm = this.getPackageManager();
                Intent i = pm.getLaunchIntentForPackage("foflelp.coderdojohelper");
                if (i != null) {
                    startActivity(i);
                } else {
                    Toast.makeText(this, getString(R.string.cdhNotInstalledException), Toast.LENGTH_LONG).show();
                }
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, getString(R.string.cdhNotInstalledException), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else if (id == R.id.action_koeniWs) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + getString(R.string.koeniWs)));
            startActivity(intent);
        } else if (id == R.id.action_cdWs) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + getString(R.string.cdWs)));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
