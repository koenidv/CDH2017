package koenidv.demo.natalia.cd;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class SettingsActivity extends AppCompatActivity {

    private EditText mCityEditText0;
    private EditText mCityEditText1;
    private EditText mCityEditText2;
    private EditText mCityEditText3;
    private EditText mCityEditText4;
    private EditText mCityEditText5;

    private Switch mColorsSwitch;
    private CheckBox mLightColorsCheckBox;
    private CheckBox mDarkColorsCheckBox;
    private CheckBox mDrearyColorsCheckBox;
    private CheckBox mSaturatedColorsCheckBox;
    private CheckBox mMutedColorsCheckBox;
    private CheckBox mMaterialColorsCheckBox;

    private TextView mLightColorsDemoTextView;
    private TextView mDarkColorsDemoTextView;
    private TextView mDrearyColorsDemoTextView;
    private TextView mSaturatedColorsDemoTextView;
    private TextView mMutedColorsDemoTextView;
    private TextView mMaterialColorsDemoTextView;

    private EditText mGreetingEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), 0);
        final SharedPreferences.Editor settingsEditor = settings.edit();

        mCityEditText0 = findViewById(R.id.cityEditText0);
        mCityEditText1 = findViewById(R.id.cityEditText1);
        mCityEditText2 = findViewById(R.id.cityEditText2);
        mCityEditText3 = findViewById(R.id.cityEditText3);
        mCityEditText4 = findViewById(R.id.cityEditText4);
        mCityEditText5 = findViewById(R.id.cityEditText5);

        mColorsSwitch = findViewById(R.id.colorsSwitch);
        mLightColorsCheckBox = findViewById(R.id.lightColorsCheckBox);
        mDarkColorsCheckBox = findViewById(R.id.darkColorsCheckBox);
        mDrearyColorsCheckBox = findViewById(R.id.drearyColorsCheckBox);
        mSaturatedColorsCheckBox = findViewById(R.id.saturatedColorsCheckBox);
        mMutedColorsCheckBox = findViewById(R.id.mutedColorsCheckBox);
        mMaterialColorsCheckBox = findViewById(R.id.materialColorsCheckBox);

        mLightColorsDemoTextView = findViewById(R.id.lightColorsDemoTextView);
        mDarkColorsDemoTextView = findViewById(R.id.darkColorsDemoTextView);
        mDrearyColorsDemoTextView = findViewById(R.id.drearyColorsDemoTextView);
        mSaturatedColorsDemoTextView = findViewById(R.id.saturatedColorsDemoTextView);
        mMutedColorsDemoTextView = findViewById(R.id.mutedColorsDemoTextView);
        mMaterialColorsDemoTextView = findViewById(R.id.materialColorsDemoTextView);

        mGreetingEditText = findViewById(R.id.greetingEditText);


        mCityEditText0.setText(settings.getString("city0", getString(R.string.defaultCity0)));
        mCityEditText1.setText(settings.getString("city1", getString(R.string.defaultCity1)));
        mCityEditText2.setText(settings.getString("city2", getString(R.string.defaultCity2)));
        mCityEditText3.setText(settings.getString("city3", getString(R.string.defaultCity3)));
        mCityEditText4.setText(settings.getString("city4", getString(R.string.defaultCity4)));
        mCityEditText5.setText(settings.getString("city5", getString(R.string.defaultCity5)));

        /*ToDo: Own Color/s (Disables other options)*/
        mColorsSwitch.setChecked(settings.getBoolean("colors", true));
        mLightColorsCheckBox.setChecked(settings.getBoolean("lightColors", true));
        mDarkColorsCheckBox.setChecked(settings.getBoolean("darkColors", false));
        mDrearyColorsCheckBox.setChecked(settings.getBoolean("drearyColors", false));
        mSaturatedColorsCheckBox.setChecked(settings.getBoolean("saturatedColors", false));
        mMutedColorsCheckBox.setChecked(settings.getBoolean("mutedColors", true));
        mMaterialColorsCheckBox.setChecked(settings.getBoolean("materialColors", true));

        mGreetingEditText.setText(settings.getString("greeting", getString(R.string.launch_city)));


        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        mLightColorsDemoTextView.setTypeface(font);
        mDarkColorsDemoTextView.setTypeface(font);
        mDrearyColorsDemoTextView.setTypeface(font);
        mSaturatedColorsDemoTextView.setTypeface(font);
        mMutedColorsDemoTextView.setTypeface(font);
        mMaterialColorsDemoTextView.setTypeface(font);


        if (!settings.getBoolean("colors", true)) {
            mLightColorsCheckBox.setEnabled(false);
            mDarkColorsCheckBox.setEnabled(false);
            mDrearyColorsCheckBox.setEnabled(false);
            mSaturatedColorsCheckBox.setEnabled(false);
            mMutedColorsCheckBox.setEnabled(false);
            mMaterialColorsCheckBox.setEnabled(false);

            mLightColorsDemoTextView.setEnabled(false);
            mDarkColorsDemoTextView.setEnabled(false);
            mDrearyColorsDemoTextView.setEnabled(false);
            mSaturatedColorsDemoTextView.setEnabled(false);
            mMutedColorsDemoTextView.setEnabled(false);
            mMaterialColorsDemoTextView.setEnabled(false);
        }


        mCityEditText0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText0.getText().toString().equals("")) {
                    settingsEditor.remove("city0").apply();
                } else {
                    settingsEditor.putString("city0", mCityEditText0.getText().toString()).apply();
                }
            }


            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
        mCityEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText1.getText().toString().equals("")) {
                    settingsEditor.remove("city1").apply();
                } else {
                    settingsEditor.putString("city1", mCityEditText1.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
        mCityEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText2.getText().toString().equals("")) {
                    settingsEditor.remove("city2").apply();
                } else {
                    settingsEditor.putString("city2", mCityEditText2.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
        mCityEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText3.getText().toString().equals("")) {
                    settingsEditor.remove("city3").apply();
                } else {
                    settingsEditor.putString("city3", mCityEditText3.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
        mCityEditText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText4.getText().toString().equals("")) {
                    settingsEditor.remove("city4").apply();
                } else {
                    settingsEditor.putString("city4", mCityEditText4.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
        mCityEditText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mCityEditText5.getText().toString().equals("")) {
                    settingsEditor.remove("city5").apply();
                } else {
                    settingsEditor.putString("city5", mCityEditText5.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });

        mColorsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("colors", mColorsSwitch.isChecked()).apply();
                if (mColorsSwitch.isChecked()) {
                    mLightColorsCheckBox.setEnabled(true);
                    mDarkColorsCheckBox.setEnabled(true);
                    mDrearyColorsCheckBox.setEnabled(true);
                    mSaturatedColorsCheckBox.setEnabled(true);
                    mMutedColorsCheckBox.setEnabled(true);
                    mMaterialColorsCheckBox.setEnabled(true);

                    mLightColorsDemoTextView.setEnabled(true);
                    mDarkColorsDemoTextView.setEnabled(true);
                    mDrearyColorsDemoTextView.setEnabled(true);
                    mSaturatedColorsDemoTextView.setEnabled(true);
                    mMutedColorsDemoTextView.setEnabled(true);
                    mMaterialColorsDemoTextView.setEnabled(true);
                } else {
                    mLightColorsCheckBox.setEnabled(false);
                    mDarkColorsCheckBox.setEnabled(false);
                    mDrearyColorsCheckBox.setEnabled(false);
                    mSaturatedColorsCheckBox.setEnabled(false);
                    mMutedColorsCheckBox.setEnabled(false);
                    mMaterialColorsCheckBox.setEnabled(false);

                    mLightColorsDemoTextView.setEnabled(false);
                    mDarkColorsDemoTextView.setEnabled(false);
                    mDrearyColorsDemoTextView.setEnabled(false);
                    mSaturatedColorsDemoTextView.setEnabled(false);
                    mMutedColorsDemoTextView.setEnabled(false);
                    mMaterialColorsDemoTextView.setEnabled(false);
                }
            }
        });
        mLightColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("lightColors", mLightColorsCheckBox.isChecked()).apply();
            }
        });
        mDarkColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("darkColors", mDarkColorsCheckBox.isChecked()).apply();
            }
        });
        mDrearyColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("drearyColors", mDrearyColorsCheckBox.isChecked()).apply();
            }
        });
        mSaturatedColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("saturatedColors", mSaturatedColorsCheckBox.isChecked()).apply();
            }
        });
        mMutedColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("mutedColors", mMutedColorsCheckBox.isChecked()).apply();
            }
        });
        mMaterialColorsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton mCompoundButton, boolean mB) {
                settingsEditor.putBoolean("materialColors", mMaterialColorsCheckBox.isChecked()).apply();
            }
        });

        mLightColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_light));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_light));
                }
                mDemoTextView.setText(getString(R.string.lightColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });
        mDarkColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_dark));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_dark));
                }
                mDemoTextView.setText(getString(R.string.darkColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });
                mOkButton.setTextColor(Color.WHITE);

                d.show();
            }
        });
        mDrearyColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_dreary));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_dreary));
                }
                mDemoTextView.setText(getString(R.string.drearyColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });
        mSaturatedColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_saturated));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_saturated));
                }
                mDemoTextView.setText(getString(R.string.saturatedColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });
        mMutedColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_muted));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_muted));
                }
                mDemoTextView.setText(getString(R.string.mutedColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });
        mMaterialColorsDemoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_palette);
                LinearLayout mBackground = d.findViewById(R.id.colorDemoBackground);
                TextView mDemoTextView = d.findViewById(R.id.colorDemoTextView);
                Button mOkButton = d.findViewById(R.id.colorDemoButton);

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mBackground.setBackground(getDrawable(R.drawable.palette_material));
                } else {
                    mBackground.setBackground(getResources().getDrawable(R.drawable.palette_material));
                }
                mDemoTextView.setText(getString(R.string.materialColors));
                mOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });

        mGreetingEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
                if (mGreetingEditText.getText().toString().equals("")) {
                    settingsEditor.remove("greeting").apply();
                } else {
                    settingsEditor.putString("greeting", mGreetingEditText.getText().toString()).apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {
            }

            @Override
            public void afterTextChanged(Editable mEditable) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);

        MenuItem item = menu.getItem(menu.size() - 1);
        SpannableString spanString = new SpannableString(menu.getItem(menu.size() - 1).getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, spanString.length(), 0); //fix the color to white
        item.setTitle(spanString);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            final SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), 0);
            final SharedPreferences.Editor settingsEditor = settings.edit();

            AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
            dialog.setTitle(getString(R.string.reset))
                    .setMessage(R.string.reset_confirmation)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mDialogInterface, int mI) {
                            settingsEditor.clear().apply();
                            SettingsActivity.this.finish();
                            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mDialogInterface, int mI) {
                        }
                    })
                    .setNeutralButton(getString(R.string.maybe), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mDialogInterface, int mI) {
                            Random r = new Random();
                            if (r.nextInt(10) < 4) {
                                //36% Chance of resetting
                                settingsEditor.clear().apply();

                                SettingsActivity.this.finish();
                                startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
                            }
                        }
                    })
                    .show();


        } else if (id == R.id.action_about) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
            dialog.setTitle(getString(R.string.about))
                    .setMessage(R.string.about_message)
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mDialogInterface, int mI) {
                        }
                    })
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}
