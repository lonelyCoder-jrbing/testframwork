package com.springdemo.admindivisionimport;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/oa/related")
@Slf4j
public class AdministrationDivisionImportController {

    @Autowired
    AdministratorDivisionInterface iadministratorDivisionInterface;

    @PostMapping("/uploadWithConstructor")
    public String uploadWithConstructor(@RequestBody MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DicVO.class, new ExcelListener(iadministratorDivisionInterface)).sheet("行政区划").doRead();
        return "success";
    }




}
