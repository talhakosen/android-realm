package tkosen.com.android_realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener {
    EditText edt_name,edt_population,edt_code;
    Button btn_save;
    Realm realm;
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        eventBus = EventBus.getDefault();

        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_code = (EditText)findViewById(R.id.edt_code);
        edt_population = (EditText)findViewById(R.id.edt_population);
        btn_save = (Button) findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();

                Country country = realm.createObject(Country.class);
                country.setName(edt_name.getText().toString());
                country.setCode(edt_code.getText().toString());
                country.setPopulation(Integer.valueOf(edt_population.getText().toString()));

                realm.commitTransaction();

                RealmResults<Country> countries = realm.allObjects(Country.class);
                eventBus.post(countries);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Country item) {
        Toast.makeText(this,item.getName(),Toast.LENGTH_SHORT).show();
    }
}
