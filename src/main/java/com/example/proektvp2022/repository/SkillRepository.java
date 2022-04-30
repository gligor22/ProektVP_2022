package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
//    List<Skill> findAllById(List<Long> id);
}
