package com.tecsup.financego.service;

import com.tecsup.financego.common.type.ModuleDto;
import com.tecsup.financego.entity.TModuleEntity;
import com.tecsup.financego.repository.TModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final TModuleRepository moduleRepository;

    public List<ModuleDto> search(String description, String code, String content) {
        List<TModuleEntity> courseEntityList = moduleRepository.findAll();
        return courseEntityList.stream().map(TModuleEntity::toDto).toList();
    }

    public List<ModuleDto> getFourResultRandom(){
        List<TModuleEntity> moduleDtoList = moduleRepository.getFourResultRandom();
        return moduleDtoList.stream().map(TModuleEntity::toDto).toList();
    }
}
