package com.seeta.common.framework.cucumber.web.core;

import java.io.File;
import java.io.FilenameFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericExtFilter.
 */
public class GenericExtFilter implements FilenameFilter {

	/** The ext. */
	private String ext;

	/**
	 * Instantiates a new generic ext filter.
	 *
	 * @param ext the ext
	 */
	public GenericExtFilter(String ext) {
		this.ext = ext;
	}

	/**
	 * Accept.
	 *
	 * @param dir the dir
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean accept(File dir, String name) {
		return (name.endsWith(ext));
	}
}

