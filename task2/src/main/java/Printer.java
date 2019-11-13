public class Printer {

    private int model;
    private String color;
    private String type;
    private int price;
    private String maker;

    public Printer(int _model, String _maker, String _color, String _type, int _price){
        this.model = _model;
        this.color = _color;
        this.type = _type;
        this.price = _price;
        this.maker = _maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getMaker() {
        return maker;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
