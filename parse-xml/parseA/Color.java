public class Color {

    private String color_name;
    private String hex_code;

    public Color(String c, String h) {
        this.color_name = c;
        this.hex_code = h;
    }

    public String color_name() {
        return this.color_name;
    }

    public String gethex_code() {
        return this.hex_code;
    }

    @Override
    public String toString() {
        return "Name: " + this.color_name + ", Hex: " + this.hex_code;
    }
}
