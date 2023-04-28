package com.example.idempotent.simple;

import com.example.idempotent.common.Dto;
import com.example.idempotent.common.ServiceLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class basicController {
    private final ServiceLogic service;
    Map<String,Integer> idempotentDB = new HashMap<>();

    @PostMapping("/idempotent")
    public Integer idempotent(@RequestHeader("idempotentId") String idempotentId, @RequestBody Dto dto){
        if(idempotentDB.containsKey(idempotentId)){
            return idempotentDB.get(idempotentId);
        }else {
            Integer returnVal = service.myLogic(dto);
            idempotentDB.put(idempotentId, returnVal);
            return returnVal;
        }
    }

    @PostMapping("/nonIdempotent")
    public Integer nonIdempotent(@RequestBody Dto dto){
        return service.myLogic(dto);
    }

}
