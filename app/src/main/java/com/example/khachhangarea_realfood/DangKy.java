package com.example.khachhangarea_realfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.example.khachhangarea_realfood.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DangKy extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPhone, edtAddress, edtPassword, edtConfirmPassword, edtBirthDay;
    private TextView tvDangNhap;
    private Button btnDangKy;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    KAlertDialog kAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        setControl();
        setEvent();
    }

    private void setEvent() {
        edtBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + month + "/" + year;
                        edtBirthDay.setText(date);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DangKy.this, dateSetListener, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDangKy();
            }
        });
    }

    private void showDatePickerDiaLog() {

    }

    private void onClickDangKy() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String birtDay = edtBirthDay.getText().toString().trim();
        progressDialog = new ProgressDialog(this);

        if (name.isEmpty()) {
            edtName.setError("Vui l??ng nh???p h??? t??n");
        } else if (email.isEmpty()) {
            edtEmail.setError("Vui l??ng nh???p email");
        } else if (!email.matches(emailPattern)) {
            edtEmail.setError("Nh???p sai ?????nh d???ng email");
        } else if (phone.isEmpty()) {
            edtPhone.setError("Vui l??ng nh???p s??? ??i???n tho???i");
        } else if (address.isEmpty()) {
            edtAddress.setError("Vui l??ng nh???p ?????a ch???");
        } else if (birtDay.isEmpty()) {
            edtBirthDay.setError("Vui l??ng nh???p ng??y sinh");
        } else if (password.isEmpty()) {
            edtPassword.setError("Vui l??ng nh???p password");
        } else if (password.length() < 6) {
            edtPassword.setError("????? d??i password t??? 6 ?????n 100");
        } else if (confirmPassword.isEmpty()) {
            edtConfirmPassword.setError("Vui l??ng nh???p password");
        } else if (!password.equals(confirmPassword)) {
            edtConfirmPassword.setError("M???t kh???u kh??ng tr??ng nhau");
        } else {
//            progressDialog = new ProgressDialog(this);
//            progressDialog.setMessage("Vui l??ng ?????i trong khi ????ng k?? ...");
//            progressDialog.setTitle("????ng k??");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
            kAlertDialog = new KAlertDialog(DangKy.this, KAlertDialog.PROGRESS_TYPE)
                    .setTitleText("????ng k??").setContentText("Vui l??ng ?????i trong khi ????ng k?? ...");
            kAlertDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                KhachHang khachHang = new KhachHang(mAuth.getUid(), name, address, phone, "", email, "", null, birtDay);
                                mDatabase.child("KhachHang").child(mAuth.getUid()).setValue(khachHang);
                                kAlertDialog.dismiss();
                                Intent intent = new Intent(DangKy.this, Home.class);
                                startActivity(intent);
                                finishAffinity();

                            } else {
                                // If sign in fails, display a message to the user.
                                kAlertDialog.changeAlertType(KAlertDialog.ERROR_TYPE);
                                kAlertDialog.setTitleText("????ng k??");
                                kAlertDialog.setContentText("Email ???? t???n t???i");
                            }
                        }
                    });

        }
    }

    private void setControl() {
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        tvDangNhap = findViewById(R.id.tvDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtBirthDay = findViewById(R.id.edtBirthDay);
    }
}