package com.mybatisdemos.excelutils;

import java.util.List;

/**
 * create by sumerian on 2020/4/13
 * <p>
 * desc:
 **/
public interface IExcelPageService {
    /***
     * 获取总的记录数量
     * @return integer
     */
    public Integer getTotalDataSize();

    /**
     * 按照页码去查询
     * @param pageNum
     * @return
     */
    public List<? extends IExcelModel> queryPageData(int pageNum);


}
