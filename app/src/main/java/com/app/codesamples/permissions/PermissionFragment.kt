package com.app.codesamples.permissions

import android.app.ActivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.codesamples.R
import com.google.android.material.snackbar.Snackbar

class PermissionFragment : Fragment() {

    companion object {
        val TAG: String = PermissionFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            PermissionFragment()
    }
    lateinit var single: Button
    lateinit var group: Button
    lateinit var all: Button
    lateinit var clear: Button

    private val permissionManager = PermissionManager.from(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        single = view.findViewById(R.id.single)
        group = view.findViewById(R.id.group)
        all = view.findViewById(R.id.all)
        clear = view.findViewById(R.id.clear)
        single.setOnClickListener {
            permissionManager
                // Check one permission at a time
                .request(Permission.Camera)
                .rationale("Grant single permission")
                .checkPermission { granted ->
                    if (granted) {
                        success("Permission Granted")
                    } else {
                        error("Permission Denied")
                    }
                }
        }

        group.setOnClickListener {
            permissionManager
                // Check a few bundled permissions under one: Storage = Read + Write
                .request(Permission.MandatoryForFeatureOne)
                .rationale("Grant multiple permissions")
                .checkPermission { granted ->
                    if (granted) {
                        success("Permissions Granted")
                    } else {
                        error("Permission Denied")
                    }
                }
        }

        all.setOnClickListener {
            permissionManager
                // Check all permissions without bundling them
                .request(Permission.Storage, Permission.Location, Permission.Camera)
                .rationale("Grant all permissions")
                .checkDetailedPermission { result ->
                    if (result.all { it.value }) {
                        success("Permissions Granted")
                    } else {
                        showErrorDialog(result)
                    }
                }
        }

        clear.setOnClickListener {
            val manager = requireContext().getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
            manager.clearApplicationUserData()
        }
    }

    private fun showErrorDialog(result: Map<Permission, Boolean>) {
        val message = result.entries.fold("") { message, entry ->
            message + "${getErrorMessageFor(entry.key)}: ${entry.value}\n"
        }
        Log.i("TAG", message)
        AlertDialog.Builder(requireContext())
            .setTitle("Missing permissions")
            .setMessage(message)
            .show()
    }

    private fun getErrorMessageFor(permission: Permission) = when (permission) {
        Permission.Camera -> "Camera permission: "
        Permission.Location -> "Location permission"
        Permission.Storage -> "Storage permission"
        else -> "Unknown permission"
    }

    private fun success(message: String) {
        view?.rootView?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun error(message: String) {
        view?.rootView?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

}