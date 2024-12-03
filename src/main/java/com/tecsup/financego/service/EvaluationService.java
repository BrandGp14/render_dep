package com.tecsup.financego.service;

import com.tecsup.financego.common.type.EvaluationDto;
import com.tecsup.financego.entity.TEvaluationEntity;
import com.tecsup.financego.entity.TModuleEntity;
import com.tecsup.financego.repository.TEvaluationRepository;
import com.tecsup.financego.repository.TModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final TEvaluationRepository evaluationRepository;
    private final TModuleRepository moduleRepository;

    // Obtener todas las Evaluaciones
    public List<EvaluationDto> getAll() {
        List<TEvaluationEntity> evaluationEntities = evaluationRepository.findAll();
        return evaluationEntities.stream().map(TEvaluationEntity::toDto).toList();
    }

    // Obtener una Evaluación por ID
    public EvaluationDto get(Long id) {
        Optional<TEvaluationEntity> evaluationEntityOptional = evaluationRepository.findById(id);
        return evaluationEntityOptional.map(TEvaluationEntity::toDto).orElse(null);
    }

    // Crear una nueva Evaluación
    public EvaluationDto create(EvaluationDto evaluationDto) {
        TModuleEntity module = moduleRepository.findById(evaluationDto.getModule().getId())
                .orElseThrow(() -> new IllegalArgumentException("Módulo no encontrado"));

        TEvaluationEntity evaluationEntity = new TEvaluationEntity(evaluationDto, module);
        evaluationEntity = evaluationRepository.save(evaluationEntity);
        return evaluationEntity.toDto();
    }

    // Actualizar una Evaluación existente
    public EvaluationDto update(Long id, EvaluationDto evaluationDto) {
        TEvaluationEntity evaluationEntity = evaluationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada"));

        evaluationEntity.update(evaluationDto);
        evaluationEntity = evaluationRepository.save(evaluationEntity);
        return evaluationEntity.toDto();
    }

    // Eliminar una Evaluación por su ID
    public void delete(Long id) {
        if (!evaluationRepository.existsById(id)) {
            throw new IllegalArgumentException("Evaluación no encontrada");
        }
        evaluationRepository.deleteById(id);
    }
}
