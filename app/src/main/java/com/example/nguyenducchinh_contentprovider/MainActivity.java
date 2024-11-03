package com.example.nguyenducchinh_contentprovider;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 101;
    private ListView listView;
    private TinNhanAdapter adapter;
    private List<TinNhan> tinNhans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
        } else {
            LoadTinNhan();
        }

    }

    private void LoadTinNhan() {
        tinNhans = LayTinNhan();
        adapter = new TinNhanAdapter(this, tinNhans);
        listView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LoadTinNhan();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<TinNhan> LayTinNhan() {
        List<TinNhan> tinNhanList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String nguoiGui = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("body"));
                @SuppressLint("Range") String thoiGian = cursor.getString(cursor.getColumnIndex("date"));

                long timestamp =  Long.parseLong(thoiGian);
                String formattedTime = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault()).format(new java.util.Date(timestamp));

                tinNhanList.add(new TinNhan(nguoiGui, noiDung, formattedTime));
            }
            cursor.close();
        }
        return tinNhanList;
    }

}