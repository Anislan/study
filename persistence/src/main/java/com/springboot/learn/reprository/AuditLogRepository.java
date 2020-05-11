package com.springboot.learn.reprository;

import com.springboot.learn.domain.AuditLog;
import com.springboot.learn.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AuditLogRepository extends JpaSpecificationExecutor<AuditLog>,CrudRepository<AuditLog,Long>{

}
