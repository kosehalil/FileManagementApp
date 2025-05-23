package com.halilkose.controller;

import com.halilkose.model.User;
import com.halilkose.service.FileService;
import com.halilkose.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.halilkose.model.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "user/index";
    }

    @GetMapping("/uploadFile")
    public String uploadFileForm() {
        return "user/uploadFile";
    }

    @PostMapping("/saveFile")
    public String saveFile(@ModelAttribute com.halilkose.model.Files fileEntity,
                           @RequestParam("file") MultipartFile file,
                           HttpSession session) throws IOException {

        if (file.isEmpty()) {
            session.setAttribute("errorMessage", "Lütfen bir dosya seçin!");
            return "redirect:/user/uploadFile";
        }

        String fileName = file.getOriginalFilename();
        fileEntity.setFileName(fileName);

        String fileExtension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = fileName.substring(i + 1).toLowerCase();
        }

        List<String> allowedExtensions = List.of("png", "jpg", "jpeg", "pdf", "txt", "pptx", "docx");

        if (!allowedExtensions.contains(fileExtension)) {
            session.setAttribute("errorMessage", "yalnizca png, jpg, jpeg, pdf, txt, pptx, docx dosya formatlarina izin verilmektedir");
            return "redirect:/user/uploadFile";
        }

        Files saved = fileService.saveFile(fileEntity);

        if (saved != null) {
            File saveDir = new ClassPathResource("static/resimler").getFile();

            File targetFile = new File(saveDir, fileName);
            file.transferTo(targetFile);

            session.setAttribute("successMessage", "dosya basariyla yuklendi");
        } else {
            session.setAttribute("errorMessage", "dosya kaydedilemedi");
        }

        return "redirect:/user/uploadFile";
    }

    @GetMapping("/viewFile")
    public String viewFiles(Model model) {
        model.addAttribute("files", fileService.getAllFiles());
        return "user/viewFile";
    }

    @GetMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable Integer id, HttpSession session) {
        Boolean deleted = fileService.deleteFiles(id);

        if (deleted) {
            session.setAttribute("successMessage", "Dosya silindi.");
        } else {
            session.setAttribute("errorMessage", "Dosya silinemedi.");
        }

        return "redirect:/user/viewFile";
    }

    @GetMapping("/userList")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/userList";
    }

}