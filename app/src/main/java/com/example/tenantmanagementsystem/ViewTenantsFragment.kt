package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newTenant = args.newTenant
        if (newTenant != null) {
            binding.newTenantGroup.visibility = View.VISIBLE
            binding.newTenantTextView.text = newTenant.summary()
            binding.callButton.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                startActivity(dialIntent)
            }
        } else {
            binding.newTenantGroup.visibility = View.GONE
        }

        displayAllTenants()
    }

    private fun displayAllTenants() {
        val tenants = TenantRepository.getTenants()
        if (tenants.isEmpty()) {
            binding.tenantsListTextView.text = "No tenants added yet."
        } else {
            binding.tenantsListTextView.text = tenants.joinToString("\n\n-------------------\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
