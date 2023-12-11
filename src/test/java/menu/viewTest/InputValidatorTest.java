package menu.viewTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import menu.view.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InputValidatorTest {
    InputValidator inputValidator = new InputValidator();

    @DisplayName("코치 이름 입력 판별")
    @ParameterizedTest(name = "{1}")
    @MethodSource("invalidCoachNames")
    void invalidCoachNamesThrowsException(List<String> input, String reason) {
        assertThatThrownBy(() -> inputValidator.validateNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("못먹는 메뉴 입력 판별")
    @ParameterizedTest(name = "{1}")
    @MethodSource("invalidUnedibleMenus")
    void invalidUnedibleMenusThrowsException(List<String> input, String reason) {
        assertThatThrownBy(() -> inputValidator.validateNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidCoachNames() {
        return Stream.of(
                Arguments.of(List.of("토미"), "코치 수 판별"),
                Arguments.of(List.of("토미,제임스,니콜,솔라,포비,캐롤"), "코치 수 판별"),
                Arguments.of(List.of("토미,다익스트라알고리즘"), "코치 이름 판별"),
                Arguments.of(List.of("토미,별"), "코치 이름 판별")
        );
    }

    static Stream<Arguments> invalidUnedibleMenus() {
        return Stream.of(
                Arguments.of(List.of("뇨끼,비빔밥,김치찌개"), "메뉴 수 판별"),
                Arguments.of(List.of("낙지김치죽"), "메뉴의 존재 여부 판별")
        );
    }
}
