package com.androfara.bwamovapp.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androfara.bwamovapp.R
import com.androfara.bwamovapp.sign.signin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mFirebaseInstance:FirebaseDatabase
    lateinit var mDatabase:DatabaseReference
    lateinit var mDatabaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener{
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if(sUsername.equals("")){
                et_username.error="Silahkan Masukkan Username Anda"
                et_username.requestFocus()
            }else if(et_password.equals("")){
                et_password.error="Silahkan Masukkan Password Anda"
                et_password.requestFocus()
            }else if(et_nama.equals("")){
                et_nama.error="Silahkan Masukkan Nama Anda"
                et_nama.requestFocus()
            }else if(et_email.equals("")){
                et_email.error="Silahkan Masukkan Email Anda"
                et_email.requestFocus()
            }else{
                saveData(sUsername, sPassword, sNama, sEmail)
            }
        }
    }

    private fun saveData(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama
        user.email = sEmail

        if (sUsername != null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User){
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity,databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)

                if (user == null){
                    mDatabaseReference.child(sUsername).setValue(data)

                    var intent = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("nama",
                        data.nama
                    )
                    startActivity(intent)

                }else{
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan",
                        Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}