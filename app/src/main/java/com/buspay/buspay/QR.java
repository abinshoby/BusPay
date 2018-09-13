package com.buspay.buspay;

import android.Manifest;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class QR extends AppCompatActivity {
    private static final String LOG_TAG = "Barcode Scanner API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private BarcodeDetector detector;
    private Uri imageUri;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
    }
    Button button = (Button) findViewById(R.id.button);
    TextView scanResults = (TextView) findViewById(R.id.scan_results);
        if (savedInstanceState != null) {
        imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
        scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
    }
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityCompat.requestPermissions(MainActivity.this, new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    });

    detector = new BarcodeDetector.Builder(getApplicationContext())
            .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                .build();
        if (!detector.isOperational()) {
        scanResults.setText("Could not set up the detector!");
        return;
    }

}
