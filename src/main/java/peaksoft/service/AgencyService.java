package peaksoft.service;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface AgencyService {
    void saveAgency(Agency agency);
    List<Agency> getAllAgencies();
    Agency getAgencyById(Long id);
    void updateAgency(Long id, Agency newAgency);
    void deleteAgencyById(Long id);
    List<Agency> searchAgency(String word);
    List<House> getAllAgencyHouse();
}
