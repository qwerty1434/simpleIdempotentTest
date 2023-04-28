package com.example.idempotent.withRedis;

import com.example.idempotent.common.Dto;
import com.example.idempotent.common.ServiceLogic;
import com.example.idempotent.withRedis.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class withRedisController {
    private final ServiceLogic serviceLogic;
    private final RedisService redisService;
    @PostMapping("/idempotent")
    public Integer idempotent(@RequestHeader("idempotentId") String idempotentId, @RequestBody Dto dto){
        if(redisService.isExists(idempotentId)){
            return Integer.parseInt(redisService.getValues(idempotentId));
        }else {
            Integer returnVal = serviceLogic.myLogic(dto);
            redisService.setValues(idempotentId, Integer.toString(returnVal), Duration.ofSeconds(3));
            return returnVal;
        }
    }

    @PostMapping("/nonIdempotent")
    public Integer nonIdempotent(@RequestBody Dto dto){
        return serviceLogic.myLogic(dto);
    }

}
