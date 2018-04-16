package com.paradisum.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class that handles with parsing files with the cfg extension.
 * @author Carlos Aviles
 */
public final class CFGFileReader {
	
	/**
	 * The reader instance, for opening the file.
	 */
	private final BufferedReader reader;
	
	/**
	 * A map that contains stored data from read files.
	 */
	private final Map<String, String> mappings = new HashMap<>();
	
	/**
	 * Instantiates a new file reader instance.
	 * @param stream The input stream instance.
	 */
	public CFGFileReader(InputStream stream) {
		this.reader = new BufferedReader(new InputStreamReader(stream));
	}
	
	/**
	 * Parses the data from the file.
	 * @throws IOException Any I/O related problems whilst parsing the file.
	 */
	public void parse() throws IOException {
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0 || line.startsWith("#")) {
				continue;
			}
			
			String[] split = line.split(":");
			if(split.length != 2) {
				continue;
			}
			
			String parent = split[0].trim(), child = split[1].trim();
			
			mappings.put(parent, child);
		}
		
		reader.close();
	}
	
	/**
	 * @param parent The 'id' of the line.
	 * @return Gets the value of the line, as a string.
	 */
	public String getChildAsString(String parent) {
		return mappings.get(parent);
	}
	
	/**
	 * @param parent The 'id' of the line.
	 * @return Gets the value of the line, as an integer.
	 */
	public Integer getChildAsInteger(String parent) {
		return Integer.parseInt(mappings.get(parent));
	}
	
	/**
	 * @param parent The 'id' of the line.
	 * @return Gets the value of the line, as a boolean.
	 */
	public Boolean getChildAsBoolean(String parent) {
		return Boolean.parseBoolean(mappings.get(parent));
	}
	
	/**
	 * @param parent The 'id' of the line.
	 * @return Gets the value of the line, as a double.
	 */
	public Double getChildAsDouble(String parent) {
		return Double.parseDouble(mappings.get(parent));
	}

}