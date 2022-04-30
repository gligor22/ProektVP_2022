package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Skill;
import com.example.proektvp2022.model.exceptions.InvalidSkillIdException;
import com.example.proektvp2022.repository.SkillRepository;
import com.example.proektvp2022.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill findById(Long id) {
        return this.skillRepository.findById(id).orElseThrow(InvalidSkillIdException::new);
    }

    @Override
    public List<Skill> listAll() {
        return this.skillRepository.findAll();
    }

    @Override
    public Skill create(String name) {
        Skill skill = new Skill(name);
        return this.skillRepository.save(skill);
    }
}
