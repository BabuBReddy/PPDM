package ppdm.preprocessing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ppdm.preprocessing.*;
public class Preprocessing {
	public void preprocess() throws IOException
	{
		//HandlingMissingValues hmv = new HandlingMissingValues();
		//hmv.getAllMissingValues();
		DataSelection ds = new DataSelection();
		MetaData md = new MetaData();
		ds.getDataIntoDesiredForm(md);
	}
}
// 