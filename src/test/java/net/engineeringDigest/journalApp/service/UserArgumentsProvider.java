//package net.engineeringDigest.journalApp.service;
//
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.stream.Stream;
//
//public class UserArgumentsProvider implements ArgumentsProvider {
//
//
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().username("sh11yam").password("shyam").build()),
//                Arguments.of(User.builder().username("s11uraj").password("suraj").build())
//
//        );
//
//    }
//}
