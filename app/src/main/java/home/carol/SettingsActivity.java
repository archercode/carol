package home.carol;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedpreferences = getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);

        final String deviceId = sharedpreferences.getString("device_id", "310021001447343338333633");
        final String accessToken = sharedpreferences.getString("access_token", "ed85ceeff03057276a0d00fcd3d32a8fe367a648");
        final String number = sharedpreferences.getString("cell_number", "09758005563");

        final EditText etDevice = (EditText) findViewById(R.id.et_device_id);
        final EditText etAccess = (EditText) findViewById(R.id.et_access);
        final EditText etNumber = (EditText) findViewById(R.id.cell_number);


        etDevice.setText(deviceId);
        etAccess.setText(accessToken);
        etNumber.setText(number);


        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("device_id", etDevice.getText().toString());
                editor.putString("access_token", etAccess.getText().toString());
                editor.putString("cell_number", etNumber.getText().toString());
                editor.commit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
