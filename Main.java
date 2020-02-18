package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Main extends Application{
    private static final int SIZE = 4;
    private Scene scene;
    private Tile[][] tiles = new Tile[SIZE][SIZE];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scene = new Scene(initialize());

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {   // handling key events in game
                switch (event.getCode()) {
                    case UP:    scene.setRoot(updated(1)); break;
                    case DOWN:  scene.setRoot(updated(2)); break;
                    case LEFT:  scene.setRoot(updated(3)); break;
                    case RIGHT: scene.setRoot(updated(4)); break;
                }
            }
        });

        primaryStage.setTitle("2048 GAME");
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent initialize(){   // setting initial state of the board
        Pane root = new Pane();

        Random r = new Random();
        Random r1 = new Random();
        int x1 = r.nextInt(4);
        int x2 = r.nextInt(4);
        int y1 = r.nextInt(4);
        int y2 = r.nextInt(4);


        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                if((j == x1 && i == y1) || (j == x2 && i == y2))
                {
                    if(r1.nextFloat() < 0.25)
                        tiles[i][j] = new Tile(4 ,i, j);
                    else
                        tiles[i][j] = new Tile(2 ,i, j);

                }
                else
                    tiles[i][j] = new Tile(0 ,i, j);
                root.getChildren().add(tiles[i][j]);
            }
        }
        root.setPrefSize(4*Tile.TILE_SIZE, 4*Tile.TILE_SIZE);
        return root;
    }

    private Parent updated(int mode) { // updating the board after pressing a key
        Pane root1 = new Pane();
        boolean bylruch = false;

        switch (mode) {
            case 1: {
                for(int j=0; j<4; j++)
                {
                    for(int i=0; i<3; i++)
                    {
                        if(tiles[i][j].getValue() != 0) {
                            int n = i + 1;
                            while (tiles[n][j].getValue() == 0 && n < 3)
                                n++;

                            if(tiles[n][j].getValue() == tiles[i][j].getValue())
                            {
                                tiles[i][j] = new Tile(2 * tiles[i][j].getValue(), i, j);
                                tiles[n][j] = new Tile(0, n, j);
                                bylruch = true;
                            }
                        }

                    }
                }
                for(int j=0; j<4; j++) {
                    for (int i = 0; i < 3; i++) {
                        if(tiles[i][j].getValue() == 0) {
                            int n = i + 1;
                            while (tiles[n][j].getValue() == 0 && n < 3)
                                n++;
                            if(tiles[n][j].getValue() != tiles[i][j].getValue())
                                bylruch = true;
                            tiles[i][j] = new Tile(tiles[n][j].getValue(), i, j);
                            tiles[n][j] = new Tile(0, n, j);
                        }
                    }
                }
            }; break;
            case 2: {
                for(int j=0; j<4; j++)
                {
                    for(int i=3; i>0; i--)
                    {
                        if(tiles[i][j].getValue() != 0) {
                            int n = i - 1;
                            while (tiles[n][j].getValue() == 0 && n > 0)
                                n--;

                            if(tiles[n][j].getValue() == tiles[i][j].getValue())
                            {
                                tiles[i][j] = new Tile(2 * tiles[i][j].getValue(), i, j);
                                tiles[n][j] = new Tile(0, n, j);
                                bylruch = true;
                            }
                        }

                    }
                }
                for(int j=0; j<4; j++) {
                    for (int i = 3; i > 0; i--) {
                        if(tiles[i][j].getValue() == 0) {
                            int n = i - 1;
                            while (tiles[n][j].getValue() == 0 && n > 0)
                                n--;
                            if(tiles[n][j].getValue() != tiles[i][j].getValue())
                                bylruch = true;
                            tiles[i][j] = new Tile(tiles[n][j].getValue(), i, j);
                            tiles[n][j] = new Tile(0, n, j);
                        }
                    }
                }
            }; break;
            case 3:  {
                for(int i=0; i<4; i++)
                {
                    for(int j=0; j<3; j++)
                    {
                        if(tiles[i][j].getValue() != 0) {
                            int n = j + 1;
                            while (tiles[i][n].getValue() == 0 && n < 3)
                                n++;

                            if(tiles[i][n].getValue() == tiles[i][j].getValue())
                            {
                                tiles[i][j] = new Tile(2 * tiles[i][j].getValue(), i, j);
                                tiles[i][n] = new Tile(0, i, n);
                                bylruch = true;
                            }
                        }

                    }
                }
                for(int i=0; i<4; i++) {
                    for (int j=0; j<3; j++) {
                        if(tiles[i][j].getValue() == 0) {
                            int n = j + 1;
                            while (tiles[i][n].getValue() == 0 && n < 3)
                                n++;
                            if(tiles[n][j].getValue() != tiles[i][j].getValue())
                                bylruch = true;
                            tiles[i][j] = new Tile(tiles[i][n].getValue(), i, j);
                            tiles[i][n] = new Tile(0, i, n);
                        }
                    }
                }
            }; break;
            case 4: {
                for(int i=0; i<4; i++)
                {
                    for(int j=3; j>0; j--)
                    {
                        if(tiles[i][j].getValue() != 0) {
                            int n = j - 1;
                            while (tiles[i][n].getValue() == 0 && n > 0)
                                n--;

                            if(tiles[i][n].getValue() == tiles[i][j].getValue())
                            {
                                tiles[i][j] = new Tile(2 * tiles[i][j].getValue(), i, j);
                                tiles[i][n] = new Tile(0, i, n);
                                bylruch = true;
                            }
                        }

                    }
                }
                for(int i=0; i<4; i++) {
                    for (int j = 3; j > 0; j--) {
                        if(tiles[i][j].getValue() == 0) {
                            int n = j - 1;
                            while (tiles[i][n].getValue() == 0 && n > 0)
                                n--;
                            if(tiles[n][j].getValue() != tiles[i][j].getValue())
                                bylruch = true;
                            tiles[i][j] = new Tile(tiles[i][n].getValue(), i, j);
                            tiles[i][n] = new Tile(0, i, n);
                        }
                    }
                }
            }; break;
        }

        boolean czypelna = true;
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                if(tiles[i][j].getValue() == 0)
                    czypelna = false;
        if(!czypelna && bylruch) {
            Random r = new Random();
            int x = r.nextInt(4);
            int y = r.nextInt(4);
            while (tiles[y][x].getValue() != 0) {
                x = r.nextInt(4);
                y = r.nextInt(4);
            }
            tiles[y][x] = new Tile(2, y, x);
        }
        else if(czypelna) {                                   //ending the game
            System.out.println("PRZEGRALES!!!!!!");
            System.exit(0);
        }
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                root1.getChildren().add(tiles[i][j]);
            }
        }

        root1.setPrefSize(4*Tile.TILE_SIZE, 4*Tile.TILE_SIZE);

        return root1;
    }

}
