package com.lulosys.projectManager.services;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.DocumentEntity;
import com.lulosys.projectManager.repositories.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    @PostMapping
    public DocumentEntity postService(DocumentEntity document) {
        try {
            return documentRepository.save(document);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ArrayList<DocumentEntity> getByTaskIdService(long taskId) {
        try {
            return documentRepository.findByTaskId(taskId);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public boolean deleteService(Long id) {
        try {
            documentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
