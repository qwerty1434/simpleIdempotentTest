package com.example.idempotent.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ServiceLogic {
    private final Repository repository;

    public Integer myLogic(Dto dto){
        int id = dto.getId();
        if(repository.findById(id).isPresent()){
            Domain data = repository.findById(id).get();
            data.addCnt();
            return data.cnt;
        }else{
            Domain data = Domain.builder().cnt(1).build();
            repository.save(data);
            return data.cnt;
        }
    }
}
