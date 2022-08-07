package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Skill;
import com.confluenciacreativa.portfile.entity.SkillDB;
import com.confluenciacreativa.portfile.mapper.SkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillRepositoryImpl implements SkillRepository{

    @Autowired
    private SkillCrudRepository skillCrudRepository;

    private SkillMapper mapper;

    @Override
    public List<Skill> getAll() {
        List<SkillDB> skillsDB = (List<SkillDB>) skillCrudRepository.findAll();
        return mapper.toSkills(skillsDB);
    }

    @Override
    public Optional<List<Skill>> getByPerson(Integer idPerson) {
        List<SkillDB> skillsDB = skillCrudRepository.findByIdPersonDB(idPerson);
        return  Optional.of(mapper.toSkills(skillsDB));
    }

    @Override
    public Boolean exitsById(Integer idSkill) {
        return skillCrudRepository.existsById(idSkill);
    }

    @Override
    public Boolean existsByItem(String item) {
        return skillCrudRepository.existsByItemDB(item);
    }

    @Override
    public Optional<Skill> getSkill(Integer idSkill) {
        return skillCrudRepository.findById(idSkill).map(skillDB -> mapper.toSkill(skillDB));
    }

    @Override
    public Optional<Skill> findByItem(String item) {
        return skillCrudRepository.findByItemDB(item).map(skillDB -> mapper.toSkill(skillDB));
    }

    @Override
    public void save(Skill skill) {
        SkillDB skillDB = mapper.toSkillDB(skill);
        skillCrudRepository.save(skillDB);
    }

    @Override
    public void delete(Integer idSkill) {
        skillCrudRepository.deleteById(idSkill);
    }

    @Override
    public Boolean existsByItemAndIdPerson(String item, Integer idPerson) {
        return skillCrudRepository.existsByItemDBAndIdPersonDB(item, idPerson);
    }
}
