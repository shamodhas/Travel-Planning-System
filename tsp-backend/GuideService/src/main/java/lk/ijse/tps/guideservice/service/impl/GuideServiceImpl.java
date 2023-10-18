package lk.ijse.tps.guideservice.service.impl;

import lk.ijse.tps.guideservice.dto.GuideDTO;
import lk.ijse.tps.guideservice.persistance.GuideDao;
import lk.ijse.tps.guideservice.service.GuideService;
import lk.ijse.tps.guideservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:55 AM
 */

@Service
@Transactional
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideDao guideDao;
    @Autowired
    private DataTypeConvertor convertor;
    @Override
    public GuideDTO saveGuide(GuideDTO guideDTO) {
        return null;
    }

    @Override
    public GuideDTO getSelectedGuide(String guideId) {
        return null;
    }

    @Override
    public void updateGuide(GuideDTO guideDTO) {

    }

    @Override
    public void deleteGuide(String guideId) {

    }

    @Override
    public List<GuideDTO> getAllGuide() {
        return null;
    }
}
