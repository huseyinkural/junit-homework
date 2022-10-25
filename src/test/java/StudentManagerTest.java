
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    private StudentManager studentManager;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Instantiating Student Manager");
        studentManager = new StudentManager();
    }

    @Test
    @DisplayName("Should Create Student")
    public void shouldCreateStudent() {
        studentManager.addStudent("Huseyin", "Kural", "01234567892");
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }

    @Test
    @DisplayName("Grouped Create Student Test")
    void groupedStudentAssertions() {
        studentManager.addStudent("hus", "kur", "01234567892");
        assertAll("student",
                () -> assertEquals("hus", studentManager.getAllStudents().get(0).getFirstName()),
                () -> assertEquals("kur", studentManager.getAllStudents().get(0).getLastName()),
                () -> assertEquals("01234567892", studentManager.getAllStudents().get(0).getIdNumber())
        );
    }

    @Test
    @DisplayName("Should Not Create Student When Student is already exist")
    public void shouldThrowRuntimeExceptionWhenStudentIsExist() {
        studentManager.addStudent("Huseyin", "Kural", "01234567892");
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent("Huseyin", "Kural", "01234567894");
        });
    }

    @Test
    @DisplayName("Should Not Create Student When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent(null, "Kural", "01234567892");
        });
    }

    @Test
    @DisplayName("Should Not Create Student When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent("Hüseyin", null, "01234567892");
        });
    }

    @Test
    @DisplayName("Should Not Create Student When Id Number is Null")
    public void shouldThrowRuntimeExceptionWhenIdNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent("Hüseyin", "Kural", null);
        });
    }


    @Test
    @DisplayName("Id Number should ends with even number")
    public void shouldTestIdNumberFormat() {
        studentManager.addStudent("Hüseyin", "Kural", "01234567892");
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }

    @Test
    @DisplayName("Id Number should ends with odd number")
    public void shouldThrowRuntimeExceptionWhenIdNumberEndsWithOddFormat() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent("Hüseyin", "Kural", "11234567893");
        });
    }

    @Test
    @DisplayName("Id Number length should be 11")
    public void shouldThrowRuntimeExceptionWhenIdNumberLengthIsNot11() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent("Hüseyin", "Kural", "98765");
        });
    }


    @Nested
    class RepeatedTests {
        @DisplayName("Repeat Student Creation Test 5 Times")
        @RepeatedTest(value = 5,
                name = "Repeating Student Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestStudentCreationRepeatedly() {
            studentManager.addStudent("Hüseyin", "Kural", "01234567892");
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }
    }

    @Nested
    class ParameterizedTests {
        @DisplayName("Id Number should match the required Format")
        @ParameterizedTest
        @ValueSource(strings = {"01234567892", "98765432100", "98765432108"})
        public void shouldTestIdNumberFormatUsingValueSource(String idNumber) {
            studentManager.addStudent("Hüseyin", "Kural", idNumber);
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }
    }

    @DisplayName("Method Source Case - Id Number should match the required Format")
    @ParameterizedTest
    @MethodSource("idNumberList")
    public void shouldTestIdNumberFormatUsingMethodSource(String idNumber) {
        studentManager.addStudent("John", "Doe", idNumber);
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }

    private static List<String> idNumberList() {
        return Arrays.asList("01234567898", "27538825998", "98765432100");
    }

    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }


    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }

}