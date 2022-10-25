public class Student {
    private String firstName;
    private String lastName;
    private String idNumber;

    public Student(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void validateFirstName() {
        if (this.firstName.isBlank())
            throw new RuntimeException("First Name cannot be null or empty");
    }

    public void validateLastName() {
        if (this.lastName.isBlank())
            throw new RuntimeException("Last Name cannot be null or empty");
    }

    public void validateIdNumber() {
        if (this.idNumber.isBlank()){
            throw new RuntimeException("Student ID Number cannot be null or empty");
        }

        if (this.idNumber.length() != 11) {
            throw new RuntimeException("Student ID Number Should be 11 Digits");
        }
        if (!this.idNumber.matches("\\d+")) {
            throw new RuntimeException("Student ID Number Contain only digits");
        }
        if (Integer.parseInt(String.valueOf(this.idNumber.charAt(this.idNumber.length()-1))) % 2 != 0) {
            throw new RuntimeException("Student ID Number Should Ends with even number");
        }
    }
}
