package io.github.yagoazedias.snesgamesandroid;

public class Surface {

    int x, y, width, height;

    String type = "plant";

    public Surface() {
        if(type.equals("plant")) {
            this.x = 0;
            this.y = 0;
        }
    }

}
