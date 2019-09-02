package kr.co.searchAPI.regionproject.serivce.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.searchAPI.regionproject.dto.RegionDTO;
import kr.co.searchAPI.regionproject.repository.RegionRepository;
import kr.co.searchAPI.regionproject.serivce.RegionProjectService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionProjectServiceImpl implements RegionProjectService{

	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public boolean readFile(MultipartFile[] files) {
		
		boolean result = false;
		
		String line = null;
		BufferedReader br = null;
		try {
			
			for(MultipartFile file : files) {
				
				File f = new File(file.getOriginalFilename());
				f.createNewFile();
				FileOutputStream output = new FileOutputStream(f);
				output.write(file.getBytes());
				output.close();
				
				br = new BufferedReader(new FileReader(f));
				
				boolean first = true;
				while((line = br.readLine()) != null) {
					
					if(first) {
						first = false;
					}else {
						String[] arr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
						
						RegionDTO regionDTO = RegionDTO.builder()
									.regionSeq(Long.parseLong(arr[0]))
									.region(arr[1])
									.target(arr[2])
									.usage(arr[3])
									.limit(arr[4])
									.rate(arr[5])
									.institute(arr[6])
									.mgmt(arr[7])
									.reception(arr[8])
									.build();
						
						if(regionDTO != null) {
							regionRepository.save(regionDTO);
						}
					}
				}
			}
			result = true;
			log.info("readFile("+result+") : " + "Success!");
		} catch (Exception e) {
			result = false;
			log.info("readFile("+result+") : " + "Error!");
		}
		return result;
	}

	@Override
	public List<RegionDTO> allFindRegion() {

		return regionRepository.findAll();
	}

	@Override
	public List<RegionDTO> findAllByRegion(String keyword) {

		return regionRepository.findAllByRegion(keyword);
	}

}
