package com.example.nguyenducchinh_contentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TinNhanAdapter extends ArrayAdapter<TinNhan> {
    public TinNhanAdapter(@NonNull Context context, List<TinNhan> tinNhans) {
        super(context, 0, tinNhans);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tin_nhan, parent, false);
        }

        TinNhan tinNhan = getItem(position);

        TextView nguoiGui = convertView.findViewById(R.id.nguoiGui);
        TextView noiDung = convertView.findViewById(R.id.noiDung);
        TextView thoiGian = convertView.findViewById(R.id.thoiGian);

        nguoiGui.setText(tinNhan.getTenNguoiGui());
        noiDung.setText(tinNhan.getNoiDung());
        thoiGian.setText(tinNhan.getThoiGian());

        return convertView;
    }
}
