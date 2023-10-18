package lk.ijse.tps.guideservice.service;


import lk.ijse.tps.guideservice.dto.GuideDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:28 AM
 */

public interface GuideService {
    GuideDTO saveGuide(GuideDTO guideDTO);
    GuideDTO getSelectedGuide(String guideId);
    void updateGuide(GuideDTO guideDTO);
    void deleteGuide(String guideId);
    List<GuideDTO> getAllGuide();
}
