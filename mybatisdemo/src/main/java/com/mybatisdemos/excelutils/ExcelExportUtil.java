package com.mybatisdemos.excelutils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.List;

/**
 * create by sumerian on 2020/4/13
 * <p>
 * desc:
 **/
public class ExcelExportUtil {


    public static SXSSFWorkbook initSXSSWorkbookByBizService(File templeFile, Integer rowAccessWindowSzie, String prefixName,
                                                             String headers[],
                                                             IExcelPageService service) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);

        //sheet页名称前缀
        prefixName = (null == prefixName ? "sheet" : prefixName);
        workbook.setCompressTempFiles(true);
        //表头样式 缺省
        CellStyle headerStyle = getHeaderStyle(workbook);
        //数据body样式
        CellStyle bodyStyle = getBodyStyle(workbook);
        //总的记录数量
        Integer totalDataSize = service.getTotalDataSize();
        if (0 == totalDataSize) {


        }
        List<? extends IExcelModel> iExcelModels = service.queryPageData(0);
        return workbook;
    }

    private static CellStyle getBodyStyle(SXSSFWorkbook workbook) {
        return null;
    }

    private static CellStyle getHeaderStyle(SXSSFWorkbook workbook) {

        return null;

    }


}
