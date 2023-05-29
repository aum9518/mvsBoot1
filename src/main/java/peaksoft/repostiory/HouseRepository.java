package peaksoft.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House , Long> {

    @Query("SELECT h FROM House h ORDER BY h.houseType ASC")
    List<House> getAllSortedHouseAsc();

    @Query("SELECT h FROM House h ORDER BY h.houseType DESC")
    List<House> getAllSortedHouseDesc();

}
