//package net.engineeringDigest.journalApp.service;
//
//import net.engineeringDigest.journalApp.entity.JournalEntry;
//import net.engineeringDigest.journalApp.entity.User;
//import net.engineeringDigest.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//public class UserServiceTests {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//
//
//    @BeforeAll
//    static void setup() {
//
//    }
//
//    @BeforeEach
//    void beforeEachMethod() {
//
//    }
//
//    @AfterAll
//    static void closeConnection() {
//
//    }
//
//    @AfterEach
//    void afterEachMethod() {
//
//    }
//
//
//    @Test
//    @Disabled
//    public void testFindByUserName() {
//        User shyam = userRepository.findByUserName("ram");
//        assertTrue(!shyam.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUserName("shyam"));
//
//
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "ram",
//            "shyam",
//            "vipul"
//    })
//    public void testFindByUserName(String name) {
//        User shyam = userRepository.findByUserName(name);
//        assertTrue(!shyam.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUserName("shyam"));
//
//
//    }
//
//
//    @ParameterizedTest
//    @CsvSource(
//            {
//                    "1,1,2",
//                    "2,10,12",
//                    "3,21,24"
//
//            }
//    )
//    public void test(int a, int b, int expected) {
//        assertEquals(expected, a + b);
//    }
//
//
//    /// //////////////////////////
//    @ParameterizedTest
//    @ValueSource(strings =
//            {
//                    "ram",
//                    "shyam",
//                    "vipul"
//
//            }
//    )
//    public void test1(String userName) {
//
//        assertNotNull(userRepository.findByUserName(userName));
//    }
//
//
//    /// /////////////////   Arguments source /////////////////////
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void test2(User user) {
//
//        assertTrue(userService.saveNewUser(user));
//    }
//
//
//}
