package Exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
*Writes all the errors that are relevant to the server in the file on
 *
 */
public class CouponServerException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private static BufferedWriter bufferedWriter = null;
	private static FileWriter fileWriter = null;
	private static SimpleDateFormat dateformat = new SimpleDateFormat("dd_MM_yyyy");
	private static Date date = new Date();
	private static String erorsFileName = "errors/error" + dateformat.format(date) + "_" + date.getTime() + ".txt";


	public CouponServerException(String message, Exception exception) throws CouponServerException {
		super(message);
		openFileWriter();
		try {
			bufferedWriter.write(message + "\n ********* \n" + exception.getLocalizedMessage() + exception.getMessage()
					+ "\n ********* \n");
		} catch (IOException e) {
			throw new CouponServerException("error in the report to the server");
		}
	}

	/**
	 * 
	 */
	private void openFileWriter() throws CouponServerException {
		try {
			if (fileWriter == null||bufferedWriter==null) {
				fileWriter = new FileWriter(erorsFileName);
				bufferedWriter = new BufferedWriter(fileWriter);
				System.err.println("writer");
			}
		} catch (IOException e) {
			throw new CouponServerException("eror in the report to the server");
		}
	}

	/**
	 * RETURN AN INFORMATIVE MESSAGE
	 */
	public CouponServerException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public CouponServerException(String message, SQLException exception) throws CouponServerException {
		super(message);
		openFileWriter();
		try {
			bufferedWriter.write(message + "\n ********* \n" + exception.getMessage() + exception.getSQLState()
					+ "\n ********* \n");
		} catch (IOException e) {
			throw new CouponServerException("eror in the report to the server");
		}
	}

	/**
	 * close BufferedWriter when you turn off the program
	 */
	public static void closeBufferedWriter() throws CouponServerException {
		try {
			if (bufferedWriter != null)
				bufferedWriter.close();
		} catch (IOException e) {
			throw new CouponServerException("eror in the close report to the server");
		}
	}
}
