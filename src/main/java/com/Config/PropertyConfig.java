package com.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:c2jProp.properties")
public class PropertyConfig {

	@Value("${config.SourceFileName}")
	private String SourceFileName;

	@Value("${config.DestinationFileName}")
	private String DestinationFileName;
	
	@Value("${config.seprator}")
	private String seprator;
	
	@Value("${config.SourceFilePath}")
	private String SourceFilePath;
	
	
	@Value("${config.DestinationFilePath}")
	private String DestinationFilePath;
	
	@Value("${config.headerPresent}")
	private String headerPresent;
	
	@Value("${config.Header}")
	private String header;
	
	public String getHeader(){
		return header;
	}
	
	public void setHeader(String header){
		this.header=header;
	}
	
	public String getHeaderPresent() {
		return headerPresent;
	}


	public void setHeaderPresent(String headerPresent) {
		this.headerPresent = headerPresent;
	}


	public String getSeprator() {
		return seprator;
	}


	public void setSeprator(String seprator) {
		this.seprator = seprator;
	}


	public String getSourceFilePath() {
		return SourceFilePath;
	}


	public void setSourceFilePath(String sourceFilePath) {
		SourceFilePath = sourceFilePath;
	}


	public String getDestinationFilePath() {
		return DestinationFilePath;
	}


	public void setDestinationFilePath(String destinationFilePath) {
		DestinationFilePath = destinationFilePath;
	}


	public String getSourceFileName() {
		return SourceFileName;
	}


	public void setSourceFileName(String sourceFileName) {
		SourceFileName = sourceFileName;
	}


	public String getDestinationFileName() {
		return DestinationFileName;
	}


	public void setDestinationFileName(String destinationFileName) {
		DestinationFileName = destinationFileName;
	}


	public PropertyPlaceholderConfigurer properties() {
		PropertyPlaceholderConfigurer placeholderConfigurer= new PropertyPlaceholderConfigurer();
		return placeholderConfigurer;
	}
}
