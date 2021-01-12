package com.springdemo.admindivisionimport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/oa/related")
@Slf4j
public class DataDealController {

    @Autowired
    AdministratorDivisionRepository administratorDivisionRepository;
    private static final Map<String, String> strMap = new ConcurrentHashMap<>();
    private static final String chinaId = "8e44c01dbc6245389961f9bcca384de2";

    /****
     * 处理省数据
     */
    @PostMapping("/dealProvinceData")
    public void dealData() {
        List<AdministratorDivisionBO> all = administratorDivisionRepository.findAll();
        List<AdministratorDivisionBO> collect = all.stream().filter(el -> "administrativeDivision_pcc".equals(el.getDataDictionaryCode()) && Objects.nonNull(el.getDataKey()))
                .sorted((e1, e2) -> Integer.valueOf(e1.getDataKey()) < Integer.valueOf(e2.getDataKey()) ? -1 : 1)
                .collect(Collectors.toList());
        List<String> provincePart = partProvince(all);

        Map<String, List<AdministratorDivisionBO>> provinceMap = new HashMap<>();
        for (int i = 0; i < provincePart.size() - 1; i++) {
            List<AdministratorDivisionBO> province = new ArrayList<>();
            for (int j = 0; j < collect.size(); j++) {
                if (Integer.valueOf(provincePart.get(i)) <= Integer.valueOf(collect.get(j).getDataKey())
                        &&
                        Integer.valueOf(collect.get(j).getDataKey()) < Integer.valueOf(provincePart.get(i + 1))) {
                    province.add(collect.get(j));
                }
            }
            provinceMap.put(provincePart.get(i), province);
        }
        Map<String, String> provinceKVMap = provinceKV(all);

        Set<String> provinceIds = provinceKVMap.keySet();
        log.info("provinceIds=>{}", provinceIds);
        provinceMap.forEach((k, v) -> {
            log.info("k=>{}   v=>{}", k, v);
            v.forEach(e -> {
                e.setParent_id(provinceKVMap.get(k));
                if (provinceIds.contains(e.getDataKey())) {
                    log.info("provinceIds_parentId=>{}  provinceIds_dataValue=>{}", e.getId(), e.getDataValue());
                    e.setParent_id(chinaId);
                }
                administratorDivisionRepository.save(e);
            });
        });
    }

    /****
     * 处理城市数据
     */
    @PostMapping("/dealCityData")
    public void dealCityData() {
        List<AdministratorDivisionBO> all = administratorDivisionRepository.findAll();
        List<AdministratorDivisionBO> collect = all.stream()
                .filter(el -> !chinaId.equals(el.getParent_id()))
                .filter(el -> "administrativeDivision_pcc".equals(el.getDataDictionaryCode()) && Objects.nonNull(el.getDataKey()))
                .sorted((e1, e2) -> Integer.valueOf(e1.getDataKey()) < Integer.valueOf(e2.getDataKey()) ? -1 : 1)
                .collect(Collectors.toList());
        //按照省划分
        Map<String, List<AdministratorDivisionBO>> provinceMap = collect.stream().collect(Collectors.groupingBy(AdministratorDivisionBO::getParent_id));

        provinceMap.forEach((k, v) -> {
            v.forEach(e -> {
                if (Integer.valueOf(e.getDataKey()) % 100 == 0) {
                    log.info("id=>{} name=>{}", e.getId(), e.getDataValue());
                    strMap.put("city", e.getId());
                }
                if (strMap.size() > 0 && Integer.valueOf(e.getDataKey()) % 100 != 0) {
                    e.setParent_id(strMap.get("city"));
                }
                administratorDivisionRepository.save(e);
            });
            strMap.clear();
        });
    }

    /****
     * 划分省
     * @param all
     * @return
     */
    public List<String> partProvince(List<AdministratorDivisionBO> all) {
        return all
                .stream()
                .filter(el -> el.getDataKey().contains("0000"))
                .map(el -> el.getDataKey())
                .sorted((e1, e2) -> Integer.valueOf(e1) < Integer.valueOf(e2) ? -1 : 1)
                .collect(Collectors.toList());
    }

    /***
     * 获取datakey 和 id对应的map
     * @param all
     * @return
     */
    public Map<String, String> provinceKV(List<AdministratorDivisionBO> all) {
        return all
                .stream()
                .filter(el -> el.getDataKey().contains("0000"))
                .collect(Collectors.toMap(AdministratorDivisionBO::getDataKey, AdministratorDivisionBO::getId, (k1, k2) -> k1));
    }

    /****
     * demo
     */
    @PostMapping("/demo")
    public String demo() {
        log.info("demo.......");
        return "hello skywaking";
    }

}
