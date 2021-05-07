package com.cts.training.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.bean.Skills;
import com.cts.training.exception.SkillsNotFoundException;
import com.cts.training.repository.SkillsRepository;

@RestController
@RequestMapping("/api")
public class SkillsResource {
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@GetMapping("/skills")
	public List<Skills> getAllUsers(){
		return skillsRepository.findAll();
	}
	
	@GetMapping("/skills/{id}")
	public ResponseEntity<Optional<Skills>> getSkillsById(@PathVariable Long id) {
		Optional<Skills> skills = skillsRepository.findById(id);
		if(!skills.isPresent())
			throw new SkillsNotFoundException("The user with id -" +id+ "Not found");
		return new ResponseEntity<Optional<Skills>>(skills, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/skills/{id}")
	public void deleteSkills(@PathVariable Long id) {
		skillsRepository.deleteById(id);
	}
	
	@PostMapping("/skills")
	public Skills createSkills(@Valid @RequestBody Skills skills) {
		Skills savedSkills = skillsRepository.save(skills);
		return savedSkills;
	}
	
	@PutMapping("/skills/{id}")
	public ResponseEntity<Object> updateSkills(@Valid @RequestBody Skills skills, @PathVariable Long id){
		skillsRepository.save(skills);
		return ResponseEntity.noContent().build();
		
		
	}
}
