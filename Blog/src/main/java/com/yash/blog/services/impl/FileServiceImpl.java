package com.yash.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.blog.services.FileService;
@Service
public  class FileServiceImpl implements FileService {

	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		//get file name
		String name = file.getOriginalFilename();
		
		//random name generate file
		String randomId = UUID.randomUUID().toString();
		String filename1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//fullPath
		String filePath = path + File.separator +filename1;
		
		//create folder if not generate
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		//file copy 
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullPath = path + File.separator +fileName;
		InputStream is  = new FileInputStream(fullPath);
		//db logic to return inputStream
		return is;
	}

}
