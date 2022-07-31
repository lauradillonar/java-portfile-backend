package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Skill;
import com.confluenciacreativa.portfile.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAll(){
        return skillRepository.getAll();
    }

    public Optional<List<Skill>> getByPerson(Integer idPerson){
        return skillRepository.getByPerson(idPerson);
    }

    public Boolean existsById(Integer idSkill){
        return skillRepository.exitsById(idSkill);
    }

    public Boolean existsByItem(String item){
        return skillRepository.existsByItem(item);
    }

    public Optional<Skill> getSkill(Integer idSkill){
        return skillRepository.getSkill(idSkill);
    }

    public Optional<Skill> findByItem(String item){
        return skillRepository.findByItem(item);
    }

    public void save(Skill skill){
        skillRepository.save(skill);
    }

    public Boolean delete(Integer idSkill){
        return getSkill(idSkill).map(
                skill -> {
                    skillRepository.delete(idSkill);
                    return true;
                }).orElse(false);
    }
}
