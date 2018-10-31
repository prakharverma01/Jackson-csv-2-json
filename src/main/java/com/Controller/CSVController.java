package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.Services.ConvertCsv2Json;

@Controller
public class CSVController {


	@Autowired
	ConvertCsv2Json csv2JsonService;
	
	public void startTask() {
		csv2JsonService.convertCsv2Json();
	}
	
}
