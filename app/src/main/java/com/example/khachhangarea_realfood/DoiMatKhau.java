package com.example.khachhangarea_realfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoiMatKhau extends AppCompatActivity {
    private EditText edtPassword, edtNewPassword, edtConfirmNewPassword;
    private Button btnLuu;
    private FirebaseUser mUser;
    private ProgressDialog progressDialog;
    private AuthCredential credential;
    private KAlertDialog kAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePassword();
            }
        });
    }

    private void setControl() {
        edtPassword = findViewById(R.id.edtPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = findViewById(R.id.edtConfirmNewPassword);
        btnLuu = findViewById(R.id.btnLuu);
    }

    private void onClickChangePassword() {
        progressDialog = new ProgressDialog(this);
        String password = edtPassword.getText().toString().trim();
        String newPassword = edtNewPassword.getText().toString().trim();
        String confirmNewPassword = edtConfirmNewPassword.getText().toString().trim();
        final String email = mUser.getEmail().toString().trim();

        if (password.isEmpty()) {
            edtPassword.setError("Vui l??ng nh???p m???t kh???u");
        } else if (newPassword.isEmpty()) {
            edtNewPassword.setError("Vui l??ng nh???p m???t kh???u m???i");
        } else if (newPassword.length() < 6) {
            edtNewPassword.setError("????? d??i m???t kh???u t??? 6 ?????n 100");
        } else if (confirmNewPassword.isEmpty()) {
            edtConfirmNewPassword.setError("Vui l??ng nh???p l???i m???t kh???u m???i ");
        } else if (!newPassword.equals(confirmNewPassword)) {
            edtConfirmNewPassword.setError("M???t kh???u kh??ng tr??ng nhau");
        } else {
            credential = EmailAuthProvider.getCredential(email, password);
            mUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        if (newPassword.isEmpty()) {
                            edtNewPassword.setError("Vui l??ng nh???p m???t kh???u m???i");
                        } else if (newPassword.length() < 6) {
                            edtNewPassword.setError("????? d??i m???t kh???u t??? 6 ?????n 100");
                        } else if (confirmNewPassword.isEmpty()) {
                            edtConfirmNewPassword.setError("Vui l??ng nh???p l???i m???t kh???u m???i ");
                        } else if (!newPassword.equals(confirmNewPassword)) {
                            edtConfirmNewPassword.setError("M???t kh???u kh??ng tr??n nhau");
                        } else {
                            progressDialog.setMessage("Vui l??ng ?????i trong khi ?????i m???t kh???u ...");
                            progressDialog.setTitle("?????i m???t kh???u");
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                            kAlertDialog = new KAlertDialog(DoiMatKhau.this, KAlertDialog.PROGRESS_TYPE)
                                    .setTitleText("?????i m???t kh???u")
                                    .setContentText("Vui l??ng ?????i trong khi ?????i m???t kh???u ...");
                            kAlertDialog.show();
                            mUser.updatePassword(newPassword)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                kAlertDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE);
                                                kAlertDialog.setContentText("?????i th??nh c??ng");
                                                Intent intent = new Intent(DoiMatKhau.this, Home.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    } else {
                        kAlertDialog = new KAlertDialog(DoiMatKhau.this,KAlertDialog.ERROR_TYPE);
                        kAlertDialog.setContentText("M???t kh???u hi???n t???i sai");
                        kAlertDialog.setConfirmText("OK");
                        kAlertDialog.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                kAlertDialog.dismiss();
                            }
                        });
                        kAlertDialog.show();
                    }
                }
            });
        }


    }
}