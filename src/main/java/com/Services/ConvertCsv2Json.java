package com.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Config.PropertyConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class ConvertCsv2Json implements JsonServiceProvider {

	@Autowired
	PropertyConfig config;
	
	@Override
	public void convertCsv2Json() {
		CsvSchema csvSchema = null;
		
		//output File				
		File outputFile = new File( config.getDestinationFilePath(),
					    StringUtils.split(config.getDestinationFileName(),".")[0]+
					    "_" +
					    getTimestamp() +"."+
					    StringUtils.split(config.getDestinationFileName(),".")[1]
					  );
		
		//Input File
		File inputFile=new File(config.getSourceFilePath()+"/"+config.getSourceFileName());
		
		/*
		 * Lets Check for file existence First
		 * 
		 * */
		if( !(inputFile.exists()) || inputFile.isDirectory()) { 
			MsgService("The File To be Convert into JSON does not exists. "
					+ "\nAborting the Process. "
					+ "\nPlease keep the file at path : "+config.getSourceFilePath());
			endApplication(1);
		}
		
		/*
		 * If file exists following processing will be done.
		 * 
		 * */
		
		if(config.getHeaderPresent().charAt(0)=='Y')
		{
			csvSchema = CsvSchema.builder()
			/*Set separator*/
			.setColumnSeparator(config.getSeprator().charAt(0))
			/*Set header as present*/
			.setUseHeader(true)
			.build();
		}
		else
		{
			
		    csvSchema = CsvSchema.builder()
				/*Set separator*/
				.setColumnSeparator(config.getSeprator().charAt(0))
				/*Set header as present*/
				.setUseHeader(false)
				.addColumns(Arrays.stream(config.getHeader().split(config.getSeprator())).map(String::trim).collect(Collectors.toList()),
						CsvSchema.ColumnType.STRING)
				.build();
		}
		
		if(config.getHeaderPresent().charAt(0)=='Y')
		{
			csvSchema.withSkipFirstDataRow(true);
		}
		CsvMapper csvMapper = new CsvMapper();	
		
		try {
			List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(inputFile).readAll();
			ObjectMapper mapper = new ObjectMapper();
			
			/*
			 * Write JSON formatted data to output.json file
			 */
			mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile,readAll);
		}
		catch(FileNotFoundException e) {
			MsgService("File not found" + e.getMessage());
			endApplication(1);
		}catch (IOException e) {
			MsgService("IO Exception while reading file"+e.getMessage());	
			endApplication(1);
		}
		
		endApplication(0);
	}

	public void endApplication(int status) {
		
		switch (status) {
		
		case 1: MsgService("process ending with Non-conversion Envent.");
				break;
		case 0: MsgService("process ending with Conversion Envent.");
				break;
		default:
			break;
			
		}
		System.exit(status);
	}
	
	public void MsgService(String msg)
	{
		System.out.println(msg);
	}
	
	public String getTimestamp() {
		String pattern = "MMddyyyyHHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return (date);
	}
}
