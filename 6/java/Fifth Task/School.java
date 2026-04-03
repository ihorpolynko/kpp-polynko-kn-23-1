// Акредитація школи
enum SchoolType {
    GENERAL, GYMNASIUM, LYCEUM
}

class School extends Building {

    private int students;
    private SchoolType type;

    public School(String address, SchoolType type, int students) {
        super(address);
        this.type = type;
        this.students = students;
    }

    public int getStudents() { return students; }
    public SchoolType getType() { return type; }

        public void setFromString(String str) {
        String[] parts = str.split(",");
        this.type = SchoolType.valueOf(parts[0]);
        this.students = Integer.parseInt(parts[1]);
    }

    @Override
    public void print() {
        System.out.println("Школа " + address + " [" + type + "] учнiв: " + students);
    }
}