package com.mobile.tiara.daftarmahasiswa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.tiara.daftarmahasiswa.model.ResponseInsert;
import com.mobile.tiara.daftarmahasiswa.restapi.ConfigRetrofit;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateHapusActivity extends AppCompatActivity {

    @BindView(R.id.edtNim)
    EditText edtNim;
    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.spinnerJurusan)
    Spinner spinnerJurusan;
    @BindView(R.id.rbPria)
    RadioButton rbPria;
    @BindView(R.id.rbWanita)
    RadioButton rbWanita;
    @BindView(R.id.rgJekel)
    RadioGroup rgJekel;
    @BindView(R.id.edtEmail)
    EditText edtEmail;

    String id;
    String selectedJekel, selectedJurusan;


    String[] arrayjurusan = {
            "Teknik Komputer", "Manajemen Informatika", "Teknik Mesin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hapus);
        ButterKnife.bind(this);

        //todo -- terima data intent

        id = getIntent().getStringExtra("id");
        String nim = getIntent().getStringExtra("nim");
        String nama = getIntent().getStringExtra("nama");
        String jurusan = getIntent().getStringExtra("jurusan");
        String jekel = getIntent().getStringExtra("jekel");
        String email = getIntent().getStringExtra("email");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arrayjurusan);
        spinnerJurusan.setAdapter(adapter);

        spinnerJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedJurusan = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //todo - tampilkan data di edit text
        edtNim.setText(nim);
        edtNama.setText(nama);

        rbWanita.setChecked(true);
        edtEmail.setText(email);

    }

    @OnClick(R.id.btnUpdate)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnUpdate:
                updateData();
                break;
        }
    }

    private void updateData() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Konfirmasi");
        alert.setMessage("Anda yakin ingin merubah data ini?");
        alert.setCancelable(false);
        alert.setPositiveButton("ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(rbPria.isChecked()){
                    selectedJekel = rbPria.getText().toString();
                } else if(rbWanita.isChecked()){
                    selectedJekel = rbWanita.getText().toString();
                }




                String upnim = edtNim.getText().toString();
                String upnama = edtNama.getText().toString();

                String upemail = edtEmail.getText().toString();

                ConfigRetrofit.service.update(id, upnim, upnama, selectedJurusan, selectedJekel, upemail ).enqueue(new Callback<ResponseInsert>() {
                    @Override
                    public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                        String pesan = response.body().getPesan();
                        int status = response.body().getStatus();

                        if (status == 1) {
                            Toast.makeText(UpdateHapusActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateHapusActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UpdateHapusActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsert> call, Throwable t) {

                    }
                });

            }
        });

        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hapus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idMenu = item.getItemId();
        //todo -- hapus data
        if (idMenu == R.id.btnHapus) {
            hapusData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void hapusData() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Konfirmasi");
        alert.setMessage("Anda yakin ingin menghapus data ini ?");
        alert.setCancelable(false);
        alert.setPositiveButton("ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ConfigRetrofit.service.hapus(id).enqueue(new Callback<ResponseInsert>() {
                    @Override
                    public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                        String pesan = response.body().getPesan();
                        int status = response.body().getStatus();
                        if (status == 1) {
                            Toast.makeText(UpdateHapusActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateHapusActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UpdateHapusActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsert> call, Throwable t) {

                    }
                });
            }
        });

        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }
}
