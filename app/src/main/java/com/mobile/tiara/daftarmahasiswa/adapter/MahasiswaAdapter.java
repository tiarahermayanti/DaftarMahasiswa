package com.mobile.tiara.daftarmahasiswa.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.tiara.daftarmahasiswa.R;
import com.mobile.tiara.daftarmahasiswa.UpdateHapusActivity;
import com.mobile.tiara.daftarmahasiswa.model.DataMahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    List<DataMahasiswa> data;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtnim, txtnama, txtjurusan, txtjekel, txtemail;
        public MyViewHolder(View itemView) {
            super(itemView);

            //todo -- inisialisasi variable dngn id
            txtnama = itemView.findViewById(R.id.txtNama);
            txtnim = itemView.findViewById(R.id.txtNim);
            txtjurusan = itemView.findViewById(R.id.txtJurusan);
            txtjekel = itemView.findViewById(R.id.txtJekel);
            txtemail = itemView.findViewById(R.id.txtEmail);

        }
    }

    public MahasiswaAdapter(List <DataMahasiswa> dataMhs) {
        data = dataMhs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO -- menghubungkan dengan layout

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mahasiswa,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MahasiswaAdapter.MyViewHolder holder, final int position) {
        //todo -- yang akan ditampilkan di MainActivity

        holder.txtnim.setText(data.get(position).getMahasiswaNim());
        holder.txtnama.setText(data.get(position).getMahasiswaNama());
        holder.txtjurusan.setText(data.get(position).getMahasiswaJurusan());
        holder.txtjekel.setText(data.get(position).getMahasiswaJekel());
        holder.txtemail.setText(data.get(position).getMahasiswaEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kirimData = new Intent(holder.itemView.getContext(), UpdateHapusActivity.class);
                kirimData.putExtra("id",data.get(position).getMahasiswaId());
                kirimData.putExtra("nim",data.get(position).getMahasiswaNim());
                kirimData.putExtra("nama",data.get(position).getMahasiswaNama());
                kirimData.putExtra("jurusan",data.get(position).getMahasiswaJurusan());
                kirimData.putExtra("jekel",data.get(position).getMahasiswaJekel());
                kirimData.putExtra("email",data.get(position).getMahasiswaEmail());
                holder.itemView.getContext().startActivity(kirimData);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}