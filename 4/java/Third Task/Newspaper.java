class Newspaper implements LibraryItem {

    private String title;
    private String date;
    private String[] headlines;

    public Newspaper(String title, String date, String[] headlines) {
        this.title = title;
        this.date = date;
        this.headlines = headlines;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Газета: " + title + " | Дата: " + date);
        System.out.print("Заголовки: ");
        for (String h : headlines) {
            System.out.print(h + ", ");
        }
        System.out.println();
    }
}