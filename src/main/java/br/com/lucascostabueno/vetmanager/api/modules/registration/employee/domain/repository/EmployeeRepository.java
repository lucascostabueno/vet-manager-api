package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID>, JpaSpecificationExecutor<Employee>, EmployeeRepositoryCustom {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
