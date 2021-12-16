package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.services.DanhMucDiaBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dongbo")
public class DongBoController {
    @Autowired
    private DanhMucDiaBanService danhMucDiaBanService;

    @GetMapping("/danhmucdiaban")
    public Object dongBoDanhMucDiaBan(@RequestParam(required = false) Integer page,
                      @RequestParam(required = false) Integer pageSize) throws Exception {
        danhMucDiaBanService.dongBoDanhMucDiaBan();
        return new ResponseAPI("Đang đồng bộ", null);
    }

    @GetMapping("/danhmucdiaban/pause-job")
    public Object pauseDongBoDiaBan(@RequestParam(required = false) Long jobId) throws Exception {
        danhMucDiaBanService.pauseJob(jobId);
        return new ResponseAPI("Đang dừng đồng bộ", null);
    }

    @GetMapping("/danhmucdiaban/resume-job")
    public Object resumeDongBoDiaBan(@RequestParam(required = false) Long jobId) throws Exception {
        danhMucDiaBanService.resumeJob(jobId);
        return new ResponseAPI("Đang dừng đồng bộ", null);
    }
}
