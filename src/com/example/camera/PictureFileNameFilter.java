package com.example.camera;

import java.io.File;
import java.io.FilenameFilter;

public class PictureFileNameFilter implements FilenameFilter
{
	private String _targetFile;
	
	public PictureFileNameFilter()
	{
	}
	
	public PictureFileNameFilter(String targetFile)
	{
		_targetFile = targetFile;
	}
	
	public String getTargetFileName()
	{
		return _targetFile;
	}
	
	public void setTargetFileName(String targetFile)
	{
		_targetFile = targetFile;
	}
	
	@Override
	public boolean accept(File dir, String filename)
	{
		return filename.contains(filename);
	}

}
