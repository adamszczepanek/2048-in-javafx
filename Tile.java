package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane{                //a class representing a single tile
    public static final int TILE_SIZE = 100;

    private Text text;
    private int valuee;
    private int row, column;
    private Rectangle rect = new Rectangle(TILE_SIZE - 4, TILE_SIZE - 4);

    public Tile(int value,int row,int column){
        text = new Text(Integer.toString(value));
        valuee = value;
        this.row = row;
        this.column = column;
        rect.setVisible(false);

        if(value <= 100)
            text.setFont(new Font("Verdana", 40));
        else if(value <= 1000)
            text.setFont(new Font("Verdana", 30));
        else
            text.setFont(new Font("Verdana", 20));

        if(value == 0)
            text.setVisible(false);

        switch (value){
            case 0:
                getStyleClass().add("StackPane0"); break;
            case 2:
                getStyleClass().add("StackPane2"); break;
            case 4:
                getStyleClass().add("StackPane4"); break;
            case 8:
                getStyleClass().add("StackPane8"); break;
            case 16:
                getStyleClass().add("StackPane16"); break;
            case 32:
                getStyleClass().add("StackPane32"); break;
            case 64:
                getStyleClass().add("StackPane64"); break;
            case 128:
                getStyleClass().add("StackPane128"); break;
            case 256:
                getStyleClass().add("StackPane256"); break;
            case 512:
                getStyleClass().add("StackPane512"); break;
            case 1024:
                getStyleClass().add("StackPane1024"); break;
            case 2048:
                getStyleClass().add("StackPane2048"); break;
        }


        getChildren().addAll(rect, text);
        setTranslateX(column * TILE_SIZE);
        setTranslateY(row * TILE_SIZE);
    }

    public int getValue() { return this.valuee; }

}
