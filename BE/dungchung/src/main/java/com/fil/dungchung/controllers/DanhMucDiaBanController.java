package com.fil.dungchung.controllers;

import com.fil.dungchung.services.DanhMucDiaBanService;
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

    @GetMapping()
    public Object getAll(@RequestParam Integer page, @RequestParam Integer pageSize) throws Exception {
        return danhMucDiaBanService.getAll(null, null, page, pageSize);
    }
}
