package com.fil.authentication.controllers;

import com.fil.authentication.models.MenuCase;
import com.fil.authentication.services.MenuCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/menu")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class MenuCaseController {
    @Autowired
    private MenuCaseService menuCaseService;

    @GetMapping
    public Object get(@RequestParam(required = false) String searchData,
                      @RequestParam(required = false) String sortData,
                      @RequestParam(required = false) Integer page,
                      @RequestParam(required = false) Integer pageSize
    ) throws Exception {
        return menuCaseService.getAll(searchData, sortData, null, page, pageSize);
    }

    @PostMapping("/{clientId}")
    public Object create(@Valid @RequestBody MenuCase menuCase, @PathVariable String clientId) throws Exception {
        return menuCaseService.createWith(menuCase, clientId);
    }

    @PutMapping
    public Object put(@Valid @RequestBody MenuCase menuCase, @RequestParam boolean modifiedChild) throws Exception {
        return menuCaseService.putWith(menuCase, modifiedChild);
    }

    @GetMapping("/find/{id}")
    public Object findById(@PathVariable Long id) throws Exception {
        return menuCaseService.findById(id);
    }

    @DeleteMapping()
    public Object deletes(@RequestBody List<Long> ids) throws Exception {
        return menuCaseService.deletes(ids);
    }


}
