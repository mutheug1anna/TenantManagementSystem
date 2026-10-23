package com.example.tenantmanagementsystem

object TenantRepository {
    private val tenants = mutableListOf<Tenant>()

    fun getTenants(): List<Tenant> = tenants.toList()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun getTenantCount(): Int = tenants.size

    fun getTotalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }
}
