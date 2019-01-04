package id.devloved.jogjaloker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingActivity extends AppCompatActivity {

    Toolbar settingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settingToolbar = (Toolbar) findViewById(R.id.settingToolbar);
        setSupportActionBar(settingToolbar);
        getSupportActionBar().setTitle("Setting Akun");
    }
}
