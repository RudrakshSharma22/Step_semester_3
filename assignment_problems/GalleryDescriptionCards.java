abstract class ArtPiece {
    private static int counter = 1;
    private final String pieceId;
    ArtPiece() {
        pieceId = "P" + counter++;
    }
    public abstract String describe();
    String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {
    private String title;
    Painting(String title) {
        super();
        this.title = title;
    }
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;
    Sculpture(String title) {
        super();
        this.title = title;
    }
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class GalleryDescriptionCards {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(p.describe());
        System.out.println(s.describe());
        System.out.println(p.getPieceId());
        System.out.println(s.getPieceId());
    }
}
