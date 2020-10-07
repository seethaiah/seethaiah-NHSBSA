package com.seeta.common.framework.cucumber.web.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.monte.screenrecorder.ScreenRecorder;
import org.testng.Reporter;



// TODO: Auto-generated Javadoc
/**
 * Utils class will have some common utility methods required.
 */

public abstract class OSUtils {
  
  /**
   * enumeration for Operating System Type.
   */
  public enum OSType {
      
      /** The windows. */
      windows, 
 /** The mac. */
 mac, 
 /** The linux. */
 linux, 
 /** The windows 64. */
 windows64
  }

	
	/**
	 * Two strings with new line.
	 *
	 * @param one the one
	 * @param two the two
	 * @return the string
	 */
	public static String twoStringsWithNewLine(String one, String two) {
		
		String separator = System.getProperty( "line.separator" );
		StringBuilder lines = new StringBuilder( one );
		lines.append( separator );
		lines.append( two );
		return lines.toString( );
		
	}
	
	/**
	 * Windows Only method.
	 *
	 * @param serviceName the service name
	 * @return true if process status is running, false otherwise
	 */
	public static boolean isProcessRuning(String serviceName) {
		if (getOSname() == OSType.windows) {
			Process p;
			try {
				p = Runtime.getRuntime().exec("tasklist");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				String line;

				while ((line = reader.readLine()) != null) {
					if (line.contains(serviceName)) {
						Reporter.log(serviceName + " is running", true);
						return true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			LoggerUtil.log(
					"Client OS is not Windows, cannot check running process: "
							+ serviceName);
			return false;
		}
	}

	/**
	 * Kills the specified process (Windows Only method).
	 *
	 * @param serviceName the service name
	 */
	public static void killProcess(String serviceName) {
		Reporter.log("Trying to kill " + serviceName, true);

		String KILL = "taskkill /F /T /IM ";
		if (getOSname() == OSType.windows) {
			try {
				Runtime.getRuntime().exec(KILL + serviceName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			Reporter.log(
					"Client OS is not window, can not kill " + serviceName,
					true);
	}
	
	/**
	 * utility method for capturing video.
	 *
	 * @param videofilename the videofilename
	 * @param recorder the recorder
	 */
	public static void captureVideo(String videofilename,
			ScreenRecorder recorder) {
		try {
			String currentVideoFilePath = videofilename+"avi";
			Reporter.log("Video Recordings:<br> <a href='file:///"+
				 currentVideoFilePath+
				 "' target='new'>Click here to view </a>");

				if (recorder != null)
					recorder.stop();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in onTestFailure" + e.getMessage());
		}

	}
	
	/**
	 * Creates the folder.
	 *
	 * @param strDirectoy the str directoy
	 */
	public static void createFolder(String strDirectoy) {
		boolean success = (new File(strDirectoy)).mkdirs();
		if (success) {
		}
	}
	
	/**
	 * Method to find the relative path from 2 absolute paths.
	 *
	 * @param absPath the abs path
	 * @param basePath the base path
	 * @return relative path to the file/folder. If an exception occurs absPath will be returned
	 */
	public static String getRelativePath(String absPath, String basePath){
        try {
        	Path pathAbsolute = Paths.get(absPath);
            Path pathBase = Paths.get(basePath);
            Path pathRelative = pathBase.relativize(pathAbsolute);
            return pathRelative.toString(); 
		} catch (Exception e) {
			return absPath;
		}
	}
	
	/**
	 * Last file modified.
	 *
	 * @param dir the dir
	 * @return the file
	 */
	public static File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() { 
	    	 public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
	
	/**
	 * Rename file.
	 *
	 * @param oldFile the old file
	 * @param newFile the new file
	 * @return true, if successful
	 */
	public static boolean renameFile(File oldFile, File newFile) {
		
	     if(oldFile.renameTo(newFile)) {
	         return true;
	      } else {
	         return false;
	      }
		
	}
	
	/**
	 * Gets the file.
	 *
	 * @param folder the folder
	 * @param ext the ext
	 * @return the file
	 */
	public static String getFile(String folder, String ext) {

		GenericExtFilter filter = new GenericExtFilter(ext);

		File dir = new File(folder);
		
		if(dir.isDirectory()==false){
			throw new CustomException("Directory does not exists : " + folder);
		}
		
		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			throw new CustomException("no files end with : " + ext);
		}

		return list[0];
		
	}
	
	/**
	 * Method which gets the list of files of a given file extension from specified path.
	 *
	 * @param folder the folder
	 * @param ext the ext
	 * @return String[]
	 */
	public static String[] getFileList(String folder, String ext) {

		GenericExtFilter filter = new GenericExtFilter(ext);

		File dir = new File(folder);
		
		if(dir.isDirectory()==false){
			throw new CustomException("Extension Directory does not exists");
		}
		
		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			throw new CustomException("There is no extension files in the extension folder");
		}

		return list;
		
	}
	
	/**
	 * Gets the file count.
	 *
	 * @param folder the folder
	 * @param ext the ext
	 * @return the file count
	 */
	public static int getFileCount(String folder, String ext) {

      GenericExtFilter filter = new GenericExtFilter(ext);

      File dir = new File(folder);
      
      if(dir.isDirectory()==false){
          throw new CustomException("Directory does not exists : " + folder);
      }
      
      // list out all the file name and filter by the extension
      String[] list = dir.list(filter);

      return list.length;
      
  }
	
	/**
	 * Generate unique id.
	 *
	 * @return the string
	 */
	public static String generateUniqueId() {
		 return Long.toString(System.currentTimeMillis());
	     
	}
	
	/**
	 * Gets the Operating System from system property.
	 *
	 * @return OSType enum
	 */
    public static OSType getOSname() {
        String osType = System.getProperty("os.name");
        // Reporter.log("System properties os.name is : "+osType, true);
        return getOSname(osType);
    }

    /**
     * Gets the Operation system enumeration type from string an overloaded
     * method.
     *
     * @param osType the os type
     * @return OSType enum
     */
    public static OSType getOSname(String osType) {
        if (osType.toLowerCase().contains("win"))
            if (System.getenv("PROCESSOR_ARCHITECTURE").contains("86")
                    && System.getenv("PROCESSOR_ARCHITEW6432") != null) {
                return OSType.windows;
            } else {
                return OSType.windows;
            }
        else if (osType.toLowerCase().contains("win") && System.getProperty("os.arch").equalsIgnoreCase("x64"))
            return OSType.windows64;
        else if (osType.toLowerCase().contains("mac"))
            return OSType.mac;
        else if (osType.toLowerCase().contains("linux"))
            return OSType.linux;
        else
            return OSType.windows; // default to window
    }
	
	/**
	 * The main method.
	 *
	 * @param x the arguments
	 */
	public static void main(String x[]) {
		
		File appDir = new File("build" + File.separator + "QA");
		File app = new File(appDir, OSUtils.getFile(appDir.getAbsolutePath(), "ipa"));
		System.out.println(app.getName());
	}


}
