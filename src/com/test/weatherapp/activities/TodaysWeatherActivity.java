package com.test.weatherapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.test.weatherapp.R;
import com.test.weatherapp.adapters.ImageDownloader;
import com.test.weatherapp.callbacks.WeatherAppProcessorCallback;
import com.test.weatherapp.database.WeatherAppDBContract;
import com.test.weatherapp.service.WeatherAppServiceHelper;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Activity class for today weather
 *
 */
public class TodaysWeatherActivity extends BaseActivity implements WeatherAppProcessorCallback, View.OnClickListener, TextWatcher {

    private static final String LOG_TAG = "CurrentWeatherActivity";

    //UI ELEMENTS
    private EditText editText_search;
    private Button button_search;
    private Button button_weeks_weather;
    private ProgressDialog progress_spinner;
    private String location;

    private final ImageDownloader imageDownloader = new ImageDownloader();

    @Override
    public String getTag() {
        return LOG_TAG;
    }

    @Override
    public WeatherAppProcessorCallback getServiceCallback() {
        return this;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_weather);

        button_search = (Button)findViewById(R.id.button_search);
        button_search.setOnClickListener(this);

        button_weeks_weather = (Button)findViewById(R.id.button_weeks_weather);
        button_weeks_weather.setOnClickListener(this);

        editText_search = (EditText) findViewById(R.id.edit_text_location);
        editText_search.addTextChangedListener(this);

        imageDownloader.setMode(ImageDownloader.Mode.CORRECT);
    }

    @Override
    public void onError(String msg) {
        progress_spinner.dismiss();
        button_weeks_weather.setEnabled(false);
        button_search.setEnabled(false);
    }

    @Override
    public void onComplete() {

        Cursor cursor = getContentResolver().query(WeatherAppDBContract.Weather.CONTENT_URI_TODAYS_WEATHER, projection, null, null, null);
        if(cursor.moveToFirst()){

            String temperature = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE));
            String date = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_DATE));
            String desc = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION));
            String precip = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION));
            String icon_url = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_ICON_URL));

            TextView temp_tv = (TextView) findViewById(R.id.temperature_val);
            temp_tv.setText(temperature);
            TextView date_tv = (TextView) findViewById(R.id.date_val);
            date_tv.setText(date);
            TextView desc_tv = (TextView) findViewById(R.id.desc_val);
            desc_tv.setText(desc);
            TextView precip_tv = (TextView) findViewById(R.id.precip_val);
            precip_tv.setText(precip);

            ImageView icon_view = (ImageView) findViewById(R.id.weather_icon);
            imageDownloader.download(icon_url, (ImageView) icon_view);

        }
        cursor.close();
        progress_spinner.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button_search :

                //PREVENT REPEATED CLICKS
                button_search.setEnabled(false);

                //Start service to get weather for today
                button_weeks_weather.setEnabled(true);
                location = editText_search.getText().toString();
                progress_spinner = ProgressDialog.show(this, "Please wait", "Talking to The Weather God..", true);
                WeatherAppServiceHelper.getInstance(this).getTodaysWeather(location);
                break;

            case R.id.button_weeks_weather :

                Intent intent = new Intent(this, WeeksWeatherActivity.class);
                intent.putExtra(WeatherAppConstants.EXTRAS_LOCATION, location);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
       button_search.setEnabled(true);
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
