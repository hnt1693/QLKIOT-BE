package com.fil.authentication.client;

import com.fil.authentication.commons.ResponseAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("common-service")
public interface DungChungClient {
    @GetMapping(value = "danhmucdiaban")
    Map<String,Object> dongBoDiaBan(@RequestParam Integer page, @RequestParam Integer pageSize);
}
