package kr.co.searchAPI.regionproject.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.searchAPI.regionproject.dto.RegionCdDTO;

@Repository
public interface RegionCdRepository  extends JpaRepository<RegionCdDTO, String>{

	RegionCdDTO findByRegion(String keyword);
}
