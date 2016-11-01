package tunisia.mall.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class JavaDropbox {

	public static final String DROP_BOX_APP_KEY = "4wm1im7jpiifnak";
	public static final String DROP_BOX_APP_SECRET = "oyhpbn06jd4mmai";
	private static final String DROP_BOX_ACCESS_TOKEN = "PYN0CaGlyrAAAAAAAAAAM1z0DQZMsVDL_9H6iiWJmBcOWOfepdW1ngtwVp2_xngj";
	private static DbxClient dbxClient;

	public DbxClient authDropbox(String dropBoxAppKey, String dropBoxAppSecret) throws IOException, DbxException {
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("JavaDropboxTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);

		/*
		 * 
		 * String authorizeUrl = dbxWebAuthNoRedirect.start();
		 * System.out.println("1. Authorize: Go to URL and click Allow : " +
		 * authorizeUrl); System.out .println(
		 * "2. Auth Code: Copy authorization code and input here "); String
		 * dropboxAuthCode = new BufferedReader(new InputStreamReader(
		 * System.in)).readLine().trim();
		 * 
		 * 
		 * DbxAuthFinish authFinish =
		 * dbxWebAuthNoRedirect.finish(dropboxAuthCode); String authAccessToken
		 * = authFinish.accessToken;
		 */
		dbxClient = new DbxClient(dbxRequestConfig, DROP_BOX_ACCESS_TOKEN);
		System.out.println("Dropbox Account Name: " + dbxClient.getAccountInfo().displayName);

		return dbxClient;
	}

	/* returns Dropbox size in GB */
	public long getDropboxSize() throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
	}

	public void uploadToDropbox(File inputFile, String repertoire) throws DbxException, IOException {
		// File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile + "");
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + repertoire + "/" + inputFile.getName(),
					DbxWriteMode.add(), inputFile.length(), fis);
			String sharedUrl = dbxClient.createShareableUrl("/" + repertoire + "/" + inputFile.getName());
			System.out.println("Uploaded: " + uploadedFile.toString() + " URL " + sharedUrl);
		} finally {
			fis.close();
		}
	}

	public void createFolder(String folderName) throws DbxException {
		dbxClient.createFolder("/" + folderName);
	}

	public DbxEntry.WithChildren listDropboxFolders(String folderPath) throws DbxException {
		DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(folderPath);
		System.out.println("Files List:");
		for (DbxEntry child : listing.children) {
			System.out.println("	" + child.name + ": " + child.toString());
		}

		return listing;
	}

	public void downloadFromDropbox(String fileName, String repertoire, String path) throws DbxException, IOException {

		FileOutputStream outputStream = new FileOutputStream(path);
		DbxEntry.File downloadedFile;
		try {
			downloadedFile = dbxClient.getFile("/" + repertoire + "/" + fileName, null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());

		} finally {
			outputStream.close();
		}

	}

	public File tempFileFromDropbox(String fileName, String repertoire, String path) throws DbxException, IOException {

		File file = File.createTempFile(fileName, ".jpg");

		FileOutputStream outputStream = new FileOutputStream(file);
		DbxEntry.File downloadedFile;
		try {
			downloadedFile = dbxClient.getFile("/" + repertoire + "/" + fileName, null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
			return file;
		} finally {
			outputStream.close();
			file.deleteOnExit();
		}

	}

	public boolean deleteFileFromDropbox(String fileName, String repertoire) {

		try {
			dbxClient.delete("/" + repertoire + "/" + fileName);
			return true;
		} catch (DbxException e) {
			return false;
		}

	}
	public boolean deleteFolderFromDropbox(String folderName) {

		try {
			dbxClient.delete("/" + folderName);
			return true;
		} catch (DbxException e) {
			return false;
		}

	}

	public static void main(String[] args) throws IOException, DbxException {
		JavaDropbox javaDropbox = new JavaDropbox();
		javaDropbox.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRET);
		System.out.println("Dropbox Size: " + javaDropbox.getDropboxSize() + " GB");
		// javaDropbox.uploadToDropbox("happy.png");
		// javaDropbox.createFolder("tutorial");
		// javaDropbox.listDropboxFolders("/chams");
		// javaDropbox.downloadFromDropbox("simwar.jpg", "siwar");
		if (javaDropbox.deleteFileFromDropbox("séance4.pptx", "chams")) {
			System.out.println("tfassa5 !!!!!!!");
		} else {
			System.out.println("haw ma tfassa5ech behi hakka !!!!!!!!");
		}
		
		if (javaDropbox.deleteFolderFromDropbox("chams")) {
			System.out.println("tfassa5 el utilisateur !!!!!!!");
		} else {
			System.out.println("haw ma tfassa5ech el utilisateur behi hakka  !!!!!!!!");
		}

	}

}
