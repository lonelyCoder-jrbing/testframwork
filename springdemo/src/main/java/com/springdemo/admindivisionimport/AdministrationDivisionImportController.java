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
import java.util.Objects;

@RestController
@RequestMapping("/api/oa/related")
@Slf4j
public class AdministrationDivisionImportController {

    @Autowired
    AdministratorDivisionInterface iadministratorDivisionInterface;

    /***
     *
     * @param file
     * @param parseCode 0001 行政区划； 0002国家和地区
     * @return
     * @throws IOException
     */

    @PostMapping("/uploadWithConstructor")
    public String uploadWithConstructor(@RequestBody MultipartFile file, String parseCode)  {

        if (Objects.isNull(parseCode)) {
            return "";
        }
        switch (parseCode) {
            case "0001":
                try {
                    EasyExcel.read(file.getInputStream(), DicVO.class, new ExcelListener(iadministratorDivisionInterface, parseCode)).sheet("行政区划").doRead();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "0002":
                try {
                    EasyExcel.read(file.getInputStream(), DicVO.class, new ExcelListener(iadministratorDivisionInterface, parseCode)).sheet("国家和地区名称代码").doRead();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return "";
        }

        return "success";
    }


}
