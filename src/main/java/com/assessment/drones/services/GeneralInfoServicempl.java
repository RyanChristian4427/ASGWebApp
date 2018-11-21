package com.assessment.drones.services;

import com.assessment.drones.domain.GeneralInfo;
import com.assessment.drones.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralInfoServicempl {
    private GeneralRepository generalRepository;

    @Autowired
    public GeneralInfoServicempl(GeneralRepository aGeneralRepository){
        generalRepository = aGeneralRepository;
    }

    public String addGeneralInfo(GeneralInfo general){
        Integer response = generalRepository.addGeneral(general);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }
}
