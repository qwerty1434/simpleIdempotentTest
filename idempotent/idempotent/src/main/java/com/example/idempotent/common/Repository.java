package com.example.idempotent.common;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Domain,Integer> {
}
