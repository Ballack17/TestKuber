package kubertest.master.service;

import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.data.dto.FullEssenceDto;
import kubertest.master.data.entity.Essence;
import kubertest.master.data.mapper.EssenceMapper;
import kubertest.master.data.repository.EssenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EssenceService {

    private final EssenceRepository essenceRepository;
    private final EssenceMapper essenceMapper;

    @Transactional
    public void createSomeEntity (FullEssenceDto fullEssenceDto) {
        if (essenceRepository.existsByEmail(fullEssenceDto.getEmail())) {throw new IllegalArgumentException("Email already used");}
        else if(essenceRepository.existsById(fullEssenceDto.getId())) {throw new IllegalArgumentException("Id already used");}
        else {
        Essence essenceNew = essenceMapper.toEssence(fullEssenceDto);
        essenceRepository.save(essenceNew);
        }
    }

    @Transactional
    public UUID createSomeEntityWithoutId (EssenceEmailOffDto essenceEmailOffDto) {
        Essence essenceNew = essenceMapper.fromEssenceEmailOffDto(essenceEmailOffDto);
        essenceNew.setId(UUID.randomUUID());
        essenceRepository.save(essenceNew);
        return essenceNew.getId();
    }

    public EssenceEmailOffDto findByIdEmailOffDto (UUID id) {
        return essenceMapper.toEssenceEmailOffDto
                (essenceRepository.findById(id).orElseThrow
                        (() -> new NoSuchElementException("There are no Data with such UUID")));
    }

    public boolean isValidFullEssence (FullEssenceDto fullEssenceDto) {
        return (fullEssenceDto.getId() != null &&
                fullEssenceDto.getEmail() != null &&
                fullEssenceDto.getSomeDate() != null &&
                fullEssenceDto.getSomeTime() != null &&
                fullEssenceDto.getTitle() != null);
    }
}
