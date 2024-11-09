package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.profession.ProfessionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.profession.ProfessionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.ProfessionMapper;
import com.codeforworks.NTH_WorkFinder.model.Industry;
import com.codeforworks.NTH_WorkFinder.model.Profession;
import com.codeforworks.NTH_WorkFinder.repository.IndustryRepository;
import com.codeforworks.NTH_WorkFinder.repository.ProfessionRepository;
import com.codeforworks.NTH_WorkFinder.service.IProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionService implements IProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public ProfessionResponseDTO createProfession(ProfessionRequestDTO professionRequestDTO) {
        Industry industry = industryRepository.findById(professionRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        Profession profession = ProfessionMapper.INSTANCE.toProfessionEntity(professionRequestDTO);
        profession.setIndustry(industry);

        Profession savedProfession = professionRepository.save(profession);
        return ProfessionMapper.INSTANCE.toProfessionResponseDTO(savedProfession);
    }

    @Override
    public ProfessionResponseDTO getProfessionById(Long id) {
        Profession profession = professionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profession not found"));
        return ProfessionMapper.INSTANCE.toProfessionResponseDTO(profession);
    }

    @Override
    public List<ProfessionResponseDTO> getAllProfessions() {
        List<Profession> professions = professionRepository.findAll();
        return professions.stream()
                .map(ProfessionMapper.INSTANCE::toProfessionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionResponseDTO updateProfession(Long id, ProfessionRequestDTO professionRequestDTO) {
        Profession profession = professionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profession not found"));

        Industry industry = industryRepository.findById(professionRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        profession.setName(professionRequestDTO.getName());
        profession.setDescription(professionRequestDTO.getDescription());
        profession.setIndustry(industry);

        Profession updatedProfession = professionRepository.save(profession);
        return ProfessionMapper.INSTANCE.toProfessionResponseDTO(updatedProfession);
    }

    @Override
    public void deleteProfession(Long id) {
        if (!professionRepository.existsById(id)) {
            throw new RuntimeException("Profession not found");
        }
        professionRepository.deleteById(id);
    }
}