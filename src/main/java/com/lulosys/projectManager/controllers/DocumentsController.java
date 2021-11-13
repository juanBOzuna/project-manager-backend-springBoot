package com.lulosys.projectManager.controllers;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.DocumentEntity;
import com.lulosys.projectManager.services.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentsController {
    @Autowired
    DocumentService documentsService;

    @GetMapping()
    public ArrayList<DocumentEntity> index() {
        // return taskService.indexService();
        return null;
    }

    @GetMapping(path = "/{id}")
    public ArrayList<DocumentEntity> get(@PathVariable("id") Long id) {
        // return taskService.getByTaskIdService(id);
        return null;
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean ok = this.documentsService.deleteService(id);
        if (ok) {
            return "Delete  " + id;
        } else {
            return "Failed delete " + id;
        }
    }

    ArrayList<DocumentEntity> getDocumentsByTaskId(long id) {
        return documentsService.getByTaskIdService(id);
    }
}
