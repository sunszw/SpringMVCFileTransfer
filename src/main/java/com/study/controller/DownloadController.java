package com.study.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.URLEncoder;


@Controller
@RequestMapping("file")
public class DownloadController {


    @RequestMapping("download.do")
    @ResponseBody
    public ResponseEntity<byte[]> download(@RequestParam("file") String fileName, HttpServletRequest request) throws IOException {
        //1.获得文件的绝对路径和文件名
        String path = request.getServletContext().getRealPath("download");
        File file = new File(path, fileName);
        fileName = URLEncoder.encode(fileName, "utf-8");
        //2.设置响应头Disposition和响应类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //3.返回数据
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
