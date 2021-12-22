package com.app.codesamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.app.codesamples.network.presentation.NetworkRequestFragment
import com.app.codesamples.permissions.PermissionFragment
import com.app.codesamples.recyclerview.RecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var permissionsBtn: Button
    lateinit var recyclerBtn: Button
    lateinit var networkCallBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionsBtn = findViewById(R.id.permissions_btn)
        permissionsBtn.setOnClickListener(this)

        recyclerBtn = findViewById(R.id.recycler_btn)
        recyclerBtn.setOnClickListener(this)

        networkCallBtn = findViewById(R.id.network_calls_btn)
        networkCallBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.permissions_btn -> {
                val permissionFragment: PermissionFragment = PermissionFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .addToBackStack(PermissionFragment.TAG)
                    .add(R.id.main, permissionFragment)
                    .commit()
            }
            R.id.recycler_btn -> {
                val recyclerFragment: RecyclerViewFragment = RecyclerViewFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .addToBackStack(RecyclerViewFragment.TAG)
                    .add(R.id.main, recyclerFragment)
                    .commit()
            }
            R.id.network_calls_btn -> {
                val networkFragment: NetworkRequestFragment = NetworkRequestFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .addToBackStack(NetworkRequestFragment.TAG)
                    .add(R.id.main, networkFragment)
                    .commit()
            }
        }
    }
}
