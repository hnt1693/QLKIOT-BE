package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.services.DanhMucDiaBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/danhmucdiaban")
public class DanhMucDiaBanController {

    @Autowired
    private DanhMucDiaBanService danhMucDiaBanService;

    @GetMapping("/by-code")
    public ResponseAPI getByCode(@RequestParam String code) throws Exception {
        return danhMucDiaBanService.getByCode(code);
    }

    @GetMapping("/by-parentcode")
    public ResponseAPI getByParentCode(@RequestParam String code) throws Exception {
        return danhMucDiaBanService.getByParentCode(code);
    }

}
