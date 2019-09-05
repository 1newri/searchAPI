package kr.co.searchAPI.housing.service;

import org.springframework.web.multipart.MultipartFile;

public interface HousiongProjectService {

	boolean readFile(MultipartFile[] files);

}
