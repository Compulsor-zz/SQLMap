package be.ap.edu.sqlmap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;

public class SaveMarkerActivity extends AppCompatActivity {

    private static final String LOCATION = "LOCATION";
    private static final String BESCHRIJVING = "BESCHRIJVING";

    private TextView mTextView;
    private EditText beschrijving;
    private Button saveButton;
    private MySQLiteHelper helper;

    final String TABLE_NAME = "locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context currentContext = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_marker);

        mTextView = (TextView) findViewById(R.id.textViewDescription);
        beschrijving = (EditText) findViewById(R.id.editTextDiscription);
        saveButton = (Button) findViewById(R.id.buttonAddMarker);

        helper = new MySQLiteHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{

                    final GeoPoint location = (GeoPoint) getIntent().getSerializableExtra("LOCATION");
                    Double lat = location.getLatitude();
                    Double lon = location.getLongitude();
                    String mBeschrijving = beschrijving.getText().toString();
                    helper.addLocation(lat, lon, mBeschrijving);
                    Toast.makeText(getApplicationContext(),
                            "De locatie werd correct opgeslagen!",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                catch(Exception ex){
                    Log.e("=INCORRECT opgeslagen", ex.getMessage());
                }

                Intent mainScreenIntent = new Intent(currentContext, MainActivity.class);
                mainScreenIntent.putExtra(LOCATION, (Serializable) (GeoPoint) getIntent().getSerializableExtra("LOCATION"));
                mainScreenIntent.putExtra(BESCHRIJVING, beschrijving.getText().toString());
                startActivity(mainScreenIntent);
            }
        });
    }
}
