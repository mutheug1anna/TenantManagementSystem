package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding
import android.util.Log
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            val name = savedInstanceState.getString("tenantName", "")
            val phone = savedInstanceState.getString("tenantPhone", "")
            val rent = savedInstanceState.getString("tenantRent", "")

            val tenant = Tenant(name, phone, rent)

            lastTenant = tenant
            binding.tenant = tenant

            binding.tenantNameEditText.setText(name)
            binding.phoneEditText.setText(phone)
            binding.rentEditText.setText(rent)
        }


        binding.saveButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            val tenant = Tenant(name, phone, rent)

            binding.tenant = tenant

            lastTenant = tenant
        }

        binding.callButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {

                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${tenant.phone}")
            )

            startActivity(intent)
        }
    }

    // Called when the Activity becomes visible.
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    //Called when the Activity comes to the foreground.
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    //Called when the Activity starts leaving the foreground.
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    //Called when the Activity is no longer visible.
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    //Called when the stopped Activity is returning.
    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    //Called when the Activity is being destroyed.
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val tenant = lastTenant

        if (tenant != null) {
            outState.putString("tenantName", tenant.name)
            outState.putString("tenantPhone", tenant.phone)
            outState.putString("tenantRent", tenant.rent)
        }
    }

}