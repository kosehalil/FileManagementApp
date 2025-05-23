package com.halilkose.service.impl;

import com.halilkose.model.Files;
import com.halilkose.model.User;
import com.halilkose.repository.FileRepository;
import com.halilkose.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Files saveFile(Files files) {
        return fileRepository.save(files);
    }

    @Override
    public List<Files> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public Boolean deleteFiles(Integer id) {
        Files files= fileRepository.findById(id).orElse(null);

        if(!ObjectUtils.isEmpty(files))
        {
            fileRepository.delete(files);
            return true;
        }
        return false;
    }

}
