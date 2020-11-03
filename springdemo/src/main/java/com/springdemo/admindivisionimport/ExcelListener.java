package com.springdemo.admindivisionimport;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户人员excel监听器
 *
 * @author fraser
 * @date 2019/10/6 10:22 AM
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<DicVO> {
    private AdministratorDivisionInterface iadministratorDivision;

    private static final String adminPccId = "8d0129f6b65946af94bbbeae339008bf";
    private static final String adminCId = "7672d9f713804830b044de5134f949d5";

    private String parseType;

    public ExcelListener() {
    }

    public ExcelListener(AdministratorDivisionInterface iadministratorDivision, String parseType) {
        this.iadministratorDivision = iadministratorDivision;
        this.parseType = parseType;
    }

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 4;
    List<AdministratorDivisionBO> list = new ArrayList<AdministratorDivisionBO>();

    @Override
    public void invoke(DicVO dicVO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(dicVO));

        list.add(convertData(dicVO, parseType));
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }


    private AdministratorDivisionBO convertData(DicVO dicVO, String parseType) {
        AdministratorDivisionBO administratorDivisionBO = new AdministratorDivisionBO();
        administratorDivisionBO.setDataKey(dicVO.getCode().trim());
        administratorDivisionBO.setDataValue(dicVO.getName().trim());
        if ("0001".equals(parseType)) {
            administratorDivisionBO.setDataDictionary(adminPccId);
            administratorDivisionBO.setDataDictionaryCode("administrativeDivision_pcc");
        } else {
            administratorDivisionBO.setDataDictionary(adminCId);
            administratorDivisionBO.setDataDictionaryCode("administrativeDivision_c");
        }
        administratorDivisionBO.setParent_id("");
        administratorDivisionBO.setCreatedTime(new Date());
        administratorDivisionBO.setCreater("37F9A430AE1A45D3A106BECD12EF8F47");
        administratorDivisionBO.setBusiness_id("");
        administratorDivisionBO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        administratorDivisionBO.setCreatedDeptId("833089A1DC3B4E1E8B625408F798B8CC");
        administratorDivisionBO.setNumber1596185401203(new BigDecimal(1));
        administratorDivisionBO.setModifiedTime(new Date());
        administratorDivisionBO.setModifier("37F9A430AE1A45D3A106BECD12EF8F47");
        administratorDivisionBO.setOwner("37F9A430AE1A45D3A106BECD12EF8F47");
        administratorDivisionBO.setOwnerDeptQueryCode("1_1#5A4BB6125B4B4E1A83C2DDD2075E22B3#F9259D359A794EDBOPLJUY68A24CA0D5#9DAD7C1274EA432C8523212D0C276D7B#11CD59FDD0944E0797C930DA6F0939DC");
        return administratorDivisionBO;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        iadministratorDivision.saveData(list);
    }
}
