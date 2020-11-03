package com.springdemo.admindivisionimport;

import com.springdemo.jpademo.entity.Person;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


public interface AdministratorDivisionRepository extends JpaRepository<AdministratorDivisionBO, String> {



}
