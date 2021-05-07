package com.cts.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cts.training.bean.Skills;

@Component
public interface SkillsRepository extends JpaRepository<Skills, Long> {

	List<Skills> findAll();

}
