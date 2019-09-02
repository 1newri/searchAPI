package kr.co.searchAPI.regionproject;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.searchAPI.regionproject.dto.RegionDTO;
import kr.co.searchAPI.regionproject.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RegionRepositoryTest {
	
	@Autowired
	RegionRepository regionRepository;
	
	@Test
	@Ignore
	public void testRegionRepository() {
		
		RegionDTO regionDTO = RegionDTO.builder()
				.regionSeq((long) 9999)
				.region("지자체명")
				.target("지원대상")
				.usage("용도")
				.limit("지원한도")
				.rate("이차보전")
				.institute("추천기관")
				.mgmt("관리점")
				.reception("취급점")
				.build();
		
		regionRepository.save(regionDTO);
		
	}
	
	@Test
	@Ignore
	public void testfindAll() {
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		list = regionRepository.findAll();
	}
	
	@Test
	public void testfindAllByRegion() {
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		list = regionRepository.findAllByRegion("강릉시");
		for(RegionDTO dto : list) {
			log.info(dto.toString());
		}	
	}
	
	@Test
	public void sortBylimit() {
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		/**
		 * 지원한도(Limit) 컬럼에서 지원금액으로 내림차순(DESC) 정렬
		 **/
		
//		list = regionRepository.findAllOrderByLimitDesc();
	}
}
