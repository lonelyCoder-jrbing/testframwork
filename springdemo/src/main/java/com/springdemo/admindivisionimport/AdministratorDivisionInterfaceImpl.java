package com.springdemo.admindivisionimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorDivisionInterfaceImpl implements AdministratorDivisionInterface {
    @Autowired
    private AdministratorDivisionRepository administratorDivisionRepository;

    @Override
    public boolean saveData(List<AdministratorDivisionBO> administratorDivisionBOS) {

        for (AdministratorDivisionBO administratorDivisionBO : administratorDivisionBOS) {
            administratorDivisionRepository.save(administratorDivisionBO);
        }
        return false;
    }
}
