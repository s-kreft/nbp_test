package com.example.nbp_test.Repository;

import com.example.nbp_test.model.LogRecord;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface nbpRepository extends JpaRepository<LogRecord, Long> {
}
