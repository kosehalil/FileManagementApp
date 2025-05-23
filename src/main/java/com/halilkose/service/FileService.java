package com.halilkose.service;

import com.halilkose.model.Files;
import com.halilkose.model.User;

import java.io.File;
import java.util.List;

public interface FileService {

    public Files saveFile(Files files);

    public List<Files> getAllFiles();

    public Boolean deleteFiles(Integer id);

}
