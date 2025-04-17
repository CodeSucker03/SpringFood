package com.example.accessing_data_mysql.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find an address by user ID or any other criteria
}
