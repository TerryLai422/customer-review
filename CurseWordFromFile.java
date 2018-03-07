package de.hybris.platform.customerreview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurseWordFromFile implements CurseWord {

	private List<String> list = new ArrayList<String>();
	
	public CurseWordFromFile(String filename) {
		// this is one of the option to store the list of curse words in file.
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				for (String string: country) {
					list.add(string);
					System.out.println(string);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> getList() {
		return list;
	}
}
