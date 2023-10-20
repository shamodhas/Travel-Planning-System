package lk.ijse.tps.guideservice.service.impl;

import lk.ijse.tps.guideservice.dto.GuideDTO;
import lk.ijse.tps.guideservice.entity.Guide;
import lk.ijse.tps.guideservice.exception.DuplicateException;
import lk.ijse.tps.guideservice.exception.NotFoundException;
import lk.ijse.tps.guideservice.persistance.GuideDao;
import lk.ijse.tps.guideservice.service.GuideService;
import lk.ijse.tps.guideservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if(guideDao.findGuideByNic(guideDTO.getNic()).isPresent())
            throw new DuplicateException("Guide nic duplicated");
        String guideId;
        do {
            guideId = String.format("G%S", UUID.randomUUID());
        } while (guideDao.findById(guideId).isPresent());
        guideDTO.setGuideId(guideId);
        return convertor.getGuideDTO(guideDao.save(convertor.getGuide(guideDTO)));
    }

    @Override
    public GuideDTO getSelectedGuide(String guideId) {
        return convertor.getGuideDTO(guideDao.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found")));
    }

    @Override
    public void updateGuide(GuideDTO guideDTO) {
        guideDao.findById(guideDTO.getGuideId()).orElseThrow(() -> new NotFoundException("Guide not found"));
        Optional<Guide> optionalGuide = guideDao.findGuideByNic(guideDTO.getNic());
        if (optionalGuide.isPresent() && !optionalGuide.get().getGuideId().equals(guideDTO.getGuideId()))
            throw new DuplicateException("Guide nic duplicated");
        guideDao.save(convertor.getGuide(guideDTO));
    }

    @Override
    public void deleteGuide(String guideId) {
        guideDao.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found"));
        // in use
        guideDao.deleteById(guideId);
    }

    @Override
    public List<GuideDTO> getAllGuide() {
        return guideDao.findAll().stream().map(convertor::getGuideDTO).collect(Collectors.toList());
    }
}
