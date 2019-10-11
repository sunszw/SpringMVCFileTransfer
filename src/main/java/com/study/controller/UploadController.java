package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/")
public class UploadController {


    @RequestMapping("upload.do")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws IOException {
        System.out.println("UploadController.upload()");

        for (int i = 0; i < file.length; i++) {
            //是否上传了文件
            boolean isEmpty = file[i].isEmpty();
            System.out.println("isEmpty:" + isEmpty);

            //获取文件大小
            long size = file[i].getSize();
            System.out.println("size:" + size);

            //获取文件类型
            String mime=file[i].getContentType();
            System.out.println("MIME:"+mime);


            //获取初始文件名(上传的文件在客户端的文件名)
            String originalFileName = file[i].getOriginalFilename();
            System.out.println("name:"+originalFileName);
            //最终保存时的文件夹
            String parentPath = request.getServletContext().getRealPath("upload");
            File parent = new File(parentPath);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            //最终保存时的文件名
            String fileName = UUID.randomUUID().toString();
            int beginIndex = originalFileName.lastIndexOf(".");
            String suffix = "";
            if (beginIndex > 0) {
                suffix = originalFileName.substring(beginIndex, originalFileName.length());
            }
            String child = fileName + suffix;
            //文件保存的位置
            File dest = new File(parent, child);
            //执行保存
            file[i].transferTo(dest);
        }


        return null;
    }
}
