package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("file")
public class UploadController {


    private static final long FILE_MAX_SIZE = 700 * 1024;
    private static final List<String> FILE_TYPES = new ArrayList<>();

    static {
        FILE_TYPES.add("image/jpeg");
        FILE_TYPES.add("image/png");
    }

    @RequestMapping("upload.do")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            //检查是否上传文件
            if (files[i].isEmpty())
                throw new FileEmptyException("请选择上传有效文件！");

            //检查文件类型
            if (!FILE_TYPES.contains(files[i].getContentType()))
                throw new FileTypeMatchException("请选择有效的文件类型！");

            //检查文件大小
            if (files[i].getSize() > FILE_MAX_SIZE)
                throw new FIleSizeBeyondException("请选择小于" + FILE_MAX_SIZE + "k的文件！");

            //保存的文件夹
            String directoryPath = request.getServletContext().getRealPath("upload");
            File directory = new File(directoryPath);
            if (!directory.exists())
                directory.mkdirs();
            //获取初始文件名
            String originalFileName = files[i].getOriginalFilename();
            String suffix = "";
            int index = originalFileName.lastIndexOf(".");
            if (index > 0)
                suffix=originalFileName.substring(index);
            //保存时的文件名
            String fileName= UUID.randomUUID().toString()+suffix;
            //保存文件
            File file=new File(directory,fileName);
            //执行保存
            try {
                files[i].transferTo(file);
            } catch (IOException e) {
                throw new FileUploadIOException("上传文件时出现读写错误!");
            }
        }

        return "success";
    }
}
