package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repostiory.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository repository;
    @Override
    public void saveAgency(Agency agency) {
        repository.save(agency);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return repository.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NullPointerException("is not found"));
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
        Agency agency = repository.findById(id).orElseThrow(() -> new NullPointerException("is not found"));
        agency.setName(newAgency.getName());
        agency.setImage(newAgency.getImage());
        agency.setCountry(newAgency.getCountry());
        agency.setEmail(newAgency.getEmail());
        agency.setPhoneNumber(newAgency.getPhoneNumber());
        repository.save(agency);
    }

    @Override
    public void deleteAgencyById(Long id) {
        if (repository.existsById(id)){
        repository.deleteById(id);
        }else throw new NullPointerException("is not found");
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return repository.searchAgencyByAnyInfo(word);
    }

    @Override
    public List<House> getAllAgencyHouse() {
        return repository.getAllAgencyHouse();
    }
}
