package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.entities.GameInfo;
import model.exceptions.EntranceException;
import model.services.GameService;
import repository.FileRepository;

public class MainViewController implements Initializable {
	@FXML
	private Button enterButton;
	@FXML
	private TextField textGameScore;
	@FXML
	private Label labelScore;
	@FXML
	private MenuBar menu = new MenuBar();
	@FXML
	GridPane gridPane = new GridPane();
	private int gameScore;

	public MenuBar getMenu() {
		return menu;
	}

	List<Label> labelListButton = new ArrayList<>();

	private List<GameInfo> gamesList = new ArrayList<>();
	public static List<GameInfo> saveFilesGamesList = new ArrayList<>();

	public MainViewController() throws FileNotFoundException, IOException {
	}

	private GameService gameService = new GameService();

	private File sourceFile;
	private int column = 1;
	private FileRepository repos = new FileRepository();

	@FXML
	public void openMenuAction(ActionEvent event) {
		try {
			List<GameInfo> openGamesList = new ArrayList<>();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(menu.getScene().getWindow());
			if (file != null) {
				sourceFile = file;
			}
			repos.setSourceFile(sourceFile);
			gameService.buffering(repos);
			gameService.addFileGame(openGamesList);
			gamesList = gameService.getList();

			for (int i = 0; i < gamesList.size(); i++) {
				List<Label> labelList = new ArrayList<>();
				labelList.clear();
				labelList.add(new Label(gamesList.get(i).getGameNumber().toString()));
				labelList.add(new Label(gamesList.get(i).getGameScore().toString()));
				labelList.add(new Label(gamesList.get(i).getSeasonMinimum().toString()));
				labelList.add(new Label(gamesList.get(i).getSeasonMaximum().toString()));
				labelList.add(new Label(gamesList.get(i).getMinimumRecordBreak().toString()));
				labelList.add(new Label(gamesList.get(i).getMaximumRecordBreak().toString()));

				this.addGridPane(gridPane, labelList);
			}
		} catch (FileNotFoundException f) {
			Alerts.showAlert("Erro", null, "Arquivo não encontrado", AlertType.ERROR);
		} catch (IOException i) {
			Alerts.showAlert("Erro", null, "Erro na entrada ou saida de dados", AlertType.ERROR);
		} catch (NumberFormatException i) {
			Alerts.showAlert("Erro", null, "Formato de entrada invalido, deve ser um número inteiro ou um arquivo .CSV",
					AlertType.ERROR);
		}
	}

	public void onEnterButtonAction() {
		try {
			gameScore = Integer.parseInt(textGameScore.getText());
			if (gameScore > 1000) {
				throw new EntranceException();
			}
			gameService.addScore(gameScore);
			gameService.addGame(gamesList);

			int gamesListSize = gamesList.size();
			if (gamesList.size() > 0) {
				gamesListSize -= 1;
			}
			labelListButton.clear();
			labelListButton.add(new Label(gamesList.get(gamesListSize).getGameNumber().toString()));
			labelListButton.add(new Label(gamesList.get(gamesListSize).getGameScore().toString()));
			labelListButton.add(new Label(gamesList.get(gamesListSize).getSeasonMinimum().toString()));
			labelListButton.add(new Label(gamesList.get(gamesListSize).getSeasonMaximum().toString()));
			labelListButton.add(new Label(gamesList.get(gamesListSize).getMinimumRecordBreak().toString()));
			labelListButton.add(new Label(gamesList.get(gamesListSize).getMaximumRecordBreak().toString()));

			this.addGridPane(gridPane, labelListButton);
		} catch (FileNotFoundException f) {
			Alerts.showAlert("Erro", null, "Arquivo não encontrado", AlertType.ERROR);
		} catch (IOException i) {
			Alerts.showAlert("Erro", null, "Erro na entrada ou saida de dados", AlertType.ERROR);
		} catch (NumberFormatException i) {
			Alerts.showAlert("Erro", null, "Formato de entrada invalido, deve ser um número inteiro", AlertType.ERROR);
		} catch (EntranceException e) {
			Alerts.showAlert("Erro", null, "O valor de entrada deve ser igual ou inferior a 1000", AlertType.ERROR);

		}
	}

	private void addGridPane(GridPane gridPane, List<Label> labelList) {
		gridPane.setAlignment(Pos.CENTER);
		for (int i = 0; i < 6; i++) {
			GridPane.setHalignment(labelList.get(i), HPos.CENTER);
			GridPane.setValignment(labelList.get(i), VPos.CENTER);
			labelList.get(i).setAlignment(Pos.CENTER);
			labelList.get(i).setTextAlignment(TextAlignment.CENTER);
			labelList.get(i).setFont(new Font("Arial", 16));
			gridPane.add(labelList.get(i), i, column);
		}
		column++;
	}

	@FXML
	private void saveMenuAction(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Salvar o arquvo");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		File outputFile = fileChooser.showSaveDialog(menu.getScene().getWindow());
		repos.setOutputFile(outputFile);
		repos.saveFile();
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public List<GameInfo> getSaveFileGamesList() {
		return saveFilesGamesList;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(textGameScore);
	}
}