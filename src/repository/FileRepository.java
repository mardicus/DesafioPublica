package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gui.MainViewController;
import model.entities.GameInfo;


public class FileRepository {
	private File sourceFile;
	private File outputFile;
	
	MainViewController mainviewcontroller() throws IOException{
	MainViewController MVC = new MainViewController();
	sourceFile = MVC.getSourceFile();
	return MVC;
	}
	
	public void saveFile() {	
	String path = outputFile.getPath()+".csv";
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {		
		 for(GameInfo g:MainViewController.saveFilesGamesList) {
		 bw.write(g.getGameNumber()+","+g.getGameScore()+","
					+g.getSeasonMinimum()
					+","
					+g.getSeasonMaximum()
					+","
					+g.getMinimumRecordBreak()
					+","
					+g.getMaximumRecordBreak()); 
		 bw.newLine(); 
		 }
	} catch(IOException e) {
		e.printStackTrace();
	}
}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
}

	
