package kr.co.searchAPI.regionproject.serivce.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.searchAPI.common.util.DateUtil;
import kr.co.searchAPI.regionproject.dto.RegionCdDTO;
import kr.co.searchAPI.regionproject.dto.RegionDTO;
import kr.co.searchAPI.regionproject.repository.RegionCdRepository;
import kr.co.searchAPI.regionproject.repository.RegionRepository;
import kr.co.searchAPI.regionproject.serivce.RegionProjectService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionProjectServiceImpl implements RegionProjectService{

	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	RegionCdRepository regionCdRepository;
	
	String date = DateUtil.getTime();
	
	/**
	 *  데이터 파일에서 각 레코드를 데이터베이스에 저장
	 */
	@Override
	public boolean readFile(MultipartFile[] files) {
		
		boolean result = false;
		
		String line = null;
		BufferedReader br = null;
		
		try {
			
			
			
			for(MultipartFile file : files) {
				log.info("regionProjectService file: {}", file.getOriginalFilename());
				File f = new File(file.getOriginalFilename());
				f.createNewFile();
				FileOutputStream output = new FileOutputStream(f);
				output.write(file.getBytes());
				output.close();
				
				if(file.getSize() > 0) {
					br = new BufferedReader(new FileReader(f));
					
					boolean first = true;
					while((line = br.readLine()) != null) {
						
						if(first) {
							first = false;
						}else {
							
							
							String[] arr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
							String regCode = getRegCode(arr[0]);
							
							RegionCdDTO cdDTO = RegionCdDTO.builder()
									.regionCd(regCode)
									.region(arr[1])
									.build();
							
							if(cdDTO != null) {
								log.info("regionCdRepository.save {} :", cdDTO.toString());
								regionCdRepository.save(cdDTO);
							}
							
							RegionDTO regionDTO = RegionDTO.builder()
									.regionSeq(Long.parseLong(arr[0]))
									.regionCd(regCode)
									.target(arr[2])
									.usage(arr[3])
									.limit(arr[4])
									.rate(arr[5])
									.institute(arr[6])
									.mgmt(arr[7])
									.reception(arr[8])
									.insertDate(date)
									.build();
							
							if(regionDTO != null) {
								log.info("regionRepository.save {} :", regionDTO.toString());
								regionRepository.save(regionDTO);
							}
							
						}
					}
					result = true;
					log.info("readFile("+result+") : " + "Success!");
				}else {
					result = false;
					log.info("file.getSize() : {}", "파일크기가 0입니다."); 
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			log.info("readFile("+result+") : " + "Error!");
		}
		return result;
	}

	public String getRegCode(String seq) {
		String order =  String.format("%04d", Integer.parseInt(seq));
		
		String regCode = "reg" + order;
		
		return regCode;
	}

	/**
	 *	 지원하는 지자체 목록 출력 및 지자체명을 입력 받아 지자체의 지원정보 출력 
	 */
	@Override
	public List<RegionDTO> selectRegion(Map<String, Object> params) {
		List<RegionDTO> list = null;
		
		
		if(params.size() == 0) {
			list = regionRepository.findAll();
		}else {
			// Join으로 대체
			String keyword = (String)params.get("region");
			RegionCdDTO cdDto = regionCdRepository.findByRegion(keyword);
			
			list = regionRepository.findByRegionCdDto(cdDto);
		}
		
		return list;
	}

	@Override
	public void regionUpdate(RegionDTO regionDTO) {
		
		regionDTO.setUpdateDate(date);

		regionRepository.saveRegionCd(regionDTO);
		
	}

}
