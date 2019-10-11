package com.study.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;


@RestController
@RequestMapping("/")
public class DownloadController {


    @RequestMapping("download.do")
    public ResponseEntity<byte[]> download(@RequestParam("file") String name, HttpServletRequest request) throws IOException {
        //获取服务器目录
        String path = request.getServletContext().getRealPath("download");
        System.out.println(path);
        System.out.println(name);
        String fileName = name;
        File file=new File(path,fileName);
        //处理文件名含有中文
        fileName = URLEncoder.encode(fileName, "utf-8");
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }


}
