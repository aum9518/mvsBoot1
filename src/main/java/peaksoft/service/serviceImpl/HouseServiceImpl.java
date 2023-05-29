package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.exceptions.MyException;
import peaksoft.repostiory.AgencyRepository;
import peaksoft.repostiory.HouseRepository;
import peaksoft.service.HouseService;

import java.util.Collections;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository repository;
    private final AgencyRepository agencyRepository;
    @Override
    public void saveHouse(Long agencyId, House house) {
        try{
            Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new MyException("Agency with Id: " + agencyId + " is not found"));
            house.setAgency(agency);
            house.setIsBooked(false);
            repository.save(house);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> getAllHouses() {
        return repository.findAll();
    }

    @Override
    public House getHouseById(Long id) {
        try{
            return repository.findById(id).orElseThrow(()->new MyException("Agency with Id: " + id + " is not found"));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateHouse(Long id, House newHouse) {
        try{
            House house = repository.findById(id).orElseThrow(() ->
                    new MyException("Agency with Id: " +
                            id + " is not found"));
            house.setHouseType(newHouse.getHouseType());
            house.setCountry(newHouse.getCountry());
            house.setImage(newHouse.getImage());
            house.setAddress(newHouse.getAddress());
            house.setDescription(newHouse.getDescription());
            house.setRoom(newHouse.getRoom());
            house.setPrice(newHouse.getPrice());
            house.setIsBooked(newHouse.getIsBooked());
            repository.save(house);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteHouseById(Long id) {
       try{
           if (repository.existsById(id)){
               repository.deleteById(id);
           }else  throw new MyException("Agency with Id: " +
                   id + " is not found ");
           repository.findById(id);
       }catch (MyException e){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public List<House> getAllSortedHouse(String ascDesc) {
        try{
            if (ascDesc.equalsIgnoreCase("asc")) {
                return repository.getAllSortedHouseAsc();
            } else if (ascDesc.equalsIgnoreCase("desc")) {
                return repository.getAllSortedHouseDesc();
            } else {
                throw new MyException("The list is empty");
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
