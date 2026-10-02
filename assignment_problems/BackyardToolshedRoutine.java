abstract class GardenTool {
    GardenTool() {
    }
    public String use() {
        return "Using the tool in the garden";
    }
}

class CuttingTool extends GardenTool {
    CuttingTool() {
        super();
    }
    public String use() {
        return super.use() + ", blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    Pruner() {
        super();
    }
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class BackyardToolshedRoutine {
    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        Pruner p = new Pruner();
        System.out.println(c.use());
        System.out.println(p.use());
    }
}
